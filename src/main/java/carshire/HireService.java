package carshire;

import carshire.domain.Hire;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Kuba
 */
public interface HireService {

    /**
     *
     * @return
     */
    public List<Hire> findAllHires();

    /**
     *
     * @param hire
     * @return
     */
    public Hire save(Hire hire);

    /**
     *
     * @param hire
     */
    public void delete(Hire hire);
    
    /**
     *
     * @param carId
     * @param status
     * @return
     */
    public Hire findByCarIdAndStatus(final Long carId, final Hire.HireStatus status);
    
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
    BigDecimal findSumOfEmployeeEarnings(Long sellerId);
}
