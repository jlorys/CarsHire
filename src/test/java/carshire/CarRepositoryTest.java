package carshire;

import carshire.domain.Car;
import carshire.domain.Car.CarStatus;
import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import static org.hamcrest.Matchers.is;
import org.junit.After;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Kuba
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class CarRepositoryTest {

    @Autowired
    private CarService service;

    @Before
    public void setUp() throws Exception {
        service.deleteAll();
        Car car1 = new Car(1L, "Daewoo", "Lanos", 1998, 1.5f, 140123, new BigDecimal("21.0"), new BigDecimal("21.0"), CarStatus.Avalible, 0, "RZE1212");
        Car car2 = new Car(2L, "Ferrari", "Enzo", 1996, 4.2f, 189000, new BigDecimal("111.23"), new BigDecimal("111.23"), CarStatus.Avalible, 0, "RZE1236");
        Car car3 = new Car(3L, "Corvette", "Stingray", 1980, 3.6f, 211555, new BigDecimal("97.45"), new BigDecimal("21.0"), CarStatus.Avalible, 0, "RZE9912");
        service.save(car1);
        service.save(car2);
        service.save(car3);
    }

    @Test
    public void find_AllAvalibleCarEntriesFound_ShouldReturnAListOfManyEntries() {
        List<Car> sellerEntries = service.findAllAvalibleCars();
        assertThat(sellerEntries.size(), is(3));
    }

    @Test
    public void find_AllNotAvalibleCarEntriesFound_ShouldReturnAListOfZeroEntries() {
        List<Car> sellerEntries = service.findAllNotAvalibleCars();
        assertThat(sellerEntries.size(), is(0));
    }
    
    @After
    public void after() throws Exception {
        service.deleteAll();
    }
}
