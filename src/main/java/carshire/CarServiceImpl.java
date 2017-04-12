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

	public List<Car> findAllCars() {
		return carRepository.findAll();
	}

	@Transactional
	public Car save(Car car) {
		return carRepository.save(car);
	}
}