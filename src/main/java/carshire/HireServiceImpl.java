package carshire;

import carshire.domain.Hire;
import carshire.repository.HireRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class HireServiceImpl implements HireService {

    private final HireRepository hireRepository;

    @Autowired
    public HireServiceImpl(HireRepository hireRepository) {
        this.hireRepository = hireRepository;
    }

    public List<Hire> findAllHires() {
        return hireRepository.findAll();
    }

    @Transactional
    public Hire save(Hire hire) {
        return hireRepository.save(hire);
    }

    @Override
    public void delete(Hire hire) {
        hireRepository.delete(hire);
    }

    @Override
    public Hire findByCarIdAndStatus(Long carId, Hire.HireStatus status) {
        return hireRepository.findByCarIdAndStatus(carId, status);
    }

    @Override
    public Long countBySellerId(Long sellerId) {
        return hireRepository.countBySellerId(sellerId);
    }

    @Override
    public BigDecimal findSumOfEmployeeEarnings(Long sellerId) {
        return hireRepository.findSumOfEmployeeEarnings(sellerId);
    }
}
