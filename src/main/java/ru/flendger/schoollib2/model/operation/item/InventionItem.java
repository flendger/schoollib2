package ru.flendger.schoollib2.model.operation.item;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.flendger.schoollib2.model.catalog.Book;
import ru.flendger.schoollib2.model.operation.Invention;

import javax.persistence.*;

@Entity
@Table(name = "doctable_invention")
@Data
@NoArgsConstructor
public class InventionItem implements OperationItem<Invention>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "doc_id")
    private Invention document;

    @Column(name = "rownum")
    private Integer rowNum;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
