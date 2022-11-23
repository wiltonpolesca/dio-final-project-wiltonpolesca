package dio.projeto.polesca.parking.models;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Parking {
    @NonNull private String id;
    @NonNull private String license;
    @NonNull private String model;
    @NonNull private String color;
    @NonNull private String state;
    @NonNull private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private Double bill;
}
