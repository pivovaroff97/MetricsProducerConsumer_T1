package ru.pivovarov.t1.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.pivovarov.t1.model.Measurement;
import ru.pivovarov.t1.model.Metric;

import java.util.Set;

@Service
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, Metric> kafkaTemplate;
    private final MetricsEndpoint metricsEndpoint;
    private final MetricUtils metricUtils;
    @Value("${metrics.kafka.topic}")
    private String topic;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, Metric> kafkaTemplate, MetricsEndpoint metricsEndpoint, MetricUtils metricUtils) {
        this.kafkaTemplate = kafkaTemplate;
        this.metricsEndpoint = metricsEndpoint;
        this.metricUtils = metricUtils;
    }

    @Scheduled(initialDelay = 1000, fixedRate = 5000)
    public void sendMetrics() {
        Set<String> metricNames = metricUtils.getActiveMetrics();
        for (String name : metricNames) {
            MetricsEndpoint.MetricDescriptor descriptor = metricsEndpoint.metric(name, null);
            kafkaTemplate.send(topic,
                    Metric.builder()
                            .name(descriptor.getName())
                            .description(descriptor.getDescription())
                            .createdAt(System.currentTimeMillis())
                            .measurement(Measurement.builder()
                                    .value(descriptor.getMeasurements().get(0).getValue())
                                    .build())
                    .build());
        }
    }
}
