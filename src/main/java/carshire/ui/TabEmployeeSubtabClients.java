package carshire.ui;

import carshire.ClientService;
import carshire.domain.Client;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TabEmployeeSubtabClients {

    @Autowired
    ClientService service;

    private CarsPresenter main;

    public void init(CarsPresenter carsPresenter) {
        main = carsPresenter;
    }

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

    void configureTable() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
        eMailColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("eMail"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("city"));
        streetColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("street"));
        houseNumberColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("houseNumber"));
        discountClientColumn.setCellValueFactory(new PropertyValueFactory<Client, Integer>("discount"));
    }

    void fillTable() {
        configureTable();
        for (Client client : service.findAllClients()) {
            clients.getItems().add(client);
        }
        clients.getSelectionModel().selectFirst();
    }
}
