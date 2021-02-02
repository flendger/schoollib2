package ru.flendger.schoollib2.gui.forms.object.catalog;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.flendger.schoollib2.model.catalog.Owner;
import ru.flendger.schoollib2.services.catalog.OwnerService;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
@NoArgsConstructor
public class OwnerController extends AbstractCatalogController<Owner, BorderPane, OwnerService> implements Initializable {

    @FXML
    public TextField codeField;
    public TextField nameField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title = "Справочник: Владельцы";

        nameField.textProperty().addListener((observable, oldValue, newValue) -> fieldChanged());
    }

    @Override
    protected void fillForm() throws Throwable {
        super.fillForm();

        codeField.setText(String.valueOf(object.getCode()));
        nameField.setText(object.getName());
    }

    @Override
    protected void fillObject() {
        object.setName(nameField.getText());
    }
}
