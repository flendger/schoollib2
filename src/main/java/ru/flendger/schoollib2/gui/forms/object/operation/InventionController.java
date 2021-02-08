package ru.flendger.schoollib2.gui.forms.object.operation;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.flendger.schoollib2.gui.utils.DialogUtils;
import ru.flendger.schoollib2.model.catalog.Location;
import ru.flendger.schoollib2.model.operation.Invention;
import ru.flendger.schoollib2.model.operation.item.InventionItem;
import ru.flendger.schoollib2.services.operation.InventionService;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

@Component
@Scope("prototype")
@NoArgsConstructor
public class InventionController extends AbstractOperationController<Invention, InventionItem, BorderPane, InventionService> implements Initializable {
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    @FXML
    public TextField numberField;
    public TextField dateField;
    public TextField commentField;
    public TableColumn<InventionItem, Integer> rowCol;
    public TableColumn<InventionItem, String> bookCol;
    public TableColumn<InventionItem, Integer> quantityCol;
    public TableView<InventionItem> itemsTable;
    public TextField locationField;
    public CheckBox acceptedBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //todo: form should be read-only if isAccepted
        title = "Операция: Инветаризация";

        commentField.textProperty().addListener((observable, oldValue, newValue) -> fieldChanged());
        acceptedBox.selectedProperty().addListener((observable, oldValue, newValue) -> fieldChanged());
        rowCol.setCellValueFactory(new PropertyValueFactory<>("rowNum"));
        rowCol.setStyle("-fx-alignment: CENTER-RIGHT;");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantityCol.setStyle("-fx-alignment: CENTER-RIGHT;");

        bookCol.setCellValueFactory(param -> {
            var par = param.getValue().getBook();
            return new SimpleStringProperty(par == null ? "" : par.getName());
        });
        itemsTable.setRowFactory(tr -> {
            TableRow<InventionItem> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    openItem(row.getItem());
                }
            });
            return row;
        });
        itemsTable.getSortOrder().add(rowCol);
    }

    @Override
    protected void fillForm() throws Throwable {
        super.fillForm();

        numberField.setText(String.valueOf(object.getNumber()));
        dateField.setText(dtf.format(object.getDate()));
        if (object.getLocation() != null) {
            locationField.setText(object.getLocation().getName());
        }
        commentField.setText(object.getComment());
        acceptedBox.setSelected(object.isAccepted());
        updateList();
    }

    @Override
    protected void fillObject() {
        object.setComment(commentField.getText());
        object.setAccepted(acceptedBox.isSelected());
        object.getItems().clear();
        object.getItems().addAll(itemsTable.getItems());
    }

    @Override
    public void btnOkClicked() {
        if (isModified && object.getLocation() == null) {
            DialogUtils.showError("Ошибка сохранения объекта", "Необходимо заполнить Место храения", "", stage);
            return;
        }
        super.btnOkClicked();
    }

    private void updateList() {
        itemsTable.getItems().clear();
        itemsTable.getItems().addAll(object.getItems());
        itemsTable.sort();
    }

    public InventionItem getCurrentItem() {
        return itemsTable.getFocusModel().getFocusedItem();
    }

    private void openItem(InventionItem item) {
        try {
            formLoader.getDbObjectForm(item, getWindow()).open(this::addItemAction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getLastRowNum() {
        return itemsTable.getItems().stream()
                .mapToInt(InventionItem::getRowNum)
                .max()
                .orElse(0);
    }

    public void addRowAction() {
        InventionItem item = new InventionItem();
        item.setDocument(object);
        item.setRowNum(getLastRowNum() + 1);
        openItem(item);
    }

    public void deleteRowAction() {
        InventionItem item = getCurrentItem();
        if (item == null) return;

        if (!DialogUtils.showConfirmation("Удаление строки", String.format("Удалить строку %d?", item.getRowNum()), "", getWindow())) {
            return;
        }

        itemsTable.getItems().remove(item);
        sortItems();
        fieldChanged();
        refreshItemsAction();
    }

    private void sortItems() {
        AtomicReference<Integer> i = new AtomicReference<>(1);
        itemsTable.getItems().stream()
                .sorted(Comparator.comparingInt(InventionItem::getRowNum))
                .forEach(item -> item.setRowNum(i.getAndSet(i.get() + 1)));
    }

    private void addItemAction(Object object) {
        InventionItem item = (InventionItem) object;
        if (!itemsTable.getItems().contains(item)) {
            itemsTable.getItems().add(item);
        }
        fieldChanged();
        refreshItemsAction();
    }

    public void refreshItemsAction() {
        itemsTable.refresh();
    }

    public void btnSelectLocationClicked() {
        try {
            listFormLoader.getCatalogListForm(Location.class, stage).open(this::onLocationChanged);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onLocationChanged(Location location) {
        if (location == null) return;

        object.setLocation(location);
        locationField.setText(location.getName());
        fieldChanged();
    }
}