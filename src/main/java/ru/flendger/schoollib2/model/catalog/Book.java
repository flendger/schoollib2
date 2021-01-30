package ru.flendger.schoollib2.model.catalog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Catalog{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code")
    private int code;

    @Column(name = "name")
    private String name;

    @Column(name = "isDeleted")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "bookTypeId")
    private BookType bookType;

    @ManyToOne
    @JoinColumn(name = "subjectId")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "publisherId")
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "ownerId")
    private Owner owner;

    @Column(name = "author")
    private String author;

    @Column(name = "classFrom")
    private int classFrom;

    @Column(name = "classTo")
    private int classTo;

    @Column(name = "year")
    private int year;

    @Column(name = "ownership")
    private boolean ownership;

    @Column(name = "price")
    private double price;

    public void setPrice(double price) {
        this.price = (double) Math.round(price*100)/100;
    }
}