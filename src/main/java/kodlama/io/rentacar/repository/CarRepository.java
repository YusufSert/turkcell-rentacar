package kodlama.io.rentacar.repository;

import kodlama.io.rentacar.entities.Car;
import kodlama.io.rentacar.entities.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    // Returns cars where car by its state(AVAILABLE, RENTED, MAINTANCE)
    @Query(value = "SELECT * FROM cars where state = ?", nativeQuery = true)
        List<Car> getCarByState(State state);
}
