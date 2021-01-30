package ru.flendger.schoollib2.model.storage;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.flendger.schoollib2.model.DbObject;
import ru.flendger.schoollib2.model.catalog.Book;
import ru.flendger.schoollib2.model.catalog.Location;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "location_storage")
@Data
@NoArgsConstructor
public class LocationStorageEntity implements DbObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "quantity")
    private int quantity;
}
