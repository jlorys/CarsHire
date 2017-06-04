package carshire.ui;

import carshire.CarService;
import carshire.domain.Car;
import java.math.BigDecimal;
import java.math.BigInteger;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TabManagerSubtabCars {

    @Autowired
    CarService service;

    private CarsPresenter main;

    public void init(CarsPresenter carsPresenter) {
        main = carsPresenter;
    }

    //Manager tab - Cars subtab
    @FXML
    TableView<Car> cars;
    @FXML
    TableColumn<Car, String> manufacturerColumn;
    @FXML
    TableColumn<Car, String> modelColumn;
    @FXML
    TableColumn<Car, Integer> yearOfManufactureColumn;
    @FXML
    TableColumn<Car, Float> engineCapacityColumn;
    @FXML
    TableColumn<Car, Integer> vehicleMileageColumn;
    @FXML
    TableColumn<Car, BigDecimal> pricePerDayColumn;
    @FXML
    TableColumn<Car, BigInteger> pricePerDayAfterDiscountColumn;
    @FXML
    TableColumn<Car, Car.CarStatus> statusColumn;
    @FXML
    TableColumn<Car, Integer> discountCarColumn;
    @FXML
    TableColumn<Car, String> registrationNumberColumn;
    @FXML
    TextField id, manufacturer, model, yearOfManufacture, engineCapacity, vehicleMileage, pricePerDay, status, discountCar, registrationNumber;

    @FXML
    public void btnAddCar() {
        if (!manufacturer.getText().isEmpty() 
                || !model.getText().isEmpty() 
                || !pricePerDay.getText().isEmpty()) {

            Car car = new Car();
            
            if (!id.getText().isEmpty()) {
                car = service.findById(Long.parseLong(id.getText()));
            }else {
                //In case of creating new car
                car.setStatus(Car.CarStatus.Avalible);
                car.setDiscount(0);
                car.setPricePerDayAfterDiscount(new BigDecimal(pricePerDay.getText()));
            }

            car.setManufacturer(manufacturer.getText());
            car.setModel(model.getText());
            car.setYearOfManufacture(Integer.parseInt(yearOfManufacture.getText()));
            car.setEngineCapacity(Float.parseFloat(engineCapacity.getText()));
            car.setVehicleMileage(Integer.parseInt(vehicleMileage.getText()));
            car.setPricePerDay(new BigDecimal(pricePerDay.getText()));
            car.setRegistrationNumber(registrationNumber.getText());
            
            main.deleteAllCarsViews();
            service.save(car);
            main.addAllCarsViews();
        }
    }

    @FXML
    public void btnDeleteCar() {
        if (cars.getSelectionModel().isEmpty()) {

        } else {
            Car car = cars.getSelectionModel().getSelectedItem();
            main.deleteAllCarsViews();
            service.delete(car);
            main.addAllCarsViews();
            btnClearCar();
        }
    }

    @FXML
    public void btnClearCar() {
        id.clear();
        manufacturer.clear();
        model.clear();
        yearOfManufacture.clear();
        engineCapacity.clear();
        vehicleMileage.clear();
        pricePerDay.clear();
        registrationNumber.clear();
    }

    public void fillTextFields() {
        Car car = cars.getSelectionModel().getSelectedItem();
        id.setText(car.getId().toString());
        manufacturer.setText(car.getManufacturer());
        model.setText(car.getModel());
        yearOfManufacture.setText(car.getYearOfManufacture().toString());
        engineCapacity.setText(car.getEngineCapacity().toString());
        vehicleMileage.setText(car.getVehicleMileage().toString());
        pricePerDay.setText(car.getPricePerDay().toString());
        registrationNumber.setText(car.getRegistrationNumber());
    }

    void configureTable() {
        manufacturerColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("manufacturer"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
        yearOfManufactureColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("yearOfManufacture"));
        engineCapacityColumn.setCellValueFactory(new PropertyValueFactory<Car, Float>("engineCapacity"));
        vehicleMileageColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("vehicleMileage"));
        pricePerDayColumn.setCellValueFactory(new PropertyValueFactory<Car, BigDecimal>("pricePerDay"));
        pricePerDayAfterDiscountColumn.setCellValueFactory(new PropertyValueFactory<Car, BigInteger>("pricePerDayAfterDiscount"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Car, Car.CarStatus>("status"));
        discountCarColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("discount"));
        registrationNumberColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("registrationNumber"));
    }

    void fillTable() {
        configureTable();
        for (Car car : service.findAllCars()) {
            cars.getItems().add(car);
        }
        cars.getSelectionModel().selectFirst();
    }

    void deleteAllViewRecords() {
        for (Car car : service.findAllCars()) {
            cars.getItems().remove(car);
        }
    }

    void addAllViewRecords() {
        for (Car car : service.findAllCars()) {
            cars.getItems().add(car);
        }
    }

}
