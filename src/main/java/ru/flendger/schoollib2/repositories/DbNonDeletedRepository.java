package ru.flendger.schoollib2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.flendger.schoollib2.model.DbObjectNonDeleted;

import java.util.List;

@NoRepositoryBean
public interface DbNonDeletedRepository<T extends DbObjectNonDeleted> extends JpaRepository<T, Integer> {
    List<T> findAllByIsDeleted(boolean isDeleted);
}
