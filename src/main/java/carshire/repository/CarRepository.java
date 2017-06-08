package carshire.repository;

import carshire.domain.Car;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Kuba
 */
public interface CarRepository extends JpaRepository<Car, Long> {

    /**
     *
     * @param id
     * @return
     */
    Car findById(Long id);
    
    /**
     *
     */
    public static final String FIND_AVALIBLE_CARS = "select * from car where status = 'Avalible'";

    /**
     *
     */
    public static final String FIND_NOT_AVALIBLE_CARS = "select * from car where status = 'NotAvalible'";

    /**
     *
     * @return
     */
    @Query(nativeQuery = true, value = FIND_AVALIBLE_CARS)
    List<Car> findAllAvalibleCars();

    /**
     *
     * @return
     */
    @Query(nativeQuery = true, value = FIND_NOT_AVALIBLE_CARS)
    List<Car> findAllNotAvalibleCars();
}
