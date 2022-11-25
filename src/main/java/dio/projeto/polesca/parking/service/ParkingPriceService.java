package dio.projeto.polesca.parking.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

@Service
public class ParkingPriceService {
    private static final double FRACTION_VALUE = 5.0;
    private static final double FIRST_HOUR_VALUE = 20.0;
    private static final double ADDITIONAL_HOUR_VALUE = 7.0;
    private static final double DAILY_VALUE = 20.0;

    private static final int FRACTION_MINUTES = 15;
    private static final int HOUR_IN_MINUTES = 60;
    private static final int DAY_IN_MINUTES = 24 * HOUR_IN_MINUTES;

    public Double calculate(LocalDateTime entry, LocalDateTime exit) {
        var value = 0.0;
        var days = ChronoUnit.DAYS.between(entry, exit);
        var minutes = ChronoUnit.MINUTES.between(entry, exit) - (days * DAY_IN_MINUTES);

        var hours = (int) (minutes / HOUR_IN_MINUTES);

        if (hours > 0) {
            // Value of the first hour.
            value += FIRST_HOUR_VALUE;

            minutes = minutes - (hours * HOUR_IN_MINUTES);

            //subtract the first hour.
            hours--;
            
            if (minutes > 0) {
                // has more one aditional hour
                hours++;
                minutes = 0;
            }
            
        } else {
            hours = 0;
        }
        var fractions = (int) (minutes / FRACTION_MINUTES);

        var lastFraction =(int) (minutes % FRACTION_MINUTES);
        
        // if the client stay more than 10% of the time of one fraction after the last fraction, 
        // will pay for 1 fraction.
        if (lastFraction > (FRACTION_MINUTES * 0.10)) {
            fractions++;
        }       
        
        value += days * DAILY_VALUE;
        value += hours * ADDITIONAL_HOUR_VALUE;
        value += fractions * FRACTION_VALUE;
        return value;
    }
}
