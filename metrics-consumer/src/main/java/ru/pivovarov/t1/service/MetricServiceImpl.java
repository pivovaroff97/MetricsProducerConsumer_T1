package ru.pivovarov.t1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.pivovarov.t1.model.Metric;
import ru.pivovarov.t1.repository.MetricRepository;

@Service
public class MetricServiceImpl implements MetricService {

    private final MetricRepository metricRepository;

    @Autowired
    public MetricServiceImpl(MetricRepository metricRepository) {
        this.metricRepository = metricRepository;
    }

    @Override
    public Metric save(Metric metric) {
        return metricRepository.save(metric);
    }

    @Override
    public Page<Metric> getAllMetrics(Pageable pageable) {
        return metricRepository.findAll(pageable);
    }

    @Override
    public Page<Metric> getLastMetrics(String name, Pageable pageable) {
        return metricRepository.findByNameOrderByCreatedAtDesc(name, pageable);
    }

    @Override
    public Metric getMetricById(Long id) {
        return metricRepository.findById(id).get();
    }
}
