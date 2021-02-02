package ru.flendger.schoollib2.gui.forms.object;

import javafx.stage.Stage;
import ru.flendger.schoollib2.gui.forms.ResultNotifier;
import ru.flendger.schoollib2.gui.forms.UpdateNotifier;
import ru.flendger.schoollib2.model.DbObject;

public interface DbObjectForm<O extends DbObject> {
    void open();
    void open(UpdateNotifier updateNotifier);
    void open(ResultNotifier<O> resultNotifier);
    void update();
    void setUpForm(O object, Stage stage);
}
