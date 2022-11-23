package dio.projeto.polesca.parking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dio.projeto.polesca.parking.helpers.CSVLoaderHelp;

@SpringBootApplication
public class PolescaParkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PolescaParkingApplication.class, args);
	}

}
