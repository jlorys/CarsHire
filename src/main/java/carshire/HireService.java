package carshire;

import carshire.domain.Hire;
import java.math.BigDecimal;
import java.util.List;

public interface HireService {

    public List<Hire> findAllHires();

    public Hire save(Hire hire);

    public void delete(Hire hire);
    
    public Hire findByCarIdAndStatus(final Long carId, final Hire.HireStatus status);
    
    public Long countBySellerId(Long sellerId);
    
    BigDecimal findSumOfEmployeeEarnings(Long sellerId);
}
