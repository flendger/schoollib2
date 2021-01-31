package ru.flendger.schoollib2.services.catalog;

import org.springframework.stereotype.Service;
import ru.flendger.schoollib2.model.catalog.Subject;

@Service
public class SubjectService extends AbstractCatalogService<Subject> {
    @Override
    public Subject getNewObject() {
        return new Subject();
    }
}
