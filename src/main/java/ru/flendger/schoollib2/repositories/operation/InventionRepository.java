package ru.flendger.schoollib2.repositories.operation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.flendger.schoollib2.model.operation.Invention;

@Repository
public interface InventionRepository extends JpaRepository<Invention, Integer>, OperationRepository<Invention> {
}
