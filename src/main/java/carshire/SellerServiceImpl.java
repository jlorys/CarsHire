package carshire;

import carshire.domain.Seller;
import carshire.repository.SellerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public List<Seller> findAllSellers() {
        return sellerRepository.findAll();
    }

    @Transactional
    public Seller save(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public void delete(Seller seller) {
        sellerRepository.delete(seller);
    }

    @Override
    public List<Seller> findAllEmployess() {
        return sellerRepository.findAllEmployees();
    }

    @Override
    public List<Seller> findAllManagers() {
        return sellerRepository.findAllManagers();
    }

    @Override
    public void deleteAll() {
        sellerRepository.deleteAll();
    }
}
