package dio.projeto.polesca.parking.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dio.projeto.polesca.parking.helpers.UUIDHelper;
import dio.projeto.polesca.parking.mockData.ParkingMock;
import dio.projeto.polesca.parking.models.Parking;

@Service
public class ParkingService {
    private static Map<String, Parking> parkingMap = new HashMap<>();
    private ParkingPriceService priceService;

    // static {
    //     for (var parking : ParkingMock.getParkins(10)) {
    //         parkingMap.put(parking.getId(), parking);
    //     }
    // }
    
    public ParkingService(ParkingPriceService priceService) {
        this.priceService = priceService;
    }

    public List<Parking> findAll() {
        return parkingMap.values()
                .stream()
                .collect(Collectors.toList());
    }

    public Parking findById(String id) {
        var item = parkingMap.get(id);
        if (item == null) {
            throw new NotFoundException("Parking with id '" + id + "' not found!");
        }
        return item;
    }

    public void create(Parking item) {
        if (item.getId() == null) {
            item.setId(UUIDHelper.getUUID());
        }

        if (item.getEntryDate() == null) {
            item.setEntryDate(LocalDateTime.now());
        }
        parkingMap.put(item.getId(), item);
    }

    public void update(String id, Parking item) {
        var value = findById(id);
        value.setColor(item.getColor());
        parkingMap.replace(id, value);
    }

    public void delete(String id) {
        findById(id);
        parkingMap.remove(id);
    }

    public Parking exit(String id) {
        var parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(priceService.calculate(parking.getEntryDate(), parking.getExitDate()));
        update(id, parking);
        return parking;
    }
}
