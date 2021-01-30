package ru.flendger.schoollib2.repositories.catalog;

import org.springframework.stereotype.Repository;
import ru.flendger.schoollib2.model.catalog.Subject;

@Repository
public interface SubjectRepository extends CatalogRepository<Subject> {
}
