package carshire.ui;

import carshire.SellerService;
import carshire.domain.Seller;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TabAdminSubtabManagers {

    @Autowired
    SellerService service;

    private CarsPresenter main;

    public void init(CarsPresenter carsPresenter) {
        main = carsPresenter;
    }

    //Admin tab - Manager subtab
    @FXML
    TableView<Seller> sellers;
    @FXML
    TableColumn<Seller, String> firstNameColumn;
    @FXML
    TableColumn<Seller, String> lastNameColumn;
    @FXML
    TableColumn<Seller, String> loginColumn;
    @FXML
    TableColumn<Seller, String> eMailColumn;
    @FXML
    TableColumn<Seller, String> passwordColumn;
    @FXML
    TableColumn<Seller, String> cityColumn;
    @FXML
    TableColumn<Seller, String> streetColumn;
    @FXML
    TableColumn<Seller, String> houseNumberColumn;
    @FXML
    TableColumn<Seller, Seller.Rights> rightsColumn;
    @FXML
    TextField firstName, lastName, login, eMail, password, city, street, houseNumber, rights;

    void configureTable() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("lastName"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("login"));
        eMailColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("eMail"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("password"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("city"));
        streetColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("street"));
        houseNumberColumn.setCellValueFactory(new PropertyValueFactory<Seller, String>("houseNumber"));
        rightsColumn.setCellValueFactory(new PropertyValueFactory<Seller, Seller.Rights>("rights"));
    }

    void fillTable() {
        configureTable();
        for (Seller seller : service.findAllManagers()) {
            sellers.getItems().add(seller);
        }
        sellers.getSelectionModel().selectFirst();
    }
}
