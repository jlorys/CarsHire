package carshire.ui;

import carshire.SellerService;
import carshire.domain.Seller;
import carshire.domain.Seller.Rights;
import java.util.Optional;
import javafx.fxml.FXML;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
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
    TabEmployeeSubtabHire tabEmployeeSubtabHire;
    SellerService sellerService;

    @Autowired
    public CarsPresenter(TabManagerSubtabCars tabManagerSubtabCars,
            TabManagerSubtabCarsDiscount tabManagerSubtabCarsDiscount,
            TabEmployeeSubtabClients tabEmployeeSubtabClients,
            TabManagerSubtabClientsDiscount tabManagerSubtabClientsDiscount,
            TabManagerSubtabEmployees tabManagerSubtabEmployees,
            TabAdminSubtabManagers tabAdminSubtabManagers,
            TabEmployeeSubtabInvoice tabEmployeeSubtabInvoice,
            TabEmployeeSubtabHire tabEmployeeSubtabHire,
            SellerService sellerService) {
        this.tabManagerSubtabCars = tabManagerSubtabCars;
        this.tabManagerSubtabCarsDiscount = tabManagerSubtabCarsDiscount;
        this.tabEmployeeSubtabClients = tabEmployeeSubtabClients;
        this.tabManagerSubtabClientsDiscount = tabManagerSubtabClientsDiscount;
        this.tabManagerSubtabEmployees = tabManagerSubtabEmployees;
        this.tabAdminSubtabManagers = tabAdminSubtabManagers;
        this.tabEmployeeSubtabInvoice = tabEmployeeSubtabInvoice;
        this.tabEmployeeSubtabHire = tabEmployeeSubtabHire;
        this.sellerService = sellerService;
    }

    //Main tabs - Admin, manager, employee
    @FXML
    private Tab adminTab;
    @FXML
    private Tab managerTab;
    @FXML
    private Tab employeeTab;

    @FXML
    TextField login;
    @FXML
    TextField password;
    @FXML
    Label info;
    
    Seller loggedSeller;

    @FXML
    public void initialize() {
        disableTabs();
        login.setText("wiertel");
        password.setText("mw");

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
        tabEmployeeSubtabHire.init(this);
        fillHiresTables();

        tabManagerSubtabCars.cars.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                tabManagerSubtabCars.fillTextFields();
            }
        });

        tabAdminSubtabManagers.sellers.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                tabAdminSubtabManagers.fillTextFields();
            }
        });

        tabManagerSubtabEmployees.sellers.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                tabManagerSubtabEmployees.fillTextFields();
            }
        });

        tabManagerSubtabClientsDiscount.clients.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                tabManagerSubtabClientsDiscount.fillTextFields();
            }
        });

        tabManagerSubtabCarsDiscount.cars.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                tabManagerSubtabCarsDiscount.fillTextFields();
            }
        });

        tabEmployeeSubtabClients.clients.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                tabEmployeeSubtabClients.fillTextFields();
            }
        });

        tabEmployeeSubtabHire.cars.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                tabEmployeeSubtabHire.fillCarTextFields();
            }
        });

        tabEmployeeSubtabHire.clients.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                tabEmployeeSubtabHire.fillClientTextFields();
            }
        });
    }

    void fillHiresTables() {
        tabEmployeeSubtabInvoice.fillTable();
        tabEmployeeSubtabHire.fillTable();
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

    void deleteAllHiresViews() {
        tabEmployeeSubtabInvoice.deleteAllViewRecords();
    }

    void deleteAllSellersViews() {
        tabAdminSubtabManagers.deleteAllViewRecords();
        tabManagerSubtabEmployees.deleteAllViewRecords();
    }

    void deleteAllCarsViews() {
        tabManagerSubtabCars.deleteAllViewRecords();
        tabManagerSubtabCarsDiscount.deleteAllViewRecords();
        tabEmployeeSubtabHire.deleteAllViewRecords();
    }

    void deleteAllClientsViews() {
        tabManagerSubtabClientsDiscount.deleteAllViewRecords();
        tabEmployeeSubtabClients.deleteAllViewRecords();
        tabEmployeeSubtabHire.deleteAllViewRecords();
    }

    void addAllSellersViews() {
        tabAdminSubtabManagers.addAllViewRecords();
        tabManagerSubtabEmployees.addAllViewRecords();
    }

    void addAllHiresViews() {
        tabEmployeeSubtabInvoice.addAllViewRecords();
    }

    void addAllCarsViews() {
        tabManagerSubtabCars.addAllViewRecords();
        tabManagerSubtabCarsDiscount.addAllViewRecords();
        tabEmployeeSubtabHire.addAllViewRecords();
    }

    void addAllClientsViews() {
        tabManagerSubtabClientsDiscount.addAllViewRecords();
        tabEmployeeSubtabClients.addAllViewRecords();
        tabEmployeeSubtabHire.addAllViewRecords();
    }

    @FXML
    public void btnLogin() {
        if (!login.getText().isEmpty()
                && !password.getText().isEmpty()) {

            loggedSeller = sellerService.findByLogin(login.getText());
            Optional<Seller> sellerOpt = Optional.ofNullable(loggedSeller);

            if (sellerOpt.isPresent() && loggedSeller.getPassword().equals(password.getText())) {
                if (loggedSeller.getRights() == Rights.Admin) {
                    btnEnableAdmin();
                } else if (loggedSeller.getRights() == Rights.Manager) {
                    btnEnableManager();
                } else if (loggedSeller.getRights() == Rights.Employee) {
                    btnEnableEmployee();
                }
                info.setText("Zalogowany jako: " + loggedSeller.getFirstName() + " " + loggedSeller.getLastName());
            } else {
                info.setText("Nieudana próba");
            }
        } else {
            info.setText("Nieudana próba");
        }
    }

    @FXML
    public void disableTabs() {
        adminTab.setDisable(true);
        managerTab.setDisable(true);
        employeeTab.setDisable(true);
        info.setText("");
    }

    private void btnEnableAdmin() {
        adminTab.setDisable(false);
        managerTab.setDisable(false);
        employeeTab.setDisable(false);
    }

    private void btnEnableManager() {
        adminTab.setDisable(true);
        managerTab.setDisable(false);
        employeeTab.setDisable(false);
    }

    private void btnEnableEmployee() {
        adminTab.setDisable(true);
        managerTab.setDisable(true);
        employeeTab.setDisable(false);
    }
}
