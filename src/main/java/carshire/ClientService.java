package carshire;

import carshire.domain.Client;
import java.util.List;

public interface ClientService {

    public List<Client> findAllClients();

    public Client save(Client client);

    public void delete(Client client);
    
    public Client findOne(Long id);
}
