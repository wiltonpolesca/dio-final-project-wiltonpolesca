package dio.projeto.polesca.parking.controller;

import java.time.LocalDateTime;

import org.codehaus.groovy.control.StaticImportVisitor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import dio.projeto.polesca.parking.service.ParkingPriceService;

@SpringBootTest
public class ParkingServicePriceTest {

    private ParkingPriceService service;
    
    @BeforeEach
    void beforeEach() {
        service = new ParkingPriceService();
    }
    
    @Test
    void whenTheClientStayUntil18MinWillPay5() {
        
        var now = LocalDateTime.now();
        
        var value = service.calculate(now, now.plusMinutes(15));
        Assertions.assertEquals(5.0, value);
        
        value = service.calculate(now, now.plusMinutes(16));
        Assertions.assertEquals(5.0, value);
        
        value = service.calculate(now, now.plusMinutes(17));
        Assertions.assertEquals(5.0, value);

        value = service.calculate(now, now.plusMinutes(18));
        Assertions.assertEquals(5.0, value);
    }

    @Test
    void whenTheClientStayFor1HourWillPay20() {
        var now = LocalDateTime.now();
        
        var value = service.calculate(now, now.plusMinutes(60));
        Assertions.assertEquals(20.0, value);
    }

    @Test
    void whenTheClientStayFor2HoursWillPay40() {
        var now = LocalDateTime.now();
        
        var value = service.calculate(now, now.plusMinutes(120));
        Assertions.assertEquals(40.0, value);
    }
    
}
