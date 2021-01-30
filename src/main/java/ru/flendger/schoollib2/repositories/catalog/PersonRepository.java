package ru.flendger.schoollib2.repositories.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.flendger.schoollib2.model.catalog.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>, CatalogRepository<Person> {
}
