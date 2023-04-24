package kodlama.io.rentacar.repository;

import kodlama.io.rentacar.entities.Car;
import kodlama.io.rentacar.entities.Maintenance;
import kodlama.io.rentacar.entities.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {



    // Select * from maintenances where car_id = {carID} and iscompleted = false
    Maintenance findMaintenanceByCarIdAndIsCompletedFalse(int carId);




    // Select * from maintenances where car_id = {carID} and iscompleted = false
    // Eğer bu koşula uygun data bulursa true döndür yoksa false
    boolean existsByCarIdAndIsCompletedFalse(int carId);

    boolean existsByCarIdAndIsCompletedIsFalse(int carId);

}
