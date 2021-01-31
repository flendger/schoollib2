package ru.flendger.schoollib2.repositories.catalog;

import org.springframework.stereotype.Repository;
import ru.flendger.schoollib2.model.catalog.Owner;

@Repository
public interface OwnerRepository extends CatalogRepository<Owner> {
}
