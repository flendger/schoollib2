package ru.flendger.schoollib2.repositories.catalog;

import org.springframework.stereotype.Repository;
import ru.flendger.schoollib2.model.catalog.Book;

@Repository
public interface BookRepository extends CatalogRepository<Book> {
}
