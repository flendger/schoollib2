package ru.flendger.schoollib2.services;

import ru.flendger.schoollib2.model.DbObject;

import java.util.List;
import java.util.Optional;

public interface CrudService<T extends DbObject>{
    Optional<T> findById(Integer id);

    List<T> findAll();

    T save(T obj);

    void deleteById(Integer id);

    void delete(T obj);

    T getNewObject();
}
