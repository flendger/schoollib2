package ru.flendger.schoollib2.gui.forms.list.operation;

import javafx.scene.layout.VBox;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.flendger.schoollib2.model.operation.Invention;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
@NoArgsConstructor
public class InventionListController extends AbstractOperationListController<Invention, VBox> {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title = "Операция: Инвентаризация";

        super.initialize(location, resources);
    }
}
