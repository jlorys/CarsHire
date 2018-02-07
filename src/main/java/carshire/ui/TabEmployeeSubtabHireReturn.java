package carshire.ui;

import carshire.CarService;
import carshire.HireService;
import carshire.domain.Car;
import carshire.domain.Hire;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tornadofx.control.DateTimePicker;

/**
 *
 * @author Kuba
 */
@Component
public class TabEmployeeSubtabHireReturn {

    @Autowired
    CarService carService;

    @Autowired
    HireService hireService;

    private CarsPresenter main;

    Car car;
    Hire hire;

    /**
     *
     * @param carsPresenter
     */
    public void init(CarsPresenter carsPresenter) {
        main = carsPresenter;
    }

    //Manager tab - CarsPresenter subtab
    @FXML
    TableView<Car> cars;
    @FXML
    TableColumn<Car, String> manufacturerColumn;
    @FXML
    TableColumn<Car, BigDecimal> pricePerDayAfterDiscountColumn;
    @FXML
    TableColumn<Car, Car.CarStatus> statusColumn;
    @FXML
    TableColumn<Car, String> registrationNumberColumn;
    @FXML
    TextField idCar;

    @FXML
    DateTimePicker hireReturnDate;
    @FXML
    TextField defaultInterest;

    LocalDateTime returnDate;
    LocalDateTime officialReturnDate;

    void configureTable() {
        manufacturerColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("manufacturer"));
        pricePerDayAfterDiscountColumn.setCellValueFactory(new PropertyValueFactory<Car, BigDecimal>("pricePerDayAfterDiscount"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Car, Car.CarStatus>("status"));
        registrationNumberColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("registrationNumber"));
    }

    /**
     * This method provides calculating default interest for late clients
     */
    @FXML
    public void btnCalculateDefaultInterest() {
        if (!idCar.getText().isEmpty()
                && hireReturnDate.getValue() != null) {

            hire = hireService.findByCarIdAndStatus(Long.parseLong(idCar.getText()), Hire.HireStatus.NotPaid);
            Optional<Hire> optional = Optional.ofNullable(hire);

            if (optional.isPresent()) {
                Integer hireReturnYear = hireReturnDate.getDateTimeValue().getYear();
                Integer hireReturnMonth = hireReturnDate.getDateTimeValue().getMonthValue();
                Integer hireReturnDay = hireReturnDate.getDateTimeValue().getDayOfMonth();
                Integer hireReturnHour = hireReturnDate.getDateTimeValue().getHour();
                Integer hireReturnMinute = hireReturnDate.getDateTimeValue().getMinute();
                returnDate = LocalDateTime.of(hireReturnYear, Month.of(hireReturnMonth), hireReturnDay, hireReturnHour, hireReturnMinute);

                officialReturnDate = hire.getHireEndDate();

                long delayDays = DAYS.between(officialReturnDate, returnDate);
                
                //ifTimeOfDayIsExceed - this value is checking is day is exceed or not, 1 if passed, 0 if not
                Integer ifTimeOfDayIsExceed = returnDate.toLocalTime().compareTo(officialReturnDate.toLocalTime());
                if(ifTimeOfDayIsExceed.equals(1)){delayDays++;}

                if (delayDays >= 1) {
                    car = carService.findById(Long.parseLong(idCar.getText()));
                    BigDecimal delayDaysBD = new BigDecimal(delayDays);
                    BigDecimal carPricePerDay = car.getPricePerDayAfterDiscount();
                    BigDecimal defaultInterestPrice = delayDaysBD
                            .multiply(carPricePerDay.multiply(new BigDecimal("3")));
                    
                    defaultInterest.setText(defaultInterestPrice.toString());
                    
                } else{
                    defaultInterest.setText("0");
                }
            } else {
                defaultInterest.clear();
            }

        }
    }

    /**
     * This method provides returning hire.
     * It checks if client is late or not, and if he or she is, it's add default interest
     */
    @FXML
    public void btnReturnHire() {
        if (!idCar.getText().isEmpty()
                && hireReturnDate.getValue() != null
                && !defaultInterest.getText().isEmpty()) {
            
            hire = hireService.findByCarIdAndStatus(Long.parseLong(idCar.getText()), Hire.HireStatus.NotPaid);
            hire.setStatus(Hire.HireStatus.Paid);
            hire.setDefaultInterest(new BigDecimal(defaultInterest.getText()));
            
            car = carService.findById(Long.parseLong(idCar.getText()));
            car.setStatus(Car.CarStatus.Avalible);
            
            main.deleteAllHiresViews();
            main.deleteAllCarsViews();

            hireService.save(hire);
            carService.save(car);
            
            main.addAllHiresViews();
            main.addAllCarsViews();
            
            btnClearHireCar();
        }
    }

    /**
     *
     */
    @FXML
    public void btnClearHireCar() {
        idCar.clear();
        defaultInterest.clear();
        hireReturnDate.getEditor().clear();
    }

    void fillTable() {
        configureTable();
        carService.findAllNotAvalibleCars().stream().forEach(v -> cars.getItems().add(v));
        cars.getSelectionModel().selectFirst();
    }

    void deleteAllViewRecords() {
        carService.findAllNotAvalibleCars().stream().forEach(v -> cars.getItems().remove(v));
    }

    void addAllViewRecords() {
        carService.findAllNotAvalibleCars().stream().forEach(v -> cars.getItems().add(v));
    }

    /**
     *
     */
    public void fillTextFields() {
        Car car = cars.getSelectionModel().getSelectedItem();
        idCar.setText(car.getId().toString());
    }
}
