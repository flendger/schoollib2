package ru.flendger.schoollib2.repositories.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.flendger.schoollib2.model.catalog.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, CatalogRepository<Book> {
}
