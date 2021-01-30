package ru.flendger.schoollib2.services.operation;


import org.springframework.stereotype.Service;
import ru.flendger.schoollib2.model.operation.Invention;

@Service
public class InventionService extends AbstractOperationService<Invention> {
    @Override
    public Invention getNewObject() {
        return new Invention();
    }
}
