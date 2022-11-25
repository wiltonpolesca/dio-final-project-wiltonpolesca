package dio.projeto.polesca.parking.controller;

import java.time.LocalDateTime;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import dio.projeto.polesca.parking.controller.dto.ParkingCreateDTO;
import dio.projeto.polesca.parking.mockData.ParkingMock;
import dio.projeto.polesca.parking.models.Parking;
import dio.projeto.polesca.parking.service.ParkingService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingServiceTest extends AbstractContainerBase {

    @Autowired
    ParkingService parkingService;

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest() {
        RestAssured.port = randomPort;
    }

    @Test
    void whenFindAllThenCheckResult() {

        for (var i = 1; i < 5; i++) {
            parkingService.create(getRandomParking());
        }

        RestAssured.given()
                .when()
                .get("/parking")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void whenCreateThenCheckIsCreate() {
        var parking = new ParkingCreateDTO();
        parking.setColor("Blue");
        parking.setLicense("ABCD-1234");
        parking.setModel("Gol");
        parking.setState("MG");

        RestAssured.given()
                .when()
                .contentType(ContentType.JSON)
                .body(parking)
                .post("/parking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("license", Matchers.equalTo("ABCD-1234"));
    }

    @Test
    void whenClientExitCalculateTheBill() {
        var parking = getRandomParking();
        parking.setEntryDate(LocalDateTime.now().minusMinutes(60));
        parkingService.create(parking);
        
        RestAssured.given()
                .when()
                .post("/parking/" + parking.getId())
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("bill", Matchers.equalTo(20.0f));
    }

    Parking getRandomParking() {
        return ParkingMock.getParkins(1).stream()
                .findFirst()
                .orElse(null);
    }
}
