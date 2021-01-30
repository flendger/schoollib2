package ru.flendger.schoollib2.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MainController {

    @FXML
    public TextArea textField;

    public void getProducts() {
    }
}
