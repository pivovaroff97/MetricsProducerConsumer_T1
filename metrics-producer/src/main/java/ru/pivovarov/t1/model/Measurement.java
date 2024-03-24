package ru.pivovarov.t1.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Measurement {
    private Double value;
}
