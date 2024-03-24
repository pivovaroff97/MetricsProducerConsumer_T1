package ru.pivovarov.t1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import ru.pivovarov.t1.model.Metric;
import ru.pivovarov.t1.service.MetricUtils;

@RequestMapping("/metrics")
@RestController
public class MetricRestController {
    private final MetricUtils metricUtils;

    @Autowired
    public MetricRestController(MetricUtils metricUtils) {
        this.metricUtils = metricUtils;
    }

    @PostMapping("/add/{name}")
    public ResponseEntity<String> addMetric(@PathVariable String name) {
        if (metricUtils.getAllMetrics().contains(name)) {
            metricUtils.addMetric(name);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove/{name}")
    public ResponseEntity<String> removeMetric(@PathVariable String name) {
        if (metricUtils.getActiveMetrics().contains(name)) {
            metricUtils.removeMetric(name);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/clear")
    public ResponseEntity<String> removeAllMetrics() {
        metricUtils.removeAllMetrics();
        return ResponseEntity.ok().build();
    }
}
