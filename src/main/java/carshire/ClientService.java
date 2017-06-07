package carshire;

import carshire.domain.Client;
import java.util.List;

/**
 *
 * @author Kuba
 */
public interface ClientService {

    /**
     *
     * @return
     */
    public List<Client> findAllClients();

    /**
     *
     * @param client
     * @return
     */
    public Client save(Client client);

    /**
     *
     * @param client
     */
    public void delete(Client client);
    
    /**
     *
     * @param id
     * @return
     */
    public Client findById(final Long id);
}
