package ru.flendger.schoollib2.gui.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

public class DialogUtils {
    private static Alert getAlert(Alert.AlertType alertType, String title, String header, String txt, Stage owner) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(txt);
        alert.initOwner(owner);
        alert.initModality(Modality.WINDOW_MODAL);
        return alert;
    }

    public static boolean showConfirmation(String title, String header, String txt, Stage owner) {
        Alert alert = getAlert(Alert.AlertType.CONFIRMATION, title, header, txt, owner);

        Optional<ButtonType> option = alert.showAndWait();
        return option.isPresent() && option.get() == ButtonType.OK;
    }

    public static void showError(String title, String header, String txt, Stage owner) {
        getAlert(Alert.AlertType.ERROR, title, header, txt, owner).showAndWait();
    }
}
