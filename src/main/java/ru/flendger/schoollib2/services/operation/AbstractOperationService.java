package ru.flendger.schoollib2.services.operation;


import ru.flendger.schoollib2.model.operation.Operation;
import ru.flendger.schoollib2.repositories.operation.OperationRepository;
import ru.flendger.schoollib2.services.AbstractNonDeletedObjectsService;

import java.util.Optional;

public abstract class AbstractOperationService<T extends Operation> extends AbstractNonDeletedObjectsService<T, OperationRepository<T>> implements OperationService<T> {
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
}