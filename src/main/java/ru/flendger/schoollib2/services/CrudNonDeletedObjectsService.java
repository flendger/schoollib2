package ru.flendger.schoollib2.services;

import ru.flendger.schoollib2.model.DbObjectNonDeleted;

import java.util.List;

public interface CrudNonDeletedObjectsService<T extends DbObjectNonDeleted> extends CrudService<T>{
    List<T> findAllExcludeDeleted();
}
