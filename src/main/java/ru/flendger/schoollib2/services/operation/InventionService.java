package ru.flendger.schoollib2.services.operation;


import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.flendger.schoollib2.model.operation.Invention;
import ru.flendger.schoollib2.model.operation.item.InventionItem;
import ru.flendger.schoollib2.model.storage.LocationStorageEntity;
import ru.flendger.schoollib2.services.storage.LocationStorageService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventionService extends AbstractOperationService<Invention, InventionItem> {
    private final LocationStorageService locationStorageService;

    @Override
    public Invention getNewObject() {
        Invention ni = new Invention();
        ni.setDate(LocalDateTime.now());
        ni.setItems(new ArrayList<>());
        return ni;
    }

    @Transactional
    @Override
    public Invention save(Invention obj) {
        if (obj.getDate() == null) {
            obj.setDate(LocalDateTime.now());
        }

        if (obj.isAccepted()) {
            List<LocationStorageEntity> locationStorageEntities;
            if (obj.getId() == null) {
                locationStorageEntities = new ArrayList<>();
            } else {
                Optional<Invention> o = findById(obj.getId());
                if (o.isPresent()) {
                    locationStorageEntities = o.get().getLocationStorageEntities();
                    Hibernate.initialize(locationStorageEntities);

                    locationStorageService.deleteAll(locationStorageEntities);
                    locationStorageService.flush();

                    locationStorageEntities.clear();
                } else {
                    locationStorageEntities = new ArrayList<>();
                }
            }

            locationStorageService.findBalanceByDateAndLocationId(obj.getDate(), obj.getLocation().getId()).forEach(row -> {
                LocationStorageEntity newEntity = new LocationStorageEntity();
                newEntity.setBook(row.getBook());
                newEntity.setLocation(row.getLocation());
                newEntity.setQuantity((int) -row.getQuantity());
                newEntity.setDate(obj.getDate());
                locationStorageEntities.add(newEntity);
            });

            obj.setLocationStorageEntities(locationStorageEntities);
            obj.getItems().forEach(inventionItem -> {
                LocationStorageEntity newEntity = new LocationStorageEntity();
                newEntity.setBook(inventionItem.getBook());
                newEntity.setLocation(obj.getLocation());
                newEntity.setQuantity(inventionItem.getQuantity());
                newEntity.setDate(obj.getDate());
                locationStorageEntities.add(newEntity);
            });
        }

        return super.save(obj);
    }
}
