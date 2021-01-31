package ru.flendger.schoollib2.services.catalog;


import ru.flendger.schoollib2.model.catalog.Catalog;
import ru.flendger.schoollib2.services.CrudNonDeletedObjectsService;

import java.util.Optional;

public interface CatalogService<T extends Catalog> extends CrudNonDeletedObjectsService<T> {
    Optional<T> findByCode(Integer code);
}
