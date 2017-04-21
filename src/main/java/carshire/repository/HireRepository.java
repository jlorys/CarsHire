package carshire.repository;

import carshire.domain.Hire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HireRepository extends JpaRepository<Hire, Long> {

}
