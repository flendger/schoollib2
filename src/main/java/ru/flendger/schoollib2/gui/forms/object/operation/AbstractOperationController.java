package ru.flendger.schoollib2.gui.forms.object.operation;


import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import ru.flendger.schoollib2.gui.forms.object.AbstractDbObjectController;
import ru.flendger.schoollib2.gui.forms.object.DbObjectForm;
import ru.flendger.schoollib2.gui.utils.FormUtils;
import ru.flendger.schoollib2.model.operation.Operation;

public abstract class AbstractOperationController<O extends Operation, V extends Pane> extends AbstractDbObjectController<O, V> implements DbObjectForm<O> {
    @Autowired
    protected FormUtils formLoader;
}