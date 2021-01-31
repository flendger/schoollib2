package ru.flendger.schoollib2.services.catalog;

import org.springframework.stereotype.Service;
import ru.flendger.schoollib2.model.catalog.LocationType;

@Service
public class LocationTypeService extends AbstractCatalogService<LocationType> {
    @Override
    public LocationType getNewObject() {
        return new LocationType();
    }
}
