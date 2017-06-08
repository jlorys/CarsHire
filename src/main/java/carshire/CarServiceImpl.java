package carshire;

import carshire.domain.Car;
import carshire.repository.CarRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    @Transactional
    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void delete(Car car) {
        carRepository.delete(car);
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public List<Car> findAllAvalibleCars() {
        return carRepository.findAllAvalibleCars();
    }

    @Override
    public List<Car> findAllNotAvalibleCars() {
        return carRepository.findAllNotAvalibleCars();
    }

    @Override
    public void deleteAll() {
        carRepository.deleteAll();
    }
}
