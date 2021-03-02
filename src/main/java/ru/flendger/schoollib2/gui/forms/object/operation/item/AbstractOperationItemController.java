package ru.flendger.schoollib2.gui.forms.object.operation.item;


import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import ru.flendger.schoollib2.gui.forms.object.DbObjectForm;
import ru.flendger.schoollib2.gui.utils.DialogUtils;
import ru.flendger.schoollib2.gui.utils.ListFormUtils;
import ru.flendger.schoollib2.model.operation.item.OperationItem;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class AbstractOperationItemController<O extends OperationItem<?>, V extends Pane> implements DbObjectForm<O> {

    protected String title;
    @Autowired
    protected ListFormUtils listFormLoader;

    protected O object;
    protected Stage stage;
    private boolean isModified = false;
    private Supplier<Void> updateNotifier;
    private Consumer<O> resultNotifier;

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

    protected abstract void fillForm();

    private void updateTitle() {
        if (this.stage == null) return;

        this.stage.setTitle(title + (isModified ? " *" : ""));
    }

    private void setUpStage() {
        this.stage.setResizable(true);
        this.stage.setAlwaysOnTop(true);
        this.stage.initModality(Modality.WINDOW_MODAL);
        updateTitle();
    }

    @Override
    public void setUpForm(O object, Stage stage) {
        this.object = object;
        this.stage = stage;
        this.stage.setOnCloseRequest(e -> {
            if (!beforeClose()) e.consume();
        });

        fillForm();
        this.isModified = false;
        setUpStage();
    }

    @Override
    public void open() {
        stage.show();
    }

    @Override
    public void open(Supplier<Void> updateNotifier) {
        this.updateNotifier = updateNotifier;
        open();
    }

    @Override
    public void open(Consumer<O> resultNotifier) {
        this.resultNotifier = resultNotifier;
        open();
    }

    @Override
    public void update() {
        fillObject();
        if (updateNotifier != null) {
            updateNotifier.get();
        }
        if (resultNotifier != null) {
            resultNotifier.accept(object);
        }
    }
}