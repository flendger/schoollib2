package ru.flendger.schoollib2.repositories.catalog;

import org.springframework.stereotype.Repository;
import ru.flendger.schoollib2.model.catalog.BookType;

@Repository
public interface BookTypeRepository extends CatalogRepository<BookType> {
}
