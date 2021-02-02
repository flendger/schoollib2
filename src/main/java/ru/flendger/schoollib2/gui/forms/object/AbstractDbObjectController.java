package ru.flendger.schoollib2.gui.forms.object;


import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import ru.flendger.schoollib2.gui.forms.ResultNotifier;
import ru.flendger.schoollib2.gui.forms.UpdateNotifier;
import ru.flendger.schoollib2.gui.utils.DialogUtils;
import ru.flendger.schoollib2.gui.utils.ListFormUtils;
import ru.flendger.schoollib2.model.DbObjectNonDeleted;
import ru.flendger.schoollib2.services.CrudNonDeletedObjectsService;

public abstract class AbstractDbObjectController<O extends DbObjectNonDeleted, V extends Pane, S extends CrudNonDeletedObjectsService<O>> implements DbObjectForm<O> {

    protected String title;
    @Autowired
    protected S service;
    @Autowired
    protected ListFormUtils listFormLoader;

    protected O object;
    protected Stage stage;
    protected boolean isModified = false;
    protected UpdateNotifier updateNotifier;
    protected ResultNotifier<O> resultNotifier;

    @FXML
    public V obForm;


    public void close() {
        stage.close();
    }

    public void btnOkClicked() {
        if (isModified) {
            update();
        }
        close();
    }

    protected abstract void fillObject();

    public void btnCancelClicked() {
        if (beforeClose()) close();
    }

    protected boolean beforeClose() {
        if (isModified) {
            return DialogUtils.showConfirmation("Объект был изменен", "Выйти без сохранения объекта?", "", stage);
        }
        return true;
    }

    protected void fieldChanged() {
        this.isModified = true;
        updateTitle();
    }

    protected abstract void fillForm() throws Throwable;

    private void updateTitle() {
        if (this.stage == null) return;

        this.stage.setTitle(title + (isModified ? " *" : ""));
    }

    private void setUpStage() {
        this.stage.setResizable(true);
        this.stage.setAlwaysOnTop(true);
        updateTitle();
    }

    @Override
    public void setUpForm(O object, Stage stage) {
        this.object = object;
        this.stage = stage;
        this.stage.setOnCloseRequest(e -> {
            if (!beforeClose()) e.consume();
        });

        try {
            fillForm();
            this.isModified = false;
            setUpStage();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void open() {
        stage.show();
    }

    @Override
    public void open(UpdateNotifier updateNotifier) {
        this.updateNotifier = updateNotifier;
        open();
    }

    @Override
    public void update() {
        fillObject();
        service.save(object);
        if (updateNotifier != null) {
            updateNotifier.notifyForm();
        }
        if (resultNotifier != null) {
            resultNotifier.notifyForm(object);
        }
    }

    @Override
    public void open(ResultNotifier<O> resultNotifier) {
        this.resultNotifier = resultNotifier;
        open();
    }

    protected Stage getWindow() {
        return (Stage) obForm.getScene().getWindow();
    }
}