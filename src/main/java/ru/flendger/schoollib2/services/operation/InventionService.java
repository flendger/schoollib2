package ru.flendger.schoollib2.services.operation;


import org.springframework.stereotype.Service;
import ru.flendger.schoollib2.model.operation.Invention;
import ru.flendger.schoollib2.model.operation.item.InventionItem;

@Service
public class InventionService extends AbstractOperationService<Invention, InventionItem> {
    @Override
    public Invention getNewObject() {
        return new Invention();
    }
}
