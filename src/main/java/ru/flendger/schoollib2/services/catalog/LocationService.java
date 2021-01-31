package ru.flendger.schoollib2.services.catalog;

import org.springframework.stereotype.Service;
import ru.flendger.schoollib2.model.catalog.Location;

@Service
public class LocationService extends AbstractCatalogService<Location> {
    @Override
    public Location getNewObject() {
        return new Location();
    }
}
