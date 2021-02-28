package ru.flendger.schoollib2.gui.forms.list;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import ru.flendger.schoollib2.gui.forms.ResultNotifier;
import ru.flendger.schoollib2.gui.utils.DialogUtils;
import ru.flendger.schoollib2.gui.utils.FormUtils;
import ru.flendger.schoollib2.model.DbObjectNonDeleted;
import ru.flendger.schoollib2.services.CrudNonDeletedObjectsService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class AbstractListController<O extends DbObjectNonDeleted, V extends Pane> implements ListForm<O>, Initializable {

    protected String title;
    @Autowired
    protected CrudNonDeletedObjectsService<O> service;
    @Autowired
    protected FormUtils objectFormLoader;
    protected Stage stage;
    protected ResultNotifier<O> resultNotifier;
    protected boolean selectedMode;

    @FXML
    public TableView<O> dataTable;
    public V listForm;
    public Button addButton;
    public Button deleteButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataTable.setRowFactory(tr -> {
            TableRow<O> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    openElement(row.getItem());
                }
            });
            return row ;
        });

        updateList();
    }

    @Override
    public void open() {
        updateFormElements();
        stage.show();
    }

    private void updateFormElements() {
        if (selectedMode) {
            stage.setTitle("ВЫБОР: " + title);
            addButton.setMinWidth(0);
            addButton.setPrefWidth(0);
            addButton.setDisable(true);
            deleteButton.setMinWidth(0);
            deleteButton.setPrefWidth(0);
            deleteButton.setDisable(true);
        } else {
            stage.setTitle(title);
        }
    }

    @Override
    public void open(ResultNotifier<O> resultNotifier) {
        this.resultNotifier = resultNotifier;
        this.selectedMode = true;
        open();
    }

    @Override
    public void updateList() {
        dataTable.getItems().clear();
        dataTable.getItems().addAll(service.findAllExcludeDeleted());
        dataTable.sort();
    }

    private void updateRow(O object) {
        if (object == null) return;

        dataTable.getItems().replaceAll(item -> item.getId().equals((object).getId()) ? object : item);
        dataTable.refresh();
    }

    @Override
    public void openElement(O object) {
        if (object == null) return;
        try {
            if (selectedMode) {
                if (resultNotifier != null) {
                    resultNotifier.notifyForm(object);
                }
                stage.close();
            } else {
                objectFormLoader.getDbObjectForm(object, getWindow()).open(this::updateRow);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUpStage() {
        this.stage.setResizable(true);
        this.stage.setAlwaysOnTop(true);
    }

    @Override
    public void setUpForm(Stage stage) {
        this.stage = stage;
        setUpStage();
    }

    @Override
    public void openCurrentElement() {
        O object = getCurrent();
        if (object == null) {
            return;
        }
        openElement(object);
    }

    @Override
    public void deleteElement(O object) {
        if (object == null) {
            return;
        }
        object.setDeleted(!object.isDeleted());
        service.save(object);
    }

    @Override
    public void deleteCurrentElement() {
        O object = getCurrent();
        if (object == null) return;

        if (! DialogUtils.showConfirmation("Удаление элемента", "Удалить текущий элемент?", "", getWindow())) {
            return;
        }

        deleteElement(object);
        updateList();
    }

    @Override
    public void addNewElement() {
        openElement(service.getNewObject());
    }

    @Override
    public O getCurrent() {
        return dataTable.getFocusModel().getFocusedItem();
    }

    protected Stage getWindow() {
        return (Stage) listForm.getScene().getWindow();
    }
}
