package dio.projeto.polesca.parking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class VehicleModel {
    @NonNull private Integer id;
    @NonNull private VehicleBrand brand;
    @NonNull private String name;
}
