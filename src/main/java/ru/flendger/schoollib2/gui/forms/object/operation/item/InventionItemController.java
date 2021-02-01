package ru.flendger.schoollib2.gui.forms.object.operation.item;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.flendger.schoollib2.gui.elements.IntegerSpinner;
import ru.flendger.schoollib2.model.catalog.Book;
import ru.flendger.schoollib2.model.operation.item.InventionItem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
@NoArgsConstructor
public class InventionItemController extends AbstractOperationItemController<InventionItem, BorderPane> implements Initializable {

    @FXML
    public TextField bookField;
    public TextField rowNumField;
    public IntegerSpinner quantityField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title = "Строка операции: Инветаризация";

        quantityField.getEditor().textProperty().addListener((observable, oldValue, newValue) -> fieldChanged());
    }

    protected void fillForm() {
        rowNumField.setText(String.valueOf(object.getRowNum()));
        if (object.getBook() != null) {
            bookField.setText(object.getBook().getName());
        }
        quantityField.getValueFactory().setValue(object.getQuantity());
    }

    @Override
    protected void fillObject() {
        object.setQuantity(quantityField.getValue());
    }

    public void btnSelectBookClicked() {
        try {
            listFormLoader.getCatalogListForm(Book.class, stage).open(this::onBookChanged);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onBookChanged(Object o) {
        if (o == null) return;

        object.setBook((Book) o);
        bookField.setText(object.getBook().getName());
        fieldChanged();
    }
}