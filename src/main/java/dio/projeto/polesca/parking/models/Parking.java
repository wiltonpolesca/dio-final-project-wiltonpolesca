package dio.projeto.polesca.parking.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class Parking {
    @Id
    @NonNull private String id;
    @NonNull private String license;
    @NonNull private String model;
    @NonNull private String color;
    @NonNull private String state;
    @NonNull private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private Double bill;
    
    public Parking() {}
}
