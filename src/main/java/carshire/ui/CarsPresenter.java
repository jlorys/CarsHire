package carshire.ui;

import javafx.fxml.FXML;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;

@Component
public class CarsPresenter {

    TabManagerSubtabCars tabManagerSubtabCars;
    TabManagerSubtabCarsDiscount tabManagerSubtabCarsDiscount;
    TabEmployeeSubtabClients tabEmployeeSubtabClients;
    TabManagerSubtabClientsDiscount tabManagerSubtabClientsDiscount;
    TabManagerSubtabEmployees tabManagerSubtabEmployees;
    TabAdminSubtabManagers tabAdminSubtabManagers;
    TabEmployeeSubtabInvoice tabEmployeeSubtabInvoice;

    @Autowired
    public CarsPresenter(TabManagerSubtabCars tabManagerSubtabCars,
            TabManagerSubtabCarsDiscount tabManagerSubtabCarsDiscount,
            TabEmployeeSubtabClients tabEmployeeSubtabClients,
            TabManagerSubtabClientsDiscount tabManagerSubtabClientsDiscount,
            TabManagerSubtabEmployees tabManagerSubtabEmployees,
            TabAdminSubtabManagers tabAdminSubtabManagers,
            TabEmployeeSubtabInvoice tabEmployeeSubtabInvoice) {
        this.tabManagerSubtabCars = tabManagerSubtabCars;
        this.tabManagerSubtabCarsDiscount = tabManagerSubtabCarsDiscount;
        this.tabEmployeeSubtabClients = tabEmployeeSubtabClients;
        this.tabManagerSubtabClientsDiscount = tabManagerSubtabClientsDiscount;
        this.tabManagerSubtabEmployees = tabManagerSubtabEmployees;
        this.tabAdminSubtabManagers = tabAdminSubtabManagers;
        this.tabEmployeeSubtabInvoice = tabEmployeeSubtabInvoice;
    }

    //Main tabs - Admin, manager, employee
    @FXML
    private Tab adminTab;
    @FXML
    private Tab managerTab;
    @FXML
    private Tab employeeTab;

    @FXML
    public void initialize() {
        disableTabs();

        tabManagerSubtabCars.init(this);
        tabManagerSubtabCarsDiscount.init(this);
        fillCarsTables();

        tabEmployeeSubtabClients.init(this);
        tabManagerSubtabClientsDiscount.init(this);
        fillClientsTables();

        tabManagerSubtabEmployees.init(this);
        tabAdminSubtabManagers.init(this);
        fillSellersTables();

        tabEmployeeSubtabInvoice.init(this);
        fillHiresTables();

        tabManagerSubtabCars.cars.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                tabManagerSubtabCars.fillTextFields();
            }
        });
    }

    void fillHiresTables() {
        tabEmployeeSubtabInvoice.fillTable();
    }

    void fillSellersTables() {
        tabManagerSubtabEmployees.fillTable();
        tabAdminSubtabManagers.fillTable();
    }

    void fillClientsTables() {
        tabEmployeeSubtabClients.fillTable();
        tabManagerSubtabClientsDiscount.fillTable();
    }

    void fillCarsTables() {
        tabManagerSubtabCars.fillTable();
        tabManagerSubtabCarsDiscount.fillTable();
    }

    void deleteAllCarsViews() {
        tabManagerSubtabCars.deleteAllViewRecords();
        tabManagerSubtabCarsDiscount.deleteAllViewRecords();
    }

    void addAllCarsViews() {
        tabManagerSubtabCars.addAllViewRecords();
        tabManagerSubtabCarsDiscount.addAllViewRecords();
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
