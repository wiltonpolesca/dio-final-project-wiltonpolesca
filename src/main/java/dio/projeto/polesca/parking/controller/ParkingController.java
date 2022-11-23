package dio.projeto.polesca.parking.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dio.projeto.polesca.parking.controller.dto.ParkingDTO;
import dio.projeto.polesca.parking.controller.mapper.ParkingMapper;
import dio.projeto.polesca.parking.service.ParkingService;

@RestController
@RequestMapping("parking")
public class ParkingController {

    private ParkingService parkingService;
    private ParkingMapper mapper;

    public ParkingController(
        ParkingService service,
        ParkingMapper mapper) {
        this.parkingService = service;
        this.mapper = mapper;
    }
    
    @GetMapping
    List<ParkingDTO> findAll() {
        return parkingService.findAll()
           .stream()
           .map(x -> mapper.toParkingDTO(x))
           .collect(Collectors.toList());
    }
}
