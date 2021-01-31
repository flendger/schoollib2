package ru.flendger.schoollib2.gui.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import ru.flendger.schoollib2.gui.forms.list.ListForm;
import ru.flendger.schoollib2.model.DbObject;
import ru.flendger.schoollib2.model.catalog.*;
import ru.flendger.schoollib2.model.operation.Invention;

import java.io.IOException;
import java.util.HashMap;

public class ListFormUtils {

    private final static HashMap<Class<? extends DbObject>, String> resourceMap;

    static {
        resourceMap = new HashMap<>();
        resourceMap.put(Book.class, "/ru/flendger/schoollib/fxgui/listForms/catalogs/bookList.fxml");
        resourceMap.put(BookType.class, "/ru/flendger/schoollib/fxgui/listForms/catalogs/bookTypeList.fxml");
        resourceMap.put(Location.class, "/ru/flendger/schoollib/fxgui/listForms/catalogs/locationList.fxml");
        resourceMap.put(LocationType.class, "/ru/flendger/schoollib/fxgui/listForms/catalogs/locationTypeList.fxml");
        resourceMap.put(Owner.class, "/ru/flendger/schoollib/fxgui/listForms/catalogs/ownerList.fxml");
        resourceMap.put(Person.class, "/ru/flendger/schoollib/fxgui/listForms/catalogs/personList.fxml");
        resourceMap.put(Publisher.class, "/ru/flendger/schoollib/fxgui/listForms/catalogs/publisherList.fxml");
        resourceMap.put(Subject.class, "/ru/flendger/schoollib/fxgui/listForms/catalogs/subjectList.fxml");
        resourceMap.put(Invention.class, "/ru/flendger/schoollib/fxgui/listForms/operations/inventionList.fxml");
    }


    private static FXMLLoader getLoader(Class<? extends DbObject> clazz) {
        return new FXMLLoader(FormUtils.class.
                getResource(resourceMap.get(clazz)));
    }

    public static Parent getRoot(Class<? extends DbObject> clazz) {
        try {
            return getLoader(clazz).load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <O extends Catalog> ListForm<O> getCatalogListForm(Class<O> clazz, Window owner) throws IOException {
        FXMLLoader fxmlLoader = getLoader(clazz);
        Parent root = fxmlLoader.load();
        ListForm<O> ctrl = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initOwner(owner);

        ctrl.setUpForm(stage);
        return ctrl;
    }
}
