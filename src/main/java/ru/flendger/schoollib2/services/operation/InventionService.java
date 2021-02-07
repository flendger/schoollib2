package ru.flendger.schoollib2.services.operation;


import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.flendger.schoollib2.model.operation.Invention;
import ru.flendger.schoollib2.model.operation.item.InventionItem;
import ru.flendger.schoollib2.model.storage.LocationStorageEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventionService extends AbstractOperationService<Invention, InventionItem> {
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
        //todo: storage should be written only if obj isAccepted or delete storage entities
        if (obj.isAccepted()) {
            List<LocationStorageEntity> locationStorageEntities;
            if (obj.getId() == null) {
                locationStorageEntities = new ArrayList<>();
            } else {
                Optional<Invention> o = findById(obj.getId());
                if (o.isPresent()) {
                    locationStorageEntities = o.get().getLocationStorageEntities();
                    Hibernate.initialize(locationStorageEntities);
                    locationStorageEntities.clear();
                } else {
                    locationStorageEntities = new ArrayList<>();
                }
            }
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

        if (obj.getDate() == null) {
            obj.setDate(LocalDateTime.now());
        }
        return super.save(obj);
    }
}
