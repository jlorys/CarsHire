package carshire.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.springframework.stereotype.Component;

import carshire.domain.Car;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

@Component
public class CarsPresenter {

    @FXML
    TableView<Car> carsTable;

    @FXML
    TableColumn<Car, String> carManufacturerColumn;
    @FXML
    TableColumn<Car, String> carNameColumn;

    @FXML
    TextField manufacturer;
    @FXML
    TextField name;

    @FXML
    private Tab adminTab;
    @FXML
    private Tab kierownikTab;
    @FXML
    private Tab pracownikTab;

    @FXML
    public void btnAddCar() {
        if (manufacturer.getText().equals("") || name.getText().equals("")) {
        } else {
             //Add car
        }
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
    }


    @FXML
    private void disableTabs() {
        adminTab.setDisable(true);
        kierownikTab.setDisable(true);
        pracownikTab.setDisable(true);

    }
}
