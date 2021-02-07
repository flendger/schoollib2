package ru.flendger.schoollib2.gui.forms.object.catalog;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.flendger.schoollib2.gui.elements.IntegerSpinner;
import ru.flendger.schoollib2.model.catalog.*;
import ru.flendger.schoollib2.services.catalog.BookService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
@NoArgsConstructor
public class BookController extends AbstractCatalogController<Book, BorderPane, BookService> implements Initializable {

    @FXML
    public TextField codeField;
    public TextField nameField;
    public TextField bookTypeField;
    public TextField publisherField;
    public TextField authorField;
    public IntegerSpinner yearField;
    public IntegerSpinner priceRubField;
    public IntegerSpinner priceCentField;
    public IntegerSpinner classFromField;
    public IntegerSpinner classToField;
    public TextField ownerField;
    public CheckBox ownershipBox;
    public TextField subjectField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title = "Справочник: Книги";

        nameField.textProperty().addListener((observable, oldValue, newValue) -> fieldChanged());
        authorField.textProperty().addListener((observable, oldValue, newValue) -> fieldChanged());
        yearField.getEditor().textProperty().addListener((observable, oldValue, newValue) -> fieldChanged());
        classFromField.getEditor().textProperty().addListener((observable, oldValue, newValue) -> fieldChanged());
        classToField.getEditor().textProperty().addListener((observable, oldValue, newValue) -> fieldChanged());
        priceRubField.getEditor().textProperty().addListener((observable, oldValue, newValue) -> fieldChanged());
        priceCentField.getEditor().textProperty().addListener((observable, oldValue, newValue) -> fieldChanged());
        ownershipBox.selectedProperty().addListener((observable, oldValue, newValue) -> fieldChanged());

        yearField.setBounds(1800, 2099, 2018);
        classFromField.setBounds(1, 11, 1);
        classToField.setBounds(1, 11, 1);
        priceCentField.setBounds(0, 99, 0);
    }

    @Override
    protected void fillForm() throws Throwable {
        super.fillForm();

        codeField.setText(String.valueOf(object.getCode()));
        nameField.setText(object.getName());
        authorField.setText(object.getAuthor());
        ownershipBox.setSelected(object.isOwnership());

        yearField.getValueFactory().setValue(object.getYear());

        classFromField.getValueFactory().setValue(object.getClassFrom());
        classToField.getValueFactory().setValue(object.getClassTo());

        if (object.getBookType() != null) {
            bookTypeField.setText(object.getBookType().getName());
        }
        if (object.getPublisher() != null) {
            publisherField.setText(object.getPublisher().getName());
        }
        if (object.getOwner() != null) {
            ownerField.setText(object.getOwner().getName());
        }
        if (object.getSubject() != null) {
            subjectField.setText(object.getSubject().getName());
        }

        priceRubField.getValueFactory().setValue((int) object.getPrice());
        priceCentField.getValueFactory().setValue((int) (object.getPrice()%1*100));
    }

    @Override
    protected void fillObject() {
        object.setName(nameField.getText());
        object.setAuthor(authorField.getText());
        object.setOwnership(ownershipBox.isSelected());
        object.setYear(yearField.getValue());
        object.setClassFrom(classFromField.getValue());
        object.setClassTo(classToField.getValue());
        object.setPrice(priceRubField.getValue() + ((double) priceCentField.getValue()/100));
    }

    public void btnSelectBookTypeClicked() {
        try {
            listFormLoader.getCatalogListForm(BookType.class, stage).open(this::onBookTypeChanged);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onBookTypeChanged(BookType o) {
        if (o == null) return;

        object.setBookType(o);
        bookTypeField.setText(object.getBookType().getName());
        fieldChanged();
    }

    public void btnSelectPublisherClicked() {
        try {
            listFormLoader.getCatalogListForm(Publisher.class, stage).open(this::onPublisherChanged);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onPublisherChanged(Publisher o) {
        if (o == null) return;

        object.setPublisher(o);
        publisherField.setText(object.getPublisher().getName());
        fieldChanged();
    }

    public void btnSelectOwnerClicked() {
        try {
            listFormLoader.getCatalogListForm(Owner.class, stage).open(this::onOwnerChanged);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onOwnerChanged(Owner o) {
        if (o == null) return;

        object.setOwner(o);
        ownerField.setText(object.getOwner().getName());
        fieldChanged();
    }

    public void btnSelectSubjectClicked() {
        try {
            listFormLoader.getCatalogListForm(Subject.class, stage).open(this::onSubjectChanged);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onSubjectChanged(Subject o) {
        if (o == null) return;

        object.setSubject(o);
        subjectField.setText(object.getSubject().getName());
        fieldChanged();
    }
}