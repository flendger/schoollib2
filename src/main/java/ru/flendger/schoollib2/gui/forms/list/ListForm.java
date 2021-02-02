package ru.flendger.schoollib2.gui.forms.list;

import javafx.stage.Stage;
import ru.flendger.schoollib2.gui.forms.ResultNotifier;
import ru.flendger.schoollib2.model.DbObject;

public interface ListForm<O extends DbObject> {
    void open();
    void open(ResultNotifier<O> resultNotifier);
    void updateList();
    void openElement(O object);
    void openCurrentElement();
    void deleteElement(O object);
    void deleteCurrentElement();
    void addNewElement();
    O getCurrent();
    void setUpForm(Stage stage);
}
