package ru.flendger.schoollib2.gui.forms.object.operation;


import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import ru.flendger.schoollib2.gui.forms.object.AbstractDbObjectController;
import ru.flendger.schoollib2.gui.forms.object.DbObjectForm;
import ru.flendger.schoollib2.gui.utils.FormUtils;
import ru.flendger.schoollib2.model.operation.Operation;
import ru.flendger.schoollib2.model.operation.item.OperationItem;
import ru.flendger.schoollib2.services.operation.OperationService;

import java.util.NoSuchElementException;

public abstract class AbstractOperationController<O extends Operation<I>, I extends OperationItem<O>, V extends Pane, S extends OperationService<O, I>> extends AbstractDbObjectController<O, V, S> implements DbObjectForm<O> {
    @Autowired
    protected FormUtils formLoader;

    @Override
    protected void fillForm() throws Throwable{
        Integer id = object.getId();
        object = service.findByIdWithItems(id).orElseThrow(() -> new NoSuchElementException(String.format("ID [%d] not found", id)));
    }
}