package dio.projeto.polesca.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dio.projeto.polesca.parking.models.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, String> {    
}
