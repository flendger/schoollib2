package ru.flendger.schoollib2.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.flendger.schoollib2.repositories.catalog.*;

@Component
@RequiredArgsConstructor
public class MainController {

    private final SubjectRepository repository;

    @FXML
    public TextArea textField;

    public void getProducts() {
        StringBuilder sb = new StringBuilder();
        repository.findAll().forEach(item -> sb.append(item).append("\n"));
        sb.append("\n");

        repository.findAllByIsDeleted(true).forEach(item -> sb.append(item).append("\n"));
        sb.append("\n");

        sb.append(repository.findByCode(1).orElse(null));
        textField.setText(sb.toString());
    }
}
