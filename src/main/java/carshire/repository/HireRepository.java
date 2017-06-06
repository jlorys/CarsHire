package carshire.repository;

import carshire.domain.Hire;
import carshire.domain.Hire.HireStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HireRepository extends JpaRepository<Hire, Long> {
    
    public Hire findByCarIdAndStatus(final Long carId, final HireStatus status);

}
