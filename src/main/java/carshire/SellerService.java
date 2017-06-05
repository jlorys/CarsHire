package carshire;

import carshire.domain.Seller;
import java.util.List;

public interface SellerService {

    public List<Seller> findAllSellers();

    public List<Seller> findAllEmployess();

    public List<Seller> findAllManagers();

    public Seller save(Seller seller);

    public void delete(Seller seller);
    
    public void deleteAll();
    
    public Seller findByLogin(final String login);
}
