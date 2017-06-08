package carshire;

import carshire.domain.Seller;
import carshire.domain.Seller.Rights;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import static org.hamcrest.Matchers.is;
import org.junit.After;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Kuba
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class SellerRepositoryTest {

    @Autowired
    private SellerService repository;

    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        repository.deleteAll();
        Seller seller1 = new Seller(1L, "Maciej", "Wiertnik", "wiertel", "mwiertnik@o2.pl", "mw", "Rzeszow", "Sienkiwicza", "14", Rights.Admin);
        Seller seller2 = new Seller(2L, "Dominik", "Chochlik", "chochel", "dchochlik@o2.pl", "dc", "Rzeszow", "Marynarska", "99", Rights.Manager);
        Seller seller3 = new Seller(3L, "Janusz", "Tranowski", "tranel", "jtranowski@o2.pl", "jt", "Rzeszow", "Wojenna", "19", Rights.Employee);
        repository.save(seller1);
        repository.save(seller2);
        repository.save(seller3);
    }

    /**
     *
     */
    @Test
    public void find_OneManagerEntryFound_ShouldReturnAListOfOneEntry() {
        List<Seller> sellerEntries = repository.findAllManagers();
        assertThat(sellerEntries.size(), is(1));
    }

    /**
     *
     */
    @Test
    public void find_OneEmployeeEntryFound_ShouldReturnAListOfOneEntry() {
        List<Seller> sellerEntries = repository.findAllEmployess();
        assertThat(sellerEntries.size(), is(1));
    }

    /**
     *
     */
    @Test
    public void find_OneSellerEntryFound_ShouldReturnOneEntry() {
        Seller seller = repository.findByLogin("wiertel");
        String login = seller.getLogin();
        assertTrue(login.equals("wiertel"));
    }

    @After
    public void after() throws Exception {
        repository.deleteAll();
    }
}
