package ru.flendger.schoollib2.model;

public interface DbObjectNonDeleted extends DbObject{
    boolean isDeleted();
    void setDeleted(boolean isDeleted);
}
