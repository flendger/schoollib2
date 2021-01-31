package ru.flendger.schoollib2.services.catalog;

import org.springframework.stereotype.Service;
import ru.flendger.schoollib2.model.catalog.Book;

@Service
public class BookService extends AbstractCatalogService<Book> {
    @Override
    public Book getNewObject() {
        return new Book();
    }
}