package ru.flendger.schoollib2.model.operation.item;


import ru.flendger.schoollib2.model.DbObject;
import ru.flendger.schoollib2.model.operation.Operation;

public interface OperationItem<D extends Operation> extends DbObject {
    D getDocument();
    Integer getRowNum();
    void setRowNum(Integer rowNum);
    void setDocument(D document);
}
