package ru.flendger.schoollib2.gui.forms.list.catalog;

import javafx.scene.layout.VBox;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.flendger.schoollib2.model.catalog.Owner;
import ru.flendger.schoollib2.services.catalog.OwnerService;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
@NoArgsConstructor
public class OwnerListController extends AbstractCatalogListController<Owner, VBox, OwnerService> {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title = "Справочник: Владельцы";

        super.initialize(location, resources);
    }
}
