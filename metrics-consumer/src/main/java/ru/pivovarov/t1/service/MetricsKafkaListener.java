package ru.pivovarov.t1.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.pivovarov.t1.model.Metric;

@Component
@Slf4j
public class MetricsKafkaListener {

    private final MetricService metricAnalyzerService;

    @Autowired
    public MetricsKafkaListener(MetricService metricAnalyzerService) {
        this.metricAnalyzerService = metricAnalyzerService;
    }

    @KafkaListener(id = "metric", topics = "metrics-topic", containerFactory = "metricConcurrentKafkaListenerContainerFactory")
    public void listenMetrics(Metric metric) {
        metricAnalyzerService.save(metric);
        log.info("Metric from kafka: {}", metric);
    }
}
