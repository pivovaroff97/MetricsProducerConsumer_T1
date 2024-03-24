package ru.pivovarov.t1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Measurement {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private Double value;
}
