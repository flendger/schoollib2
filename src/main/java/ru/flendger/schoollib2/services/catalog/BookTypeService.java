package ru.flendger.schoollib2.services.catalog;

import org.springframework.stereotype.Service;
import ru.flendger.schoollib2.model.catalog.BookType;

@Service
public class BookTypeService extends AbstractCatalogService<BookType> {
    @Override
    public BookType getNewObject() {
        return new BookType();
    }
}
