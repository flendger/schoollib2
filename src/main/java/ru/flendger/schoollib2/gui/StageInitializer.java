package ru.flendger.schoollib2.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<ClientFxApp.StageReadyEvent> {
    private final ApplicationContext applicationContext;

    public StageInitializer(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ClientFxApp.StageReadyEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/main.fxml"));
            loader.setControllerFactory(applicationContext::getBean);
            Parent root = loader.load();

            Stage stage = event.getStage();
            stage.setTitle("Школьная библиотека (2.0.0)");
            stage.setScene(new Scene(root, 1280, 600));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
