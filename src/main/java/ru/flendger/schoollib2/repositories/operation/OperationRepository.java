package ru.flendger.schoollib2.repositories.operation;

import ru.flendger.schoollib2.model.operation.Operation;
import ru.flendger.schoollib2.repositories.DbNonDeletedRepository;

import java.util.Optional;

public interface OperationRepository<T extends Operation> extends DbNonDeletedRepository<T> {
    Optional<T> findByNumber(Integer number);
    Optional<T> findTopByOrderByNumberDesc();
}
