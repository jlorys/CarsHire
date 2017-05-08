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
    TextField id, firstName, lastName, login, eMail, password, city, street, houseNumber;
    
    @FXML
    public void btnAddManager() {
        if (firstName.getText().isEmpty() || lastName.getText().isEmpty()) {

        } else {
            Seller seller = new Seller();
            if (!id.getText().isEmpty()) {
                seller.setId(Long.parseLong(id.getText()));
            }
            seller.setFirstName(firstName.getText());
            seller.setLastName(lastName.getText());
            seller.setLogin(login.getText());
            seller.setEMail(eMail.getText());
            seller.setPassword(password.getText());
            seller.setCity(city.getText());
            seller.setStreet(street.getText());
            seller.setHouseNumber(houseNumber.getText());
            seller.setRights(Seller.Rights.Manager);

            main.deleteAllSellersViews();
            service.save(seller);
            main.addAllSellersViews();
        }
    }

    @FXML
    public void btnDeleteManager() {
        if (sellers.getSelectionModel().isEmpty()) {

        } else {
            Seller seller = sellers.getSelectionModel().getSelectedItem();
            main.deleteAllSellersViews();
            service.delete(seller);
            main.addAllSellersViews();
            btnClearManager();
        }
    }

    @FXML
    public void btnClearManager() {
        id.clear();
        firstName.clear();
        lastName.clear();
        login.clear();
        eMail.clear();
        password.clear();
        city.clear();
        street.clear();
        houseNumber.clear();
        street.clear();
    }

    public void fillTextFields() {
        Seller seller = sellers.getSelectionModel().getSelectedItem();
        id.setText(seller.getId().toString());
        firstName.setText(seller.getFirstName());
        lastName.setText(seller.getLastName());
        login.setText(seller.getLogin());
        eMail.setText(seller.getEMail());
        password.setText(seller.getPassword());
        city.setText(seller.getCity());
        street.setText(seller.getStreet());
        houseNumber.setText(seller.getHouseNumber());
    }

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
    
    void deleteAllViewRecords() {
        for (Seller seller : service.findAllManagers()) {
            sellers.getItems().remove(seller);
        }
    }

    void addAllViewRecords() {
        for (Seller seller : service.findAllManagers()) {
            sellers.getItems().add(seller);
        }
    }
}
