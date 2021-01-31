package ru.flendger.schoollib2.services.catalog;

import org.springframework.stereotype.Service;
import ru.flendger.schoollib2.model.catalog.Publisher;

@Service
public class PublisherService extends AbstractCatalogService<Publisher> {
    @Override
    public Publisher getNewObject() {
        return new Publisher();
    }
}
