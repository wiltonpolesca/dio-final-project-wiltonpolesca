package dio.projeto.polesca.parking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dio.projeto.polesca.parking.helpers.UUIDHelper;
import dio.projeto.polesca.parking.models.Parking;
import dio.projeto.polesca.parking.repository.ParkingRepository;

@Service
public class ParkingService {
    private ParkingPriceService priceService;
    private ParkingRepository repository;

    public ParkingService(ParkingRepository repository, ParkingPriceService priceService) {
        this.priceService = priceService;
        this.repository = repository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Parking> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Parking findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Parking with id '" + id + "' not found!"));
    }

    @Transactional
    public Parking create(Parking item) {
        if (item.getId() == null) {
            item.setId(UUIDHelper.getUUID());
        }

        if (item.getEntryDate() == null) {
            item.setEntryDate(LocalDateTime.now());
        }
        return repository.save(item);
    }

    @Transactional
    public Parking update(String id, Parking item) {
        var value = findById(id);
        value.setColor(item.getColor());
        return repository.save(value);
    }

    @Transactional
    public void delete(String id) {
        repository.delete(findById(id));
    }

    @Transactional
    public Parking checkout(String id) {
        var parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(priceService.calculate(parking.getEntryDate(), parking.getExitDate()));
        return update(id, parking);
    }
}
