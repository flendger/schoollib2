package ru.flendger.schoollib2.gui.forms.list.catalog;

import javafx.scene.layout.VBox;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.flendger.schoollib2.model.catalog.BookType;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
@NoArgsConstructor
public class BookTypeListController extends AbstractCatalogListController<BookType, VBox> {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title = "Справочник: Типы книг";
        super.initialize(location, resources);
    }
}
