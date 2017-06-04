package carshire.repository;

import carshire.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

    public Car findById(final Long id);
}
