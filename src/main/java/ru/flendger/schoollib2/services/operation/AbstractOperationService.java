package ru.flendger.schoollib2.services.operation;


import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;
import ru.flendger.schoollib2.model.operation.Operation;
import ru.flendger.schoollib2.model.operation.item.OperationItem;
import ru.flendger.schoollib2.repositories.operation.OperationRepository;
import ru.flendger.schoollib2.services.AbstractNonDeletedObjectsService;

import java.util.List;
import java.util.Optional;

public abstract class AbstractOperationService<T extends Operation<I>, I extends OperationItem<T>> extends AbstractNonDeletedObjectsService<T, OperationRepository<T>> implements OperationService<T, I> {
    @Override
    public Optional<T> findByNumber(Integer number) {
        return repository.findByNumber(number);
    }

    @Override
    public T save(T obj) {
        if (obj.getId() == null) {
            T lastObj = repository.findTopByOrderByNumberDesc().orElse(null);
            if (lastObj == null) {
                obj.setNumber(1);
            } else {
                obj.setNumber(lastObj.getNumber() + 1);
            }
        }
        return repository.save(obj);
    }

    @Transactional
    @Override
    public List<I> findItemsByDocumentId(Integer id) {
        T object = findById(id).orElse(null);
        if (object == null) return List.of();

        return object.getItems();
    }

    @Transactional
    @Override
    public Optional<T> findByIdWithItems(Integer id) {
        Optional<T> object = findById(id);
        object.ifPresent(t -> Hibernate.initialize(t.getItems()));
        return object;
    }
}