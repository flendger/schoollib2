package ru.flendger.schoollib2.gui.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.flendger.schoollib2.gui.forms.list.ListForm;
import ru.flendger.schoollib2.model.DbObject;
import ru.flendger.schoollib2.model.catalog.*;
import ru.flendger.schoollib2.model.operation.Invention;
import ru.flendger.schoollib2.model.operation.ReceiptFromPublisher;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class ListFormUtils {
    private final ApplicationContext applicationContext;

    private HashMap<Class<? extends DbObject>, String> resourceMap;

    @PostConstruct
    private void init() {
        resourceMap = new HashMap<>();
        resourceMap.put(Book.class, "/gui/list/catalog/book_list.fxml");
        resourceMap.put(BookType.class, "/gui/list/catalog/book_type_list.fxml");
        resourceMap.put(Location.class, "/gui/list/catalog/location_list.fxml");
        resourceMap.put(LocationType.class, "/gui/list/catalog/location_type_list.fxml");
        resourceMap.put(Owner.class, "/gui/list/catalog/owner_list.fxml");
        resourceMap.put(Person.class, "/gui/list/catalog/person_list.fxml");
        resourceMap.put(Publisher.class, "/gui/list/catalog/publisher_list.fxml");
        resourceMap.put(Subject.class, "/gui/list/catalog/subject_list.fxml");
        resourceMap.put(Invention.class, "/gui/list/operation/invention_list.fxml");
        resourceMap.put(ReceiptFromPublisher.class, "/gui/list/operation/receipt_from_publisher_list.fxml");
    }


    private FXMLLoader getLoader(Class<? extends DbObject> clazz) {
        FXMLLoader fxmlLoader = new FXMLLoader(FormUtils.class.
                getResource(resourceMap.get(clazz)));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        return fxmlLoader;
    }

    public Parent getRoot(Class<? extends DbObject> clazz) {
        try {
            return getLoader(clazz).load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <O extends Catalog> ListForm<O> getCatalogListForm(Class<O> clazz, Window owner) throws IOException {
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
