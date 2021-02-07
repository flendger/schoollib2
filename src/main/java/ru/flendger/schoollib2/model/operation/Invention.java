package ru.flendger.schoollib2.model.operation;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.flendger.schoollib2.model.catalog.Location;
import ru.flendger.schoollib2.model.operation.item.InventionItem;
import ru.flendger.schoollib2.model.storage.LocationStorageEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "doc_invention")
@Data
@NoArgsConstructor
public class Invention implements Operation<InventionItem>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "docdate")
    private LocalDateTime date;

    @Column(name = "docnum")
    private Integer number;

    @Column(name = "is_accepted")
    private boolean isAccepted;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventionItem> items;

    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "invention_location_storage",
            joinColumns = @JoinColumn(name = "doc_id"),
            inverseJoinColumns = @JoinColumn(name = "storage_id")
    )
    private List<LocationStorageEntity> locationStorageEntities;

    @Override
    public String toString() {
        return "Invention{" +
                "id=" + id +
                ", date=" + date +
                ", number=" + number +
                ", isAccepted=" + isAccepted +
                ", isDeleted=" + isDeleted +
                ", comment='" + comment + '\'' +
                '}';
    }
}
