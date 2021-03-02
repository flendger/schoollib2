package ru.flendger.schoollib2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.flendger.schoollib2.model.DbObject;

import java.util.List;
import java.util.Optional;


public abstract class AbstractService<T extends DbObject, R extends JpaRepository<T, Integer>> implements CrudService<T> {

    @Autowired
    protected R repository;

    @Override
    public Optional<T> findById(Integer id) {
        if (id == null) return Optional.empty();
        return repository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T save(T obj) {
        return repository.save(obj);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(T obj) {
        repository.delete(obj);
    }
}