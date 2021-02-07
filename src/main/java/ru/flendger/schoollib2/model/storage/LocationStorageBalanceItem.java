package ru.flendger.schoollib2.model.storage;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.flendger.schoollib2.model.catalog.Book;
import ru.flendger.schoollib2.model.catalog.Location;

@Data
@NoArgsConstructor
public class LocationStorageBalanceItem {
    private Book book;
    private Location location;
    private long quantity;

    public LocationStorageBalanceItem(Object[] row) {
        this.book = (Book) row[0];
        this.location = (Location) row[1];
        this.quantity = (long) row[2];
    }
}
