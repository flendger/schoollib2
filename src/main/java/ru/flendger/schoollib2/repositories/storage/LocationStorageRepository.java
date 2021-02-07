package ru.flendger.schoollib2.repositories.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.flendger.schoollib2.model.storage.LocationStorageEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LocationStorageRepository extends JpaRepository<LocationStorageEntity, Integer> {
    @Query(
            value = "select ls.book, ls.location, sum(ls.quantity) from LocationStorageEntity as ls where ls.date <= :date group by ls.book, ls.location"
    )
    List<?> findBalanceByDate(@Param("date") LocalDateTime dateTime);
}
