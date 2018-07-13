package carshire;

import carshire.domain.Hire;
import carshire.domain.Hire.HireStatus;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

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
public class HireRepositoryTest {

    @Autowired
    private HireService service;

    @Before
    public void setUp() throws Exception {
        service.deleteAll();
        Hire hire1 = new Hire(1L, 1L, 1L, 1L,
                LocalDateTime.of(2012, Month.of(12), 13, 14, 54),
                LocalDateTime.of(2012, Month.of(12), 18, 14, 54),
                HireStatus.Paid,
                new BigDecimal("105.0"),
                new BigDecimal(BigInteger.ZERO));
        Hire hire2 = new Hire(2L, 2L, 2L, 2L,
                LocalDateTime.of(2012, Month.of(12), 13, 14, 54),
                LocalDateTime.of(2012, Month.of(12), 18, 14, 54),
                HireStatus.Paid,
                new BigDecimal("556.15"),
                new BigDecimal(BigInteger.ZERO));
        Hire hire3 = new Hire(3L, 3L, 3L, 3L,
                LocalDateTime.of(2012, Month.of(12), 13, 14, 54),
                LocalDateTime.of(2012, Month.of(12), 18, 14, 54),
                HireStatus.Paid,
                new BigDecimal("487.25"),
                new BigDecimal(BigInteger.ZERO));

        service.save(hire1);
        service.save(hire2);
        service.save(hire3);
    }

    @Test
    public void findByCarIdAndStatus_OneHireEntryFound_ShouldReturnPaid() {
        Hire hire = service.findByCarIdAndStatus(1L, HireStatus.Paid);
        assertThat(hire.getStatus(), is(HireStatus.Paid));
    }

    @Test
    public void countBySellerId_OneHireEntryFound_ShouldReturn1() {
        Long howManyHiresWasMadeBySellerWithId2 = service.countBySellerId(2L);
        assertThat(howManyHiresWasMadeBySellerWithId2, is(1L));
    }

    @Test
    public void findSumOfEmployeeEarnings_OneSellerEntryFound_ShouldReturn105() {
        BigDecimal sumOfEmployeeEarnings = service.findSumOfEmployeeEarnings(1L);
        assertThat(sumOfEmployeeEarnings, is(new BigDecimal("105.0")));
    }

    @After
    public void after() throws Exception {
        service.deleteAll();
    }
}
