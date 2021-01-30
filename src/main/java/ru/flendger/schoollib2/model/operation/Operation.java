package ru.flendger.schoollib2.model.operation;


import ru.flendger.schoollib2.model.DbObjectNonDeleted;
import ru.flendger.schoollib2.model.operation.item.OperationItem;

import java.time.LocalDateTime;
import java.util.List;

public interface Operation extends DbObjectNonDeleted {
    Integer getNumber();
    void setNumber(Integer number);
    LocalDateTime getDate();
    void setDate(LocalDateTime date);
    boolean isAccepted();
    void setAccepted(boolean accepted);
    List<? extends OperationItem<? extends Operation>> getOperationItems();
}
