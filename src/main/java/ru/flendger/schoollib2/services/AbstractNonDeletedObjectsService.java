package ru.flendger.schoollib2.services;

import ru.flendger.schoollib2.model.DbObjectNonDeleted;
import ru.flendger.schoollib2.repositories.DbNonDeletedRepository;

import java.util.List;


public abstract class AbstractNonDeletedObjectsService<T extends DbObjectNonDeleted, R extends DbNonDeletedRepository<T>> extends AbstractService<T, R> implements CrudNonDeletedObjectsService<T> {
    @Override
    public List<T> findAllExcludeDeleted() {
        return repository.findAllByIsDeleted(false);
    }
}