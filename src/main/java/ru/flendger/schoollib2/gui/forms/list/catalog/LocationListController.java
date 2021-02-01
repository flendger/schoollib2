package ru.flendger.schoollib2.gui.forms.list.catalog;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.VBox;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.flendger.schoollib2.model.catalog.Location;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
@NoArgsConstructor
public class LocationListController extends AbstractCatalogListController<Location, VBox> {

    @FXML
    TableColumn<Location, String> personCol, locationTypeCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title = "Справочник: Места хранения";

        personCol.setCellValueFactory(param -> {
            var par = param.getValue().getPerson();
            return new SimpleStringProperty(par == null ? "" : par.getName());
        });
        locationTypeCol.setCellValueFactory(param -> {
            var par = param.getValue().getLocationType();
            return new SimpleStringProperty(par == null ? "" : par.getName());
        });

        super.initialize(location, resources);
    }
}