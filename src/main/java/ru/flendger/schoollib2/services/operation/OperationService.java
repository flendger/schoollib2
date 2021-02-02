package ru.flendger.schoollib2.services.operation;

import ru.flendger.schoollib2.model.operation.Operation;
import ru.flendger.schoollib2.model.operation.item.OperationItem;
import ru.flendger.schoollib2.services.CrudNonDeletedObjectsService;

import java.util.List;
import java.util.Optional;

public interface OperationService<T extends Operation<I>, I extends OperationItem<?>> extends CrudNonDeletedObjectsService<T> {
    Optional<T> findByNumber(Integer number);
    Optional<T> findByIdWithItems(Integer id);
    List<I> findItemsByDocumentId(Integer id);
}
