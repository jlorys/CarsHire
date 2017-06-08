package carshire.repository;

import carshire.domain.Hire;
import carshire.domain.Hire.HireStatus;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Kuba
 */
public interface HireRepository extends JpaRepository<Hire, Long> {
    
    /**
     *
     * @param carId
     * @param status
     * @return
     */
    public Hire findByCarIdAndStatus(Long carId, HireStatus status);
    
    /**
     *
     * @param sellerId
     * @return
     */
    public Long countBySellerId(Long sellerId);
    
    /**
     *
     * @param sellerId
     * @return
     */
    @Query("select sum(price_for_hire+default_interest) from Hire where seller_id=?#{[0]}")
    public BigDecimal findSumOfEmployeeEarnings(Long sellerId);

}
