package dio.projeto.polesca.parking.controller.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ParkingDTO {
    private String id;
    private String license;
    private String model;
    private String color;
    private String state;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private Double bill;
}
