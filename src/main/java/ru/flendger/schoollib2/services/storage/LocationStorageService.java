package ru.flendger.schoollib2.services.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.flendger.schoollib2.model.storage.LocationStorageBalanceItem;
import ru.flendger.schoollib2.model.storage.LocationStorageEntity;
import ru.flendger.schoollib2.repositories.storage.LocationStorageRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationStorageService {
    private final LocationStorageRepository locationStorageRepository;

    public List<LocationStorageBalanceItem> findBalanceByDate(LocalDateTime dateTime) {
        return locationStorageRepository.findBalanceByDate(dateTime)
                .stream()
                .map(row -> new LocationStorageBalanceItem((Object[]) row))
                .collect(Collectors.toList());
    }

    public List<LocationStorageBalanceItem> findBalanceByDateAndLocationId(LocalDateTime dateTime, Integer locationId) {
        return locationStorageRepository.findBalanceByDateAndLocationId(dateTime, locationId)
                .stream()
                .map(row -> new LocationStorageBalanceItem((Object[]) row))
                .collect(Collectors.toList());
    }

    public void deleteAll(Iterable<LocationStorageEntity> iterable) {
        locationStorageRepository.deleteAll(iterable);
    }

    public void flush() {
        locationStorageRepository.flush();
    }
}
