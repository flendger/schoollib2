package ru.flendger.schoollib2.gui.forms.list.operation;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import ru.flendger.schoollib2.gui.forms.list.AbstractListController;
import ru.flendger.schoollib2.gui.forms.list.ListForm;
import ru.flendger.schoollib2.model.operation.Operation;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import static ru.flendger.schoollib2.gui.utils.FormElementUtils.DATE_TIME_FORMATTER;

public abstract class AbstractOperationListController<O extends Operation, V extends Pane> extends AbstractListController<O, V> implements ListForm<O>, Initializable {

    @FXML
    public TableColumn<O, String> commentCol;
    public TableColumn<O, Integer> numberCol;
    public TableColumn<O, LocalDateTime> dateCol;
    public TableColumn<O, Boolean> acceptCol;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
        numberCol.setStyle("-fx-alignment: CENTER-RIGHT;");
        commentCol.setCellValueFactory(new PropertyValueFactory<>("comment"));

        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateCol.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    this.setText(DATE_TIME_FORMATTER.format(item));
                }
            }
        });
        dateCol.setStyle("-fx-alignment: CENTER;");

        acceptCol.setCellValueFactory(arg -> new SimpleBooleanProperty(arg.getValue().isAccepted()));
        acceptCol.setCellFactory(arg -> new CheckBoxTableCell<>());

        dataTable.getSortOrder().add(numberCol);

        super.initialize(location, resources);
    }
}
