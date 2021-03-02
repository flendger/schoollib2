package ru.flendger.schoollib2.gui.forms.object;

import javafx.stage.Stage;
import ru.flendger.schoollib2.model.DbObject;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface DbObjectForm<O extends DbObject> {
    void open();
    void open(Supplier<Void> updateNotifier);
    void open(Consumer<O> resultNotifier);
    void update();
    void setUpForm(O object, Stage stage);
}
