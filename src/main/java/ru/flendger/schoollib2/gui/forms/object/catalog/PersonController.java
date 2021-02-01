package ru.flendger.schoollib2.gui.forms.object.catalog;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.flendger.schoollib2.model.catalog.Person;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
@NoArgsConstructor
public class PersonController extends AbstractCatalogController<Person, BorderPane> implements Initializable {

    @FXML
    public TextField codeField;
    public TextField nameField;
    public TextField phoneField;
    public TextField emailField;
    public TextField addressField;
    public TextArea commentField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title = "Справочник: Ответственные";

        nameField.textProperty().addListener((observable, oldValue, newValue) -> fieldChanged());
        phoneField.textProperty().addListener((observable, oldValue, newValue) -> fieldChanged());
        emailField.textProperty().addListener((observable, oldValue, newValue) -> fieldChanged());
        addressField.textProperty().addListener((observable, oldValue, newValue) -> fieldChanged());
        commentField.textProperty().addListener((observable, oldValue, newValue) -> fieldChanged());
    }

    protected void fillForm() {
        codeField.setText(String.valueOf(object.getCode()));
        nameField.setText(object.getName());
        phoneField.setText(object.getTelephone());
        emailField.setText(object.getEmail());
        addressField.setText(object.getAddress());
        commentField.setText(object.getComment());
    }

    @Override
    protected void fillObject() {
        object.setName(nameField.getText());
        object.setTelephone(phoneField.getText());
        object.setEmail(emailField.getText());
        object.setAddress(addressField.getText());
        object.setComment(commentField.getText());
    }
}
