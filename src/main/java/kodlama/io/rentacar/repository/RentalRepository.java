package kodlama.io.rentacar.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import kodlama.io.rentacar.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Integer> {

}
