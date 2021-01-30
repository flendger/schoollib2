package ru.flendger.schoollib2.repositories.operation;

import org.springframework.data.repository.NoRepositoryBean;
import ru.flendger.schoollib2.model.DbObjectNonDeleted;
import ru.flendger.schoollib2.repositories.DbNonDeletedRepository;

import java.util.Optional;

@NoRepositoryBean
public interface OperationRepository<T extends DbObjectNonDeleted> extends DbNonDeletedRepository<T> {
    Optional<T> findByNumber(Integer number);

    Optional<T> findTopByOrderByNumberDesc();
}
