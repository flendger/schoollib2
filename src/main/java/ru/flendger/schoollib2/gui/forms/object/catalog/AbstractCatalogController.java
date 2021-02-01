package ru.flendger.schoollib2.gui.forms.object.catalog;


import javafx.scene.layout.Pane;
import ru.flendger.schoollib2.gui.forms.object.AbstractDbObjectController;
import ru.flendger.schoollib2.gui.forms.object.DbObjectForm;
import ru.flendger.schoollib2.model.catalog.Catalog;

public abstract class AbstractCatalogController<O extends Catalog, V extends Pane> extends AbstractDbObjectController<O, V> implements DbObjectForm<O> {
}