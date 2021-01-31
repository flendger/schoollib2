package ru.flendger.schoollib2.services.catalog;


import ru.flendger.schoollib2.model.catalog.Catalog;
import ru.flendger.schoollib2.repositories.catalog.CatalogRepository;
import ru.flendger.schoollib2.services.AbstractNonDeletedObjectsService;

import java.util.Optional;

public abstract class AbstractCatalogService<T extends Catalog> extends AbstractNonDeletedObjectsService<T, CatalogRepository<T>> implements CatalogService<T>{
    @Override
    public T save(T obj) {
        if (obj.getId() == null) {
            T lastObj = repository.findTopByOrderByCodeDesc().orElse(null);
            if (lastObj == null) {
                obj.setCode(1);
            } else {
                obj.setCode(lastObj.getCode() + 1);
            }
        }
        return repository.save(obj);
    }

    @Override
    public Optional<T> findByCode(Integer code) {
        return repository.findByCode(code);
    }
}