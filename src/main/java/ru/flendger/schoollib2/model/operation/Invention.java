package ru.flendger.schoollib2.model.operation;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import ru.flendger.schoollib2.model.catalog.Location;
import ru.flendger.schoollib2.model.operation.item.InventionItem;
import ru.flendger.schoollib2.model.operation.item.OperationItem;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "doc_invention")
@Data
@NoArgsConstructor
public class Invention implements Operation{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "docdate")
    private LocalDateTime date;

    @Column(name = "docnum")
    private Integer number;

    @Column(name = "isAccepted")
    private boolean isAccepted;

    @Column(name = "isDeleted")
    private boolean isDeleted;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "document", fetch = FetchType.EAGER, orphanRemoval = true)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<InventionItem> items;

    @Override
    public List<? extends OperationItem<? extends Operation>> getOperationItems() {
        return getItems();
    }

    @Override
    public String toString() {
        return "Invention{" +
                "id=" + id +
                ", date=" + date +
                ", number=" + number +
                ", isAccepted=" + isAccepted +
                ", isDeleted=" + isDeleted +
                ", Rows=" + items.size() +
                ", comment='" + comment + '\'' +
                '}';
    }
}
