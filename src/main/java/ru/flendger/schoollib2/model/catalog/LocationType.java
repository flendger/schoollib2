package ru.flendger.schoollib2.model.catalog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "location_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationType implements Catalog{
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
}
