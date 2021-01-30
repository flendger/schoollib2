package ru.flendger.schoollib2.repositories;

import ru.flendger.schoollib2.model.DbObjectNonDeleted;

import java.util.List;

public interface DbNonDeletedRepository<T extends DbObjectNonDeleted> {
    List<T> findAllByIsDeleted(boolean isDeleted);
}
