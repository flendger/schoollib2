package ru.flendger.schoollib2.repositories.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.flendger.schoollib2.model.catalog.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer>, CatalogRepository<Publisher> {
}
