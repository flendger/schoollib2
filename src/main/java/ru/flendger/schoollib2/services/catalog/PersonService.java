package ru.flendger.schoollib2.services.catalog;

import org.springframework.stereotype.Service;
import ru.flendger.schoollib2.model.catalog.Person;

@Service
public class PersonService extends AbstractCatalogService<Person> {
    @Override
    public Person getNewObject() {
        return new Person();
    }
}
