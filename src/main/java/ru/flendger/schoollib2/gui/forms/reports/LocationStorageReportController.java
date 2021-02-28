package ru.flendger.schoollib2.gui.forms.reports;

import javafx.fxml.Initializable;
import javafx.scene.web.WebView;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.stereotype.Component;
import ru.flendger.schoollib2.model.storage.LocationStorageBalanceItem;
import ru.flendger.schoollib2.services.storage.LocationStorageService;

import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class LocationStorageReportController implements Initializable {
    private final LocationStorageService locationStorageService;
    public WebView webField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private void printReport() {
        //todo: delete all storage entities when deleting document
        //todo: invention list - refresh list after add new doc
        //todo: добавить группировки и итоги по МХ
        try {
            InputStream resource = getClass().getClassLoader().getResourceAsStream("reports/location_storage.jrxml");

            JasperDesign jasperDesign = JRXmlLoader.load(resource);
            JasperReport jasperReport  = JasperCompileManager.compileReport(jasperDesign);

            Map<String, Object> parameters = new HashMap<>();

            List<LocationStorageBalanceItem> dataBeanList = locationStorageService.findBalanceByDate(LocalDateTime.now());
            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);

            JasperPrint jasperPrint   = JasperFillManager.fillReport(jasperReport,
                    parameters,
                    beanColDataSource);

            String tmpFile = System.getProperty("java.io.tmpdir") + "location_storage.html";
            JasperExportManager.exportReportToHtmlFile(jasperPrint, tmpFile);

            webField.getEngine().load("file:///" + tmpFile);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public void getReport() {
        printReport();

    }
}
