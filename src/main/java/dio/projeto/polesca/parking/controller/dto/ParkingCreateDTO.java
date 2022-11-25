package dio.projeto.polesca.parking.controller.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ParkingCreateDTO {
    private String license;
    private String model;
    private String color;
    private String state;
    private LocalDateTime entryDate;
}
