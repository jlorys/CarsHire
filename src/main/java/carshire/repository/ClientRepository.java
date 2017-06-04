package carshire.repository;

import carshire.domain.Car;
import carshire.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    
    public Client findById(final Long id);
}
