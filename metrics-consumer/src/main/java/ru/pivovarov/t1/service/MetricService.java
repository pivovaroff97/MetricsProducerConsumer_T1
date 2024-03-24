package ru.pivovarov.t1.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.pivovarov.t1.model.Metric;

public interface MetricService {
    Metric save(Metric metric);
    Page<Metric> getAllMetrics(Pageable pageable);
    Page<Metric> getLastMetrics(String name, Pageable pageable);
    Metric getMetricById(Long id);
}
