package ru.flendger.schoollib2.gui.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

public class DialogUtils {
    public static boolean showConfirmation(String title, String header, String txt, Stage owner) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(txt);
        alert.initOwner(owner);
        alert.initModality(Modality.WINDOW_MODAL);

        Optional<ButtonType> option = alert.showAndWait();
        return option.isPresent() && option.get() == ButtonType.OK;
    }
}
