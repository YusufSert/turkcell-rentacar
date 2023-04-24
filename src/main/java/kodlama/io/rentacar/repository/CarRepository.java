package kodlama.io.rentacar.repository;

import kodlama.io.rentacar.entities.Car;
import kodlama.io.rentacar.entities.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    // Returns cars where car by its state(AVAILABLE, RENTED, MAINTANCE)
    @Query("SELECT c FROM Car c where c.state = ?1")
        List<Car> getCarByState(State state);

    @Query("Select c.state from Car c where c.id = ?1")
        State getCarStateById(int id);

    List<Car> findAllByStateIsNot(State maintenance);
}
