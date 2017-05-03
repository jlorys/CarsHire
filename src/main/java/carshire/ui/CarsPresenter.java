package carshire.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import carshire.domain.Car;
import carshire.CarService;
import carshire.ClientService;
import carshire.HireService;
import carshire.SellerService;
import carshire.domain.Car.CarStatus;
import carshire.domain.Client;
import carshire.domain.Hire;
import carshire.domain.Hire.HireStatus;
import carshire.domain.Seller;
import carshire.domain.Seller.Rights;
import java.time.LocalDateTime;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

@Component
public class CarsPresenter {

    //Main tabs - Admin, manager, employee
    @FXML
    private Tab adminTab;
    @FXML
    private Tab managerTab;
    @FXML
    private Tab employeeTab;

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
    TableColumn<Car, Float> pricePerDayColumn;
    @FXML
    TableColumn<Car, CarStatus> statusColumn;
    @FXML
    TableColumn<Car, Integer> discountCarColumn;
    @FXML
    TableColumn<Car, String> registrationNumberColumn;
    @FXML
    TextField manufacturer, model, yearOfManufacture, engineCapacity, vehicleMileage, pricePerDay, status, discountCar, registrationNumber;

    //Manager tab - Cars discount subtab
    @FXML
    TableView<Car> carsDiscount;
    @FXML
    TableColumn<Car, String> registrationNumberColumnDiscount;
    @FXML
    TableColumn<Car, Float> pricePerDayColumnDiscount;
    @FXML
    TableColumn<Car, Integer> discountCarColumnDiscount;
    @FXML
    TextField registrationNumberDiscount, pricePerDayDiscount, discountDiscount;

    //Manager tab - Client discount subtab
    @FXML
    TableView<Client> clientsDiscount;
    @FXML
    TableColumn<Client, String> firstNameColumnDiscount;
    @FXML
    TableColumn<Client, String> lastNameColumnDiscount;
    @FXML
    TableColumn<Client, Integer> discountClientColumnDiscount;
    @FXML
    TextField firstNameDiscount, lastNameDiscount, discountClientDiscount;

    //Employee tab - Client subtab
    @FXML
    TableView<Client> clients;
    @FXML
    TableColumn<Client, String> firstNameColumn;
    @FXML
    TableColumn<Client, String> lastNameColumn;
    @FXML
    TableColumn<Client, String> eMailColumn;
    @FXML
    TableColumn<Client, String> cityColumn;
    @FXML
    TableColumn<Client, String> streetColumn;
    @FXML
    TableColumn<Client, String> houseNumberColumn;
    @FXML
    TableColumn<Client, Integer> discountClientColumn;
    @FXML
    TextField firstName, lastName, eMail, city, street, houseNumber, discountClient;

    //Manager tab - Employee subtab
    @FXML
    TableView<Seller> employees;
    @FXML
    TableColumn<Seller, String> employeeFirstNameColumn;
    @FXML
    TableColumn<Seller, String> employeeLastNameColumn;
    @FXML
    TableColumn<Seller, String> employeeLoginColumn;
    @FXML
    TableColumn<Seller, String> employeeEMailColumn;
    @FXML
    TableColumn<Seller, String> employeePasswordColumn;
    @FXML
    TableColumn<Seller, String> employeeCityColumn;
    @FXML
    TableColumn<Seller, String> employeeStreetColumn;
    @FXML
    TableColumn<Seller, String> employeeHouseNumberColumn;
    @FXML
    TableColumn<Seller, Rights> employeeRightsColumn;
    @FXML
    TextField employeeFirstName, employeeLastName, employeeLogin, employeeEMail, employeePassword, employeeCity, employeeStreet, employeeHouseNumber, employeeRights;

