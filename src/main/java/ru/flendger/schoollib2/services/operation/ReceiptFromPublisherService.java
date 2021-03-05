package ru.flendger.schoollib2.services.operation;


import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.flendger.schoollib2.model.operation.ReceiptFromPublisher;
import ru.flendger.schoollib2.model.operation.item.ReceiptFromPublisherItem;
import ru.flendger.schoollib2.model.storage.LocationStorageEntity;
import ru.flendger.schoollib2.services.storage.LocationStorageService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceiptFromPublisherService extends AbstractOperationService<ReceiptFromPublisher, ReceiptFromPublisherItem> {
    private final LocationStorageService locationStorageService;

    @Override
    public ReceiptFromPublisher getNewObject() {
        ReceiptFromPublisher ni = new ReceiptFromPublisher();
        ni.setDate(LocalDateTime.now());
        ni.setItems(new ArrayList<>());
        return ni;
    }

    @Transactional
    @Override
    public ReceiptFromPublisher save(ReceiptFromPublisher obj) {
        if (obj.getDate() == null) {
            obj.setDate(LocalDateTime.now());
        }

        List<LocationStorageEntity> locationStorageEntities;
        if (obj.getId() == null) {
            locationStorageEntities = new ArrayList<>();
        } else {
            Optional<ReceiptFromPublisher> o = findById(obj.getId());
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

        if (obj.isAccepted() && !obj.isDeleted()) {
            obj.getItems().forEach(item -> {
                LocationStorageEntity newEntity = new LocationStorageEntity();
                newEntity.setBook(item.getBook());
                newEntity.setLocation(obj.getLocation());
                newEntity.setQuantity(item.getQuantity());
                newEntity.setDate(obj.getDate());
                locationStorageEntities.add(newEntity);
            });
        }
        obj.setLocationStorageEntities(locationStorageEntities);

        return super.save(obj);
    }
}
