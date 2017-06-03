package carshire.ui;

import carshire.ClientService;
import carshire.domain.Client;
import carshire.domain.Seller;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TabManagerSubtabClientsDiscount {

    @Autowired
    ClientService service;

    private CarsPresenter main;

    public void init(CarsPresenter carsPresenter) {
        main = carsPresenter;
    }

    //Manager tab - Client discount subtab
    @FXML
    TableView<Client> clients;
    @FXML
    TableColumn<Client, String> firstNameColumn;
    @FXML
    TableColumn<Client, String> lastNameColumn;
    @FXML
    TableColumn<Client, Integer> discountClientColumn;
    @FXML
    TextField id, discountClient;

    void configureTable() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
        discountClientColumn.setCellValueFactory(new PropertyValueFactory<Client, Integer>("discount"));
    }

    void fillTable() {
        configureTable();
        for (Client client : service.findAllClients()) {
            clients.getItems().add(client);
        }
        clients.getSelectionModel().selectFirst();
    }

    public void fillTextFields() {
        Client client = clients.getSelectionModel().getSelectedItem();
        id.setText(client.getId().toString());
        discountClient.setText(client.getDiscount().toString());
    }

    @FXML
    public void btnAddDiscountClient() {
            Client client = new Client();
            client = service.findOne(Long.parseLong(id.getText()));
            
            client.setDiscount(Integer.parseInt(discountClient.getText()));

            main.deleteAllClientsViews();
            service.save(client);
            main.addAllClientsViews();
    }
    
    void deleteAllViewRecords() {
        for (Client client : service.findAllClients()) {
            clients.getItems().remove(client);
        }
    }

    void addAllViewRecords() {
        for (Client client : service.findAllClients()) {
            clients.getItems().add(client);
        }
    }
}