    //Admin tab - Manager subtab
    @FXML
    TableView<Seller> managers;
    @FXML
    TableColumn<Seller, String> managerFirstNameColumn;
    @FXML
    TableColumn<Seller, String> managerLastNameColumn;
    @FXML
    TableColumn<Seller, String> managerLoginColumn;
    @FXML
    TableColumn<Seller, String> managerEMailColumn;
    @FXML
    TableColumn<Seller, String> managerPasswordColumn;
    @FXML
    TableColumn<Seller, String> managerCityColumn;
    @FXML
    TableColumn<Seller, String> managerStreetColumn;
    @FXML
    TableColumn<Seller, String> managerHouseNumberColumn;
    @FXML
    TableColumn<Seller, Rights> managerRightsColumn;
    @FXML
    TextField managerFirstName, managerLastName, managerLogin, managerEMail, managerPassword, managerCity, managerStreet, managerHouseNumber, managerRights;

    //Employee tab - Invoice subtab
    @FXML
    TableView<Hire> hires;
    @FXML
    TableColumn<Hire, Long> clientIdColumn;
    @FXML
    TableColumn<Hire, Long> carIdColumn;
    @FXML
    TableColumn<Hire, Long> sellerIdColumn;
    @FXML
    TableColumn<Hire, LocalDateTime> hireDateColumn;
    @FXML
    TableColumn<Hire, LocalDateTime> hireEndDateColumn;
    @FXML
    TableColumn<Hire, HireStatus> hireStatusColumn;
    @FXML
    TableColumn<Hire, Float> priceForHireColumn;
    @FXML
    TableColumn<Hire, Float> defaultInterestColumn;
    @FXML
    TextField clientId, carId, sellerId, hireDate, hireEndDate, hireStatus, priceForHire, defaultInterest;

    @Autowired
    CarService carTrackingService;

    @Autowired
    ClientService clientTrackingService;

    @Autowired
    SellerService sellerTrackingService;

