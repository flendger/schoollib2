package ru.flendger.schoollib2.gui.forms.list.catalog;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import ru.flendger.schoollib2.gui.forms.list.AbstractListController;
import ru.flendger.schoollib2.gui.forms.list.ListForm;
import ru.flendger.schoollib2.model.catalog.Catalog;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class AbstractCatalogListController<O extends Catalog, V extends Pane> extends AbstractListController<O, V> implements ListForm<O>, Initializable {

    @FXML
    public TableColumn<O, String> nameCol;
    public TableColumn<O, Integer> codeCol;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        codeCol.setStyle("-fx-alignment: CENTER-RIGHT;");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        dataTable.getSortOrder().add(codeCol);

        super.initialize(location, resources);
    }
}
