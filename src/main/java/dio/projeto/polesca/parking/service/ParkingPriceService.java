package dio.projeto.polesca.parking.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import dio.projeto.polesca.parking.models.ParkingPrice;

@Service
public class ParkingPriceService {
    private static ParkingPrice parkingPrice = new ParkingPrice(15, 3, 5.0);

    public Double calculate(LocalDateTime entry, LocalDateTime exit) {
        var minutes = ChronoUnit.MINUTES.between(entry, exit);

        var fractions = (int) (minutes / parkingPrice.getFractionOfTimeInMinutes());
        var lastFraction =(int) (minutes % parkingPrice.getFractionOfTimeInMinutes());
        if (lastFraction > parkingPrice.getToleranceInMinutes()) {
            fractions++;
        }
        
        return fractions * parkingPrice.getValue();
    }

}
