package carshire.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import carshire.domain.Car;
import carshire.CarService;
import carshire.domain.Car.Status;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

@Component
public class CarsPresenter {

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
    TableColumn<Car, Float> pricePerDayColumn;
    @FXML
    TableColumn<Car, Status> statusColumn;
    @FXML
    TableColumn<Car, Integer> discountColumn;
    @FXML
    TableColumn<Car, String> registrationNumberColumn;

    @FXML
    TextField manufacturer, model, yearOfManufacture, engineCapacity, vehicleMileage, pricePerDay, status, discount, registrationNumber;

    @FXML
    private Tab adminTab;
    @FXML
    private Tab kierownikTab;
    @FXML
    private Tab pracownikTab;

    @Autowired
    CarService carTrackingService;

    @FXML
    public void btnAddCar() {
        if (manufacturer.getText().equals("") || model.getText().equals("")) {

        } else {
            Car car = new Car();
            car.setManufacturer(manufacturer.getText());
            car.setModel(model.getText());
            car.setYearOfManufacture(Integer.parseInt(yearOfManufacture.getText()));
            car.setEngineCapacity(Float.parseFloat(engineCapacity.getText()));
            car.setVehicleMileage(Integer.parseInt(vehicleMileage.getText()));
            car.setPricePerDay(Float.parseFloat(pricePerDay.getText()));
            car.setStatus(Status.Avalible);
            car.setDiscount(0);
            car.setRegistrationNumber(registrationNumber.getText());
            carTrackingService.save(car);
            cars.getItems().add(car);

        }
    }

    @FXML
    public void btnDeleteCar() {
        Car car = cars.getSelectionModel().getSelectedItem();
        carTrackingService.delete(car);
        cars.getItems().remove(car);
    }

    @FXML
    public void btnEnableAdmin() {
        adminTab.setDisable(false);
        kierownikTab.setDisable(false);
        pracownikTab.setDisable(false);
    }

    @FXML
    public void btnEnableKierownik() {
        adminTab.setDisable(true);
        kierownikTab.setDisable(false);
        pracownikTab.setDisable(false);
    }

    @FXML
    public void btnEnablePracownik() {
        adminTab.setDisable(true);
        kierownikTab.setDisable(true);
        pracownikTab.setDisable(false);
    }

    @FXML
    public void initialize() {
        disableTabs();
        configureCarsTable();

        for (Car car : carTrackingService.findAllCars()) {
            cars.getItems().add(car);
        }

        cars.getSelectionModel().selectFirst();
    }

    private void configureCarsTable() {

        manufacturerColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("manufacturer"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
        yearOfManufactureColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("yearOfManufacture"));
        engineCapacityColumn.setCellValueFactory(new PropertyValueFactory<Car, Float>("engineCapacity"));
        vehicleMileageColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("vehicleMileage"));
        pricePerDayColumn.setCellValueFactory(new PropertyValueFactory<Car, Float>("pricePerDay"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Car, Status>("status"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("discount"));
        registrationNumberColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("registrationNumber"));
    }

    @FXML
    private void disableTabs() {
        adminTab.setDisable(true);
        kierownikTab.setDisable(true);
        pracownikTab.setDisable(true);

    }
}
