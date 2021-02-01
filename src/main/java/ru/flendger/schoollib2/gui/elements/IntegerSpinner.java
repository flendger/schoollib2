package ru.flendger.schoollib2.gui.elements;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextFormatter;

import static ru.flendger.schoollib2.gui.utils.FormElementUtils.INTEGER_CONVERTER;
import static ru.flendger.schoollib2.gui.utils.FormElementUtils.INTEGER_FILTER;

public class IntegerSpinner extends Spinner<Integer> {

    public IntegerSpinner() {
        super(0, Integer.MAX_VALUE, 0);
        this.getEditor().setTextFormatter(new TextFormatter<>(INTEGER_CONVERTER, null, INTEGER_FILTER));
    }

    public void setBounds(int min, int max, int current) {
        this.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, current));
    }
}
