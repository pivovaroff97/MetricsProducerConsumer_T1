package ru.pivovarov.t1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Metric {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @CreatedDate
    @Column(name = "created_at")
    private Long createdAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "measurement_id", referencedColumnName = "id")
    private Measurement measurement;
}
