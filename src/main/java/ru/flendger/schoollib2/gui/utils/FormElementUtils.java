package ru.flendger.schoollib2.gui.utils;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.function.UnaryOperator;

public class FormElementUtils {

    public static final StringConverter<Integer> INTEGER_CONVERTER = new IntegerStringConverter() {
        @Override
        public String toString(Integer value) {
            if (value == null) {
                return "0";
            }
            return new DecimalFormat("0").format(value);
        }
    };

    public static final UnaryOperator<TextFormatter.Change> INTEGER_FILTER = change -> {
        String text = change.getControlNewText();
        if (text.isEmpty()) {
            return null;
        }

        if (text.matches("[0-9]*")) {
            if (text.length() > 9) {
                return null;
            }
            return change;
        }

        return null;
    };

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
}