    @Autowired
    HireService hireTrackingService;

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
            car.setStatus(CarStatus.Avalible);
            car.setDiscount(0);
            car.setRegistrationNumber(registrationNumber.getText());
            carTrackingService.save(car);
            cars.getItems().add(car);
            carsDiscount.getItems().add(car);


        }
    }

    @FXML
    public void btnDeleteCar() {
        Car car = cars.getSelectionModel().getSelectedItem();
        carTrackingService.delete(car);
        cars.getItems().remove(car);
        carsDiscount.getItems().remove(car);
    }

    @FXML
    public void initialize() {
        disableTabs();

        configureCarsTable();
        for (Car car : carTrackingService.findAllCars()) {
            cars.getItems().add(car);
        }
        cars.getSelectionModel().selectFirst();

        configureCarsDiscountTable();
        for (Car car : carTrackingService.findAllCars()) {
            carsDiscount.getItems().add(car);
        }
        carsDiscount.getSelectionModel().selectFirst();

        configureClientTable();
        for (Client client : clientTrackingService.findAllClients()) {
            clients.getItems().add(client);
        }
        clients.getSelectionModel().selectFirst();

        configureClientDiscountTable();
        for (Client client : clientTrackingService.findAllClients()) {
            clientsDiscount.getItems().add(client);
        }
        clientsDiscount.getSelectionModel().selectFirst();

        configureEmployeeTable();
        for (Seller seller : sellerTrackingService.findAllEmployess()) {
            employees.getItems().add(seller);
        }
        employees.getSelectionModel().selectFirst();

        configureManagerTable();
        for (Seller seller : sellerTrackingService.findAllManagers()) {
            managers.getItems().add(seller);
        }
        managers.getSelectionModel().selectFirst();

        configureHireTable();
        for (Hire hire : hireTrackingService.findAllHires()) {
            hires.getItems().add(hire);
        }
        hires.getSelectionModel().selectFirst();
    }

    private void configureCarsTable() {
        manufacturerColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("manufacturer"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
        yearOfManufactureColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("yearOfManufacture"));
        engineCapacityColumn.setCellValueFactory(new PropertyValueFactory<Car, Float>("engineCapacity"));
        vehicleMileageColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("vehicleMileage"));
        pricePerDayColumn.setCellValueFactory(new PropertyValueFactory<Car, Float>("pricePerDay"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Car, CarStatus>("status"));
        discountCarColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("discount"));
        registrationNumberColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("registrationNumber"));
    }

    private void configureCarsDiscountTable() {
        registrationNumberColumnDiscount.setCellValueFactory(new PropertyValueFactory<Car, String>("registrationNumber"));
        pricePerDayColumnDiscount.setCellValueFactory(new PropertyValueFactory<Car, Float>("pricePerDay"));
        discountCarColumnDiscount.setCellValueFactory(new PropertyValueFactory<Car, Integer>("discount"));
    }

    private void configureClientTable() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
        eMailColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("eMail"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("city"));
        streetColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("street"));
        houseNumberColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("houseNumber"));
        discountClientColumn.setCellValueFactory(new PropertyValueFactory<Client, Integer>("discount"));
    }

    private void configureClientDiscountTable() {
        firstNameColumnDiscount.setCellValueFactory(new PropertyValueFactory<Client, String>("firstName"));
        lastNameColumnDiscount.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
        discountClientColumnDiscount.setCellValueFactory(new PropertyValueFactory<Client, Integer>("discount"));
    }

    private void configureEmployeeTable() {
        employeeFirstNameColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("firstName"));
        employeeLastNameColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("lastName"));
        employeeLoginColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("login"));
        employeeEMailColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("eMail"));
        employeePasswordColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("password"));
        employeeCityColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("city"));
        employeeStreetColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("street"));
        employeeHouseNumberColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("houseNumber"));
        employeeRightsColumn.setCellValueFactory(new PropertyValueFactory<Seller, Rights>("rights"));
    }

    private void configureManagerTable() {
        managerFirstNameColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("firstName"));
        managerLastNameColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("lastName"));
        managerLoginColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("login"));
        managerEMailColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("eMail"));
        managerPasswordColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("password"));
        managerCityColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("city"));
        managerStreetColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("street"));
        managerHouseNumberColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("houseNumber"));
        managerRightsColumn.setCellValueFactory(new PropertyValueFactory<Seller, Rights>("rights"));
    }

    private void configureHireTable() {
        clientIdColumn.setCellValueFactory(new PropertyValueFactory<Hire, Long>("clientId"));
        carIdColumn.setCellValueFactory(new PropertyValueFactory<Hire, Long>("carId"));
        sellerIdColumn.setCellValueFactory(new PropertyValueFactory<Hire, Long>("sellerId"));
        hireDateColumn.setCellValueFactory(new PropertyValueFactory<Hire, LocalDateTime>("hireDate"));
        hireEndDateColumn.setCellValueFactory(new PropertyValueFactory<Hire, LocalDateTime>("hireEndDate"));
        hireStatusColumn.setCellValueFactory(new PropertyValueFactory<Hire, HireStatus>("status"));
        priceForHireColumn.setCellValueFactory(new PropertyValueFactory<Hire, Float>("priceForHire"));
        defaultInterestColumn.setCellValueFactory(new PropertyValueFactory<Hire, Float>("defaultInterest"));
    }

    @FXML
    private void disableTabs() {
        adminTab.setDisable(true);
        managerTab.setDisable(true);
        employeeTab.setDisable(true);
    }

    @FXML
    public void btnEnableAdmin() {
        adminTab.setDisable(false);
        managerTab.setDisable(false);
        employeeTab.setDisable(false);
    }

    @FXML
    public void btnEnableManager() {
        adminTab.setDisable(true);
        managerTab.setDisable(false);
        employeeTab.setDisable(false);
    }

    @FXML
    public void btnEnableEmployee() {
        adminTab.setDisable(true);
        managerTab.setDisable(true);
        employeeTab.setDisable(false);
    }
}
