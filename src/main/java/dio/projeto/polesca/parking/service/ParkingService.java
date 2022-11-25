package dio.projeto.polesca.parking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

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

    public List<Parking> findAll() {
        return repository.findAll();
    }

    public Parking findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Parking with id '" + id + "' not found!"));
    }

    public Parking create(Parking item) {
        if (item.getId() == null) {
            item.setId(UUIDHelper.getUUID());
        }

        if (item.getEntryDate() == null) {
            item.setEntryDate(LocalDateTime.now());
        }
        return repository.save(item);
    }

    public Parking update(String id, Parking item) {
        var value = findById(id);
        value.setColor(item.getColor());
        return repository.save(value);
    }

    public void delete(String id) {
        repository.delete(findById(id));
    }

    public Parking exit(String id) {
        var parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(priceService.calculate(parking.getEntryDate(), parking.getExitDate()));
        return update(id, parking);
    }
}
