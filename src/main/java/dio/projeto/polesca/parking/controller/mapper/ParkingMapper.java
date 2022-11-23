package dio.projeto.polesca.parking.controller.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import dio.projeto.polesca.parking.controller.dto.ParkingDTO;
import dio.projeto.polesca.parking.models.Parking;

@Component
public class ParkingMapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ParkingDTO toParkingDTO(Parking parking) {
        return MODEL_MAPPER.map(parking, ParkingDTO.class);
    }
}
