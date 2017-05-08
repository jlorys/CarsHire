package carshire.ui;

import carshire.CarService;
import carshire.domain.Car;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TabManagerSubtabCarsDiscount {

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
    TableColumn<Car, Float> pricePerDayColumn;
    @FXML
    TableColumn<Car, Integer> discountCarColumn;
    @FXML
    TableColumn<Car, String> registrationNumberColumn;
    @FXML
    TextField id, manufacturer, model, yearOfManufacture, engineCapacity, vehicleMileage, pricePerDay, status, discountCar, registrationNumber;

    void configureTable() {
        pricePerDayColumn.setCellValueFactory(new PropertyValueFactory<Car, Float>("pricePerDay"));
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
