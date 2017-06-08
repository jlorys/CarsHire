package carshire;

import carshire.domain.Seller;
import java.util.List;

/**
 *
 * @author Kuba
 */
public interface SellerService {

    /**
     *
     * @return
     */
    public List<Seller> findAllSellers();

    /**
     *
     * @return
     */
    public List<Seller> findAllEmployess();

    /**
     *
     * @return
     */
    public List<Seller> findAllManagers();

    /**
     *
     * @param seller
     * @return
     */
    public Seller save(Seller seller);

    /**
     *
     * @param seller
     */
    public void delete(Seller seller);
    
    /**
     *
     */
    public void deleteAll();
    
    /**
     *
     * @param login
     * @return
     */
    public Seller findByLogin(String login);
    
    /**
     *
     * @param id
     * @return
     */
    public Seller findById(Long id);
}
