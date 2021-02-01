package ru.flendger.schoollib2.gui.forms.list.catalog;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.flendger.schoollib2.model.catalog.Book;
import ru.flendger.schoollib2.services.catalog.BookService;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
@NoArgsConstructor
public class BookListController extends AbstractCatalogListController<Book, VBox> {
    public TableColumn<Book, String> publisherCol, authorCol, subjectCol, classCol, bookTypeCol;
    public TableColumn<Book, Integer> yearCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title = "Справочник: Книги";

        publisherCol.setCellValueFactory(param -> {
            var par = param.getValue().getPublisher();
            return new SimpleStringProperty(par == null ? "" : par.getName());
        });
        subjectCol.setCellValueFactory(param -> {
            var par = param.getValue().getSubject();
            return new SimpleStringProperty(par == null ? "" : par.getName());
        });
        bookTypeCol.setCellValueFactory(param -> {
            var par = param.getValue().getBookType();
            return new SimpleStringProperty(par == null ? "" : par.getName());
        });

        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        authorCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getAuthor()));
        classCol.setCellValueFactory(param -> new SimpleStringProperty(String.format("%d - %d",
                                                                            param.getValue().getClassFrom(),
                                                                            param.getValue().getClassTo())));

        super.initialize(location, resources);
    }
}
