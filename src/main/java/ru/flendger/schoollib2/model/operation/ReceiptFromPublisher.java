package ru.flendger.schoollib2.model.operation;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.flendger.schoollib2.model.catalog.Location;
import ru.flendger.schoollib2.model.catalog.Publisher;
import ru.flendger.schoollib2.model.operation.item.ReceiptFromPublisherItem;
import ru.flendger.schoollib2.model.storage.LocationStorageEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "doc_receipt_from_publishers")
@Data
@NoArgsConstructor
public class ReceiptFromPublisher implements Operation<ReceiptFromPublisherItem>{
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

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReceiptFromPublisherItem> items;

    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "receipt_from_publisher_location_storage",
            joinColumns = @JoinColumn(name = "doc_id"),
            inverseJoinColumns = @JoinColumn(name = "storage_id")
    )
    private List<LocationStorageEntity> locationStorageEntities;

    @Override
    public String toString() {
        return "ReceiptFromPublisher{" +
                "id=" + id +
                ", date=" + date +
                ", number=" + number +
                ", isAccepted=" + isAccepted +
                ", isDeleted=" + isDeleted +
                ", comment='" + comment + '\'' +
                '}';
    }
}
