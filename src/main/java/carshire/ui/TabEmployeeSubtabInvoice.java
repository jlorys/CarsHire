package carshire.ui;

import carshire.HireService;
import carshire.domain.Hire;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TabEmployeeSubtabInvoice {

    @Autowired
    HireService service;

    private CarsPresenter main;

    public void init(CarsPresenter carsPresenter) {
        main = carsPresenter;
    }

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
    TableColumn<Hire, LocalDateTime> dateColumn;
    @FXML
    TableColumn<Hire, LocalDateTime> endDateColumn;
    @FXML
    TableColumn<Hire, Hire.HireStatus> statusColumn;
    @FXML
    TableColumn<Hire, BigDecimal> priceForHireColumn;
    @FXML
    TableColumn<Hire, BigDecimal> defaultInterestColumn;
    @FXML
    TextField clientId, carId, sellerId, date, endDate, status, priceForHire, defaultInterest;

    void configureTable() {
        clientIdColumn.setCellValueFactory(new PropertyValueFactory<Hire, Long>("clientId"));
        carIdColumn.setCellValueFactory(new PropertyValueFactory<Hire, Long>("carId"));
        sellerIdColumn.setCellValueFactory(new PropertyValueFactory<Hire, Long>("sellerId"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Hire, LocalDateTime>("hireDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<Hire, LocalDateTime>("hireEndDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Hire, Hire.HireStatus>("status"));
        priceForHireColumn.setCellValueFactory(new PropertyValueFactory<Hire, BigDecimal>("priceForHire"));
        defaultInterestColumn.setCellValueFactory(new PropertyValueFactory<Hire, BigDecimal>("defaultInterest"));
    }

    void fillTable() {
        configureTable();
        for (Hire hire : service.findAllHires()) {
            hires.getItems().add(hire);
        }
        hires.getSelectionModel().selectFirst();
    }

}
