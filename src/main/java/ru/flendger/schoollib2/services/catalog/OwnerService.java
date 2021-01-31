package ru.flendger.schoollib2.services.catalog;

import org.springframework.stereotype.Service;
import ru.flendger.schoollib2.model.catalog.Owner;

@Service
public class OwnerService extends AbstractCatalogService<Owner> {
    @Override
    public Owner getNewObject() {
        return new Owner();
    }
}
