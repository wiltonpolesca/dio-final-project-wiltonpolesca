package dio.projeto.polesca.parking.models;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Vehicle {
    private String plate;
    private String model;
    private String color;
    
    // ?
    private LocalDate timestamp;
}
