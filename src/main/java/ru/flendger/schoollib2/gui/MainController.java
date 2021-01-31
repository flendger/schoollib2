package ru.flendger.schoollib2.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.flendger.schoollib2.services.catalog.BookService;

@Component
@RequiredArgsConstructor
public class MainController {

    private final BookService service;

    @FXML
    public TextArea textField;

    public void getProducts() {
        StringBuilder sb = new StringBuilder();
        service.findAll().forEach(item -> sb.append(item).append("\n"));
        sb.append("\n");

        service.findAllExcludeDeleted().forEach(item -> sb.append(item).append("\n"));
        sb.append("\n");

        sb.append(service.findByCode(1).orElse(null));
//        sb.append(service.findByNumber(1).orElse(null));
        textField.setText(sb.toString());
    }
}
