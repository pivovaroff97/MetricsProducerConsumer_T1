package ru.pivovarov.t1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pivovarov.t1.model.Metric;
import ru.pivovarov.t1.model.MetricPageResponse;
import ru.pivovarov.t1.service.MetricService;

@RestController
@RequestMapping("/metrics")
public class MetricRestController {

    private final MetricService metricService;

    @Autowired
    public MetricRestController(MetricService metricService) {
        this.metricService = metricService;
    }

    @GetMapping
    public ResponseEntity<MetricPageResponse<Metric>> getMetrics(@RequestParam(required = false, defaultValue = "50") int size,
                                                                 @RequestParam(required = false, defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Metric> metricPage = metricService.getAllMetrics(pageable);
        return ResponseEntity.ok()
                .body(new MetricPageResponse<>(metricPage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Metric> getMetricById(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(metricService.getMetricById(id));
    }
}
