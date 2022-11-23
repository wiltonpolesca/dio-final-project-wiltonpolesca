package dio.projeto.polesca.parking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class VehicleBrand {
    @NonNull private Integer id;
    @NonNull private String name;
}
