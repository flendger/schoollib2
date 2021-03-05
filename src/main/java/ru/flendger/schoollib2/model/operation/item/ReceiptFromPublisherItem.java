package ru.flendger.schoollib2.model.operation.item;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.flendger.schoollib2.model.catalog.Book;
import ru.flendger.schoollib2.model.operation.ReceiptFromPublisher;

import javax.persistence.*;

@Entity
@Table(name = "doctable_receipt_from_publisher")
@Data
@NoArgsConstructor
public class ReceiptFromPublisherItem implements OperationItem<ReceiptFromPublisher>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "doc_id")
    private ReceiptFromPublisher document;

    @Column(name = "rownum")
    private Integer rowNum;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
