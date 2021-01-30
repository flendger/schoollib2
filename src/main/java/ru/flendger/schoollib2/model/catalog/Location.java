package ru.flendger.schoollib2.model.catalog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "locations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location implements Catalog{
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
    @JoinColumn(name = "locationTypeId")
    private LocationType locationType;

    @ManyToOne
    @JoinColumn(name = "personId")
    private Person person;
}
