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

/**
 *
 * @author Kuba
 */
@Component
public class TabEmployeeSubtabClients {

    @Autowired
    ClientService service;

    private CarsPresenter main;

    /**
     *
     * @param carsPresenter
     */
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
    TextField id, firstName, lastName, eMail, city, street, houseNumber;

    void configureTable() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
        eMailColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("eMail"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("city"));
        streetColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("street"));
        houseNumberColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("houseNumber"));
        discountClientColumn.setCellValueFactory(new PropertyValueFactory<Client, Integer>("discount"));
    }

    /**
     *
     */
    @FXML
    public void btnAddClient() {
        if (!firstName.getText().isEmpty() || !lastName.getText().isEmpty()) {
            Client client = new Client();
            if (!id.getText().isEmpty()) {
                client = service.findById(Long.parseLong(id.getText()));
            } else {
                client.setDiscount(0);
            }
            client.setFirstName(firstName.getText());
            client.setLastName(lastName.getText());
            client.setEMail(eMail.getText());
            client.setCity(city.getText());
            client.setStreet(street.getText());
            client.setHouseNumber(houseNumber.getText());

            main.deleteAllClientsViews();
            service.save(client);
            main.addAllClientsViews();
        }
    }

    /**
     *
     */
    @FXML
    public void btnDeleteClient() {
        if (clients.getSelectionModel().isEmpty()) {

        } else {
            Client client = clients.getSelectionModel().getSelectedItem();
            main.deleteAllClientsViews();
            service.delete(client);
            main.addAllClientsViews();
            btnClearClient();
        }
    }

    /**
     *
     */
    @FXML
    public void btnClearClient() {
        id.clear();
        firstName.clear();
        lastName.clear();
        eMail.clear();
        city.clear();
        street.clear();
        houseNumber.clear();
        street.clear();
    }

    /**
     *
     */
    public void fillTextFields() {
        Client client = clients.getSelectionModel().getSelectedItem();
        id.setText(client.getId().toString());
        firstName.setText(client.getFirstName());
        lastName.setText(client.getLastName());
        eMail.setText(client.getEMail());
        city.setText(client.getCity());
        street.setText(client.getStreet());
        houseNumber.setText(client.getHouseNumber());
    }

    void fillTable() {
        configureTable();
        service.findAllClients().stream().forEach(v -> clients.getItems().add(v));
        clients.getSelectionModel().selectFirst();
    }

    void deleteAllViewRecords() {
        service.findAllClients().stream().forEach(v -> clients.getItems().remove(v));
    }

    void addAllViewRecords() {
        service.findAllClients().stream().forEach(v -> clients.getItems().add(v));
    }
}
