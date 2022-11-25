package dio.projeto.polesca.parking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class ParkingPrice {
    @NonNull private Integer fractionOfTimeInMinutes;
    @NonNull private Integer toleranceInMinutes;
    @NonNull private Double value;
}
