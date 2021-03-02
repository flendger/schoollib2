package ru.flendger.schoollib2.gui.forms.list.catalog;

import javafx.scene.layout.VBox;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.flendger.schoollib2.model.catalog.Subject;
import ru.flendger.schoollib2.services.catalog.SubjectService;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
@NoArgsConstructor
public class SubjectListController extends AbstractCatalogListController<Subject, VBox, SubjectService> {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title = "Справочник: Предметы";

        super.initialize(location, resources);
    }
}
