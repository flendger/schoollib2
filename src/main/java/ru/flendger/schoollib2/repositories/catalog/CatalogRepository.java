package ru.flendger.schoollib2.repositories.catalog;

import org.springframework.data.repository.NoRepositoryBean;
import ru.flendger.schoollib2.model.catalog.Catalog;
import ru.flendger.schoollib2.repositories.DbNonDeletedRepository;

import java.util.Optional;

@NoRepositoryBean
public interface CatalogRepository<T extends Catalog> extends DbNonDeletedRepository<T> {
    Optional<T> findByCode(Integer code);
    Optional<T> findTopByOrderByCodeDesc();
}
