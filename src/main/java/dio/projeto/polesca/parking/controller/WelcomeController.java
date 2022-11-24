package dio.projeto.polesca.parking.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
public class WelcomeController {
    
    @GetMapping
    String welcome() {
        return "It's working: " + LocalDateTime.now();
    }
}
