package carshire.ui;

import carshire.ClientService;
import carshire.domain.Client;
import java.math.BigDecimal;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
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
    TextField id;
    @FXML
    Slider discountSlider;

    void configureTable() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
        discountClientColumn.setCellValueFactory(new PropertyValueFactory<Client, Integer>("discount"));
        discountSlider.setMin(0);
        discountSlider.setMax(99);
        discountSlider.setShowTickLabels(true);
        discountSlider.setShowTickMarks(true);
        discountSlider.setMajorTickUnit(4);
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
        discountSlider.setValue(client.getDiscount());
    }

    @FXML
    public void btnAddDiscountClient() {
        if (!id.getText().isEmpty()) {
            Client client = service.findById(Long.parseLong(id.getText()));

            Double discountSliderBar = discountSlider.getValue();
            Integer discountSliderValue = discountSliderBar.intValue();
            BigDecimal discountSliderValueFXbd = new BigDecimal(discountSliderValue.toString());

            client.setDiscount(discountSliderValue);

            main.deleteAllClientsViews();
            service.save(client);
            main.addAllClientsViews();
        }
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
