package carshire.repository;

import carshire.domain.Seller;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    public static final String FIND_EMPLOYEES = "select * from seller where rights = 'employee'";

    public static final String FIND_MANAGERS = "select * from seller where rights = 'manager'";

    @Query(nativeQuery = true, value = FIND_EMPLOYEES)
    List<Seller> findAllEmployees();

    @Query(nativeQuery = true, value = FIND_MANAGERS)
    List<Seller> findAllManagers();
    
    public Seller findByLogin(final String login);
}
