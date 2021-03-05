package ru.flendger.schoollib2.gui.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.flendger.schoollib2.gui.forms.object.DbObjectForm;
import ru.flendger.schoollib2.model.DbObject;
import ru.flendger.schoollib2.model.catalog.*;
import ru.flendger.schoollib2.model.operation.Invention;
import ru.flendger.schoollib2.model.operation.ReceiptFromPublisher;
import ru.flendger.schoollib2.model.operation.item.InventionItem;
import ru.flendger.schoollib2.model.operation.item.ReceiptFromPublisherItem;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class FormUtils {
    private final ApplicationContext applicationContext;

    private HashMap<Class<? extends DbObject>, String> resourceMap;

    @PostConstruct
    private void init() {
        resourceMap = new HashMap<>();
        resourceMap.put(Book.class, "/gui/object/catalog/book.fxml");
        resourceMap.put(BookType.class, "/gui/object/catalog/book_type.fxml");
        resourceMap.put(Location.class, "/gui/object/catalog/location.fxml");
        resourceMap.put(LocationType.class, "/gui/object/catalog/location_type.fxml");
        resourceMap.put(Owner.class, "/gui/object/catalog/owner.fxml");
        resourceMap.put(Person.class, "/gui/object/catalog/person.fxml");
        resourceMap.put(Publisher.class, "/gui/object/catalog/publisher.fxml");
        resourceMap.put(Subject.class, "/gui/object/catalog/subject.fxml");
        resourceMap.put(Invention.class, "/gui/object/operation/invention.fxml");
        resourceMap.put(InventionItem.class, "/gui/object/operation/item/invention_item.fxml");
        resourceMap.put(ReceiptFromPublisher.class, "/gui/object/operation/receipt_from_publisher.fxml");
        resourceMap.put(ReceiptFromPublisherItem.class, "/gui/object/operation/item/receipt_from_publisher_item.fxml");
    }

    private <O extends DbObject> DbObjectForm<O> getForm(FXMLLoader fxmlLoader, O object, Window owner) throws IOException {
        Parent root = fxmlLoader.load();
        DbObjectForm<O> ctrl = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initOwner(owner);

        ctrl.setUpForm(object, stage);
        return ctrl;
    }

    public <O extends DbObject> DbObjectForm<O> getDbObjectForm(O object, Window owner) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FormUtils.class.
                getResource(resourceMap.get(object.getClass())));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        return getForm(fxmlLoader, object, owner);
    }
}
