package dio.projeto.polesca.parking.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dio.projeto.polesca.parking.mockData.ParkingMock;
import dio.projeto.polesca.parking.models.Parking;

@Service
public class ParkingService {
    private static Map<String, Parking> parkingMap = new HashMap<>();

    static {
        for (var parking  : ParkingMock.getParkins(10)) {
            parkingMap.put(parking.getId(), parking);
        }  
    }
    
    public List<Parking> findAll() {
        return parkingMap.values()
            .stream()
            .collect(Collectors.toList());
    }
    
}
