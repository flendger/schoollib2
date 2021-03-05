package ru.flendger.schoollib2.gui.forms.list.operation;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.VBox;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.flendger.schoollib2.model.operation.ReceiptFromPublisher;
import ru.flendger.schoollib2.services.operation.ReceiptFromPublisherService;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
@NoArgsConstructor
public class ReceiptFromPublisherListController extends AbstractOperationListController<ReceiptFromPublisher, VBox, ReceiptFromPublisherService> {
    public TableColumn<ReceiptFromPublisher, String> locationCol, publisherCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title = "Операция: Поступление от издательства";

        locationCol.setCellValueFactory(param -> {
            var par = param.getValue().getLocation();
            return new SimpleStringProperty(par == null ? "" : par.getName());
        });

        publisherCol.setCellValueFactory(param -> {
            var par = param.getValue().getPublisher();
            return new SimpleStringProperty(par == null ? "" : par.getName());
        });

        super.initialize(location, resources);
    }
}
