package dio.projeto.polesca.parking.controller.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import dio.projeto.polesca.parking.controller.dto.ParkingCreateDTO;
import dio.projeto.polesca.parking.controller.dto.ParkingDTO;
import dio.projeto.polesca.parking.models.Parking;

@Component
public class ParkingMapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ParkingDTO toParkingDTO(Parking parking) {
        return MODEL_MAPPER.map(parking, ParkingDTO.class);
    }

    public Parking toParkingFromDTO(ParkingDTO dto) {
        return MODEL_MAPPER.map(dto, Parking.class);
    }

    public Parking toParkingFromCreateDTO(ParkingCreateDTO dto) {
        return MODEL_MAPPER.map(dto, Parking.class);
    }
}
