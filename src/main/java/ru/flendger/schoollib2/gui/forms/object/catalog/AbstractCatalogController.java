package ru.flendger.schoollib2.gui.forms.object.catalog;


import javafx.scene.layout.Pane;
import ru.flendger.schoollib2.gui.forms.object.AbstractDbObjectController;
import ru.flendger.schoollib2.gui.forms.object.DbObjectForm;
import ru.flendger.schoollib2.model.catalog.Catalog;
import ru.flendger.schoollib2.services.catalog.CatalogService;

import java.util.NoSuchElementException;

public abstract class AbstractCatalogController<O extends Catalog, V extends Pane, R extends CatalogService<O>> extends AbstractDbObjectController<O, V, R> implements DbObjectForm<O> {
    @Override
    protected void fillForm() throws Throwable{
        Integer id = object.getId();
        object = service.findById(id).orElseThrow(() -> new NoSuchElementException(String.format("ID [%d] not found", id)));
    }
}