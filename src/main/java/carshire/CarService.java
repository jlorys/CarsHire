package carshire;

import carshire.domain.Car;
import java.util.List;

public interface CarService {

    public List<Car> findAllCars();

    public Car save(Car car);

    public void delete(Car car);
    
    public Car findById(final Long id);
}
