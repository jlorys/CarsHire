package carshire.repository;

import carshire.domain.Hire;
import carshire.domain.Hire.HireStatus;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HireRepository extends JpaRepository<Hire, Long> {
    
    public Hire findByCarIdAndStatus(final Long carId, final HireStatus status);
    
    public Long countBySellerId(Long sellerId);
    
    @Query("select sum(price_for_hire+default_interest) from Hire where seller_id=?#{[0]}")
    public BigDecimal findSumOfEmployeeEarnings(Long sellerId);

}
