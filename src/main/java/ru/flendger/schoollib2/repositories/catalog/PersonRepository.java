package ru.flendger.schoollib2.repositories.catalog;

import org.springframework.stereotype.Repository;
import ru.flendger.schoollib2.model.catalog.Person;

@Repository
public interface PersonRepository extends CatalogRepository<Person> {
}
