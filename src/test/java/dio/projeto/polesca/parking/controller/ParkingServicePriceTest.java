package dio.projeto.polesca.parking.controller;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dio.projeto.polesca.parking.service.ParkingPriceService;

@SpringBootTest
public class ParkingServicePriceTest {

    @Autowired
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
    }

    @Test
    void whenTheClientStayFor1HourWillPay20() {
        var now = LocalDateTime.now();
        
        var value = service.calculate(now, now.plusMinutes(60));
        Assertions.assertEquals(20.0, value);
    }

    @Test
    void whenTheClientStayFor2HoursWillPay27() {
        
        // 2hs => 20.0 + 7.0 = 27.0
        
        var now = LocalDateTime.now();
        
        var value = service.calculate(now, now.plusMinutes(120));
        Assertions.assertEquals(27.0, value);
    }

    @Test
    void whenTheClientStayFor1hand30mWillPay27() {
        // After 1h, no more fraction is considered, it will always be an additional hour
        // 1hs 30m => 20.0 + 7.0 = 27.0
        
        var now = LocalDateTime.now();
        
        var value = service.calculate(now, now.plusMinutes(90));
        Assertions.assertEquals(27.0, value);
    }
    
    @Test
    void whenTheClientStayFor45mWillPay15() {
        // After 1h, no more fraction is considered, it will always be an additional hour
        // 45m => 3 * 5.0 = 15.0
        
        var now = LocalDateTime.now();
        
        var value = service.calculate(now, now.plusMinutes(45));
        Assertions.assertEquals(15, value);
    }

    @Test
    void whenTheClientStayFor49mWillPay20() {
        // After 1h, no more fraction is considered, it will always be an additional hour
        // 49m => 3 * 5.0 + 5.0 = 20.0
        
        var now = LocalDateTime.now();
        
        var value = service.calculate(now, now.plusMinutes(49));
        Assertions.assertEquals(20, value);
    }
    
    
    @Test
    void whenTheClientStayFor1d5hWillPay68() {
        // After 1h, no more fraction is considered, it will always be an additional hour
        // 1d 5hm => 20.h + 20h + (4 * 7) = 68.0
        
        var now = LocalDateTime.now();
        
        var value = service.calculate(now, now.plusHours(29));
        Assertions.assertEquals(68, value);
    }
    
}
