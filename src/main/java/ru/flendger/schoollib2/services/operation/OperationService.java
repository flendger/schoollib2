package ru.flendger.schoollib2.services.operation;

import ru.flendger.schoollib2.model.operation.Operation;
import ru.flendger.schoollib2.services.CrudNonDeletedObjectsService;

import java.util.Optional;

public interface OperationService<T extends Operation> extends CrudNonDeletedObjectsService<T> {
    Optional<T> findByNumber(Integer number);
}
