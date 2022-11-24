package dio.projeto.polesca.parking.controller;

import java.security.Provider.Service;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dio.projeto.polesca.parking.controller.dto.ParkingCreateDTO;
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
    ResponseEntity<List<ParkingDTO>> findAll() {
        var items = parkingService.findAll()
                .stream()
                .map(mapper::toParkingDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    ResponseEntity<ParkingDTO> findById(@PathVariable String id) {
        var value =  parkingService.findById(id);
        if (value == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.toParkingDTO(value));
    }
    
    @PostMapping
    ResponseEntity<ParkingDTO> post(@RequestBody ParkingCreateDTO dto) {
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
        var item = mapper.toParkingFromCreateDTO(dto);

        if (item == null) {
            return ResponseEntity.noContent().build();
        }

        parkingService.create(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toParkingDTO(item));
    }

}
