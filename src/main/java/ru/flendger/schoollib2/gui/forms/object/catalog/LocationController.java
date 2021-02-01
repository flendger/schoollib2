package ru.flendger.schoollib2.gui.forms.object.catalog;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.flendger.schoollib2.model.catalog.Location;
import ru.flendger.schoollib2.model.catalog.LocationType;
import ru.flendger.schoollib2.model.catalog.Person;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
@NoArgsConstructor
public class LocationController extends AbstractCatalogController<Location, BorderPane> implements Initializable {

    @FXML
    public TextField codeField;
    public TextField nameField;
    public TextField locationTypeField;
    public TextField personField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title = "Справочник: Места хранения";

        nameField.textProperty().addListener((observable, oldValue, newValue) -> fieldChanged());
    }

    protected void fillForm() {
        codeField.setText(String.valueOf(object.getCode()));
        nameField.setText(object.getName());
        if (object.getLocationType() != null) {
            locationTypeField.setText(object.getLocationType().getName());
        }
        if (object.getPerson() != null) {
            personField.setText(object.getPerson().getName());
        }
    }

    @Override
    protected void fillObject() {
        object.setName(nameField.getText());
    }

    public void btnSelectLocationTypeClicked() {
        try {
            listFormLoader.getCatalogListForm(LocationType.class, stage).open(this::onLocationTypeChanged);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnSelectPersonClicked() {
        try {
            listFormLoader.getCatalogListForm(Person.class, stage).open(this::onPersonChanged);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onLocationTypeChanged(Object o) {
        if (o == null) return;

        object.setLocationType((LocationType) o);
        locationTypeField.setText(object.getLocationType().getName());
        fieldChanged();
    }

    private void onPersonChanged(Object o) {
        if (o == null) return;

        object.setPerson((Person) o);
        personField.setText(object.getPerson().getName());
        fieldChanged();
    }
}