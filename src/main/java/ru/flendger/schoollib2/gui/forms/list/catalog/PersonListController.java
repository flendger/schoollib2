package ru.flendger.schoollib2.gui.forms.list.catalog;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.flendger.schoollib2.model.catalog.Person;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
@NoArgsConstructor
public class PersonListController extends AbstractCatalogListController<Person, VBox> {

    @FXML
    public TableColumn<Person, String> phoneCol, mailCol, addrCol;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title = "Справочник: Ответственные";

        phoneCol.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        mailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        addrCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        super.initialize(location, resources);
    }
}
