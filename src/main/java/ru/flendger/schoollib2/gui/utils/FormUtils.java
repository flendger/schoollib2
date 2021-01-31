package ru.flendger.schoollib2.gui.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import ru.flendger.schoollib2.gui.forms.object.DbObjectForm;
import ru.flendger.schoollib2.model.DbObject;
import ru.flendger.schoollib2.model.catalog.*;
import ru.flendger.schoollib2.model.operation.Invention;
import ru.flendger.schoollib2.model.operation.item.InventionItem;

import java.io.IOException;
import java.util.HashMap;

public class FormUtils {

    private final static HashMap<Class<? extends DbObject>, String> resourceMap;

    static {
        resourceMap = new HashMap<>();
        resourceMap.put(BookType.class, "/ru/flendger/schoollib/fxgui/objectForms/catalogs/bookType.fxml");
        resourceMap.put(LocationType.class, "/ru/flendger/schoollib/fxgui/objectForms/catalogs/locationType.fxml");
        resourceMap.put(Owner.class, "/ru/flendger/schoollib/fxgui/objectForms/catalogs/owner.fxml");
        resourceMap.put(Publisher.class, "/ru/flendger/schoollib/fxgui/objectForms/catalogs/publisher.fxml");
        resourceMap.put(Subject.class, "/ru/flendger/schoollib/fxgui/objectForms/catalogs/subject.fxml");
        resourceMap.put(Person.class, "/ru/flendger/schoollib/fxgui/objectForms/catalogs/person.fxml");
        resourceMap.put(Location.class, "/ru/flendger/schoollib/fxgui/objectForms/catalogs/location.fxml");
        resourceMap.put(Book.class, "/ru/flendger/schoollib/fxgui/objectForms/catalogs/book.fxml");
        resourceMap.put(Invention.class, "/ru/flendger/schoollib/fxgui/objectForms/operations/invention.fxml");
        resourceMap.put(InventionItem.class, "/ru/flendger/schoollib/fxgui/objectForms/operations/items/inventionItem.fxml");
    }

    private static <O extends DbObject> DbObjectForm<O> getForm(FXMLLoader fxmlLoader, O object, Window owner) throws IOException {
        Parent root = fxmlLoader.load();
        DbObjectForm<O> ctrl = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initOwner(owner);

        ctrl.setUpForm(object, stage);
        return ctrl;
    }

    public static <O extends DbObject> DbObjectForm<O> getDbObjectForm(O object, Window owner) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FormUtils.class.
                getResource(resourceMap.get(object.getClass())));
        return getForm(fxmlLoader, object, owner);
    }
}
