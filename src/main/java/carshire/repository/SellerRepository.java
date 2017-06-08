package carshire.repository;

import carshire.domain.Seller;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Kuba
 */
public interface SellerRepository extends JpaRepository<Seller, Long> {

    /**
     *
     */
    public static final String FIND_EMPLOYEES = "select * from seller where rights = 'employee'";

    /**
     *
     */
    public static final String FIND_MANAGERS = "select * from seller where rights = 'manager'";

    /**
     *
     * @return
     */
    @Query(nativeQuery = true, value = FIND_EMPLOYEES)
    List<Seller> findAllEmployees();

    /**
     *
     * @return
     */
    @Query(nativeQuery = true, value = FIND_MANAGERS)
    List<Seller> findAllManagers();
    
    /**
     *
     * @param login
     * @return
     */
    Seller findByLogin(String login);
    
    /**
     *
     * @param id
     * @return
     */
    Seller findById(Long id);
}
