package carshire;

import carshire.domain.Car;
import java.util.List;

/**
 *
 * @author Kuba
 */
public interface CarService {

    /**
     *
     * @return
     */
    public List<Car> findAllCars();

    /**
     *
     * @param car
     * @return
     */
    public Car save(Car car);

    /**
     *
     * @param car
     */
    public void delete(Car car);
    
    /**
     *
     * @param id
     * @return
     */
    public Car findById(final Long id);
    
    /**
     *
     * @return
     */
    public List<Car> findAllAvalibleCars();
    
    /**
     *
     * @return
     */
    public List<Car> findAllNotAvalibleCars();
}
