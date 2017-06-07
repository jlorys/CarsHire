package carshire.ui;

import carshire.CarService;
import carshire.domain.Car;
import java.math.BigDecimal;
import java.math.BigInteger;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kuba
 */
@Component
public class TabManagerSubtabCarsDiscount {

    @Autowired
    CarService service;

    private CarsPresenter main;

    /**
     *
     * @param carsPresenter
     */
    public void init(CarsPresenter carsPresenter) {
        main = carsPresenter;
    }

    //Manager tab - Cars subtab
    @FXML
    TableView<Car> cars;
    @FXML
    TableColumn<Car, BigInteger> pricePerDayColumn;
    @FXML
    TableColumn<Car, BigInteger> pricePerDayAfterDiscountColumn;
    @FXML
    TableColumn<Car, Integer> discountCarColumn;
    @FXML
    TableColumn<Car, String> registrationNumberColumn;
    @FXML
    TextField id;
    @FXML
    Slider discountSlider;

    void configureTable() {
        pricePerDayColumn.setCellValueFactory(new PropertyValueFactory<Car, BigInteger>("pricePerDay"));
        pricePerDayAfterDiscountColumn.setCellValueFactory(new PropertyValueFactory<Car, BigInteger>("pricePerDayAfterDiscount"));
        discountCarColumn.setCellValueFactory(new PropertyValueFactory<Car, Integer>("discount"));
        registrationNumberColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("registrationNumber"));
        discountSlider.setMin(0);
        discountSlider.setMax(99);
        discountSlider.setShowTickLabels(true);
        discountSlider.setShowTickMarks(true);
        discountSlider.setMajorTickUnit(4);
    }

    /**
     * This method is to add discount to the car
     */
    @FXML
    public void btnAddDiscountCar() {
        if (!id.getText().isEmpty()) {
            Car car = service.findById(Long.parseLong(id.getText()));

            Double discountSliderBar = discountSlider.getValue();
            Integer discountSliderValue = discountSliderBar.intValue();
            BigDecimal discountSliderValueFXbd = new BigDecimal(discountSliderValue.toString());

            car.setDiscount(discountSliderValue);

            BigDecimal discount = new BigDecimal(BigInteger.ONE).subtract(discountSliderValueFXbd.divide(new BigDecimal("100")));
            BigDecimal actualPricePerDay = car.getPricePerDay().multiply(discount);
            actualPricePerDay = actualPricePerDay.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            car.setPricePerDayAfterDiscount(actualPricePerDay);

            main.deleteAllCarsViews();
            service.save(car);
            main.addAllCarsViews();
        }
    }

    void fillTable() {
        configureTable();
        for (Car car : service.findAllCars()) {
            cars.getItems().add(car);
        }
        cars.getSelectionModel().selectFirst();
    }

    /**
     *
     */
    public void fillTextFields() {
        Car car = cars.getSelectionModel().getSelectedItem();
        id.setText(car.getId().toString());
        discountSlider.setValue(car.getDiscount());
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
