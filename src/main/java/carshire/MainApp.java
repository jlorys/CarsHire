package carshire;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import carshire.ui.CarsView;

@SpringBootApplication
public class MainApp extends Application {

    private static String[] savedCommandArgs;

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() throws Exception {
        applicationContext = SpringApplication.run(getClass(), savedCommandArgs);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        applicationContext.close();
    }

    protected static void launchApp(Class<? extends MainApp> appClass, String[] args) {
        MainApp.savedCommandArgs = args;
        Application.launch(appClass, args);
    }

    //This is set in application.properties, if not title is default
    @Value("${app.ui.title:default}")
    private String windowTitle;

    @Autowired
    CarsView carsView;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(carsView.getView()));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

    }

    public static void main(String[] args) {
        launchApp(MainApp.class, args);
    }
}
