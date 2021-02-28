package ru.flendger.schoollib2.gui.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.flendger.schoollib2.gui.forms.reports.LocationStorageReportController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class ReportFormUtils {
    private final ApplicationContext applicationContext;

    private HashMap<Class<?>, String> resourceMap;

    @PostConstruct
    private void init() {
        resourceMap = new HashMap<>();
        resourceMap.put(LocationStorageReportController.class, "/gui/report/location_storage_report.fxml");
    }


    private FXMLLoader getLoader(Class<?> clazz) {
        FXMLLoader fxmlLoader = new FXMLLoader(FormUtils.class.
                getResource(resourceMap.get(clazz)));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        return fxmlLoader;
    }

    public Parent getRoot(Class<?> clazz) {
        try {
            return getLoader(clazz).load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
