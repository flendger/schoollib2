package ru.flendger.schoollib2.model.catalog;


import ru.flendger.schoollib2.model.DbObjectNonDeleted;

public interface Catalog extends DbObjectNonDeleted {
    int getCode();
    String getName();
    void setCode(int code);
}
