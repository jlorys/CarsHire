package carshire.repository;

import carshire.domain.Car;
import carshire.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Kuba
 */
public interface ClientRepository extends JpaRepository<Client, Long> {
    
    /**
     *
     * @param id
     * @return
     */
    public Client findById(final Long id);
}
