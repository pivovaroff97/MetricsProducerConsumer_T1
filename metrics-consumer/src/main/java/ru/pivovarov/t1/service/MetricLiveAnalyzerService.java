package ru.pivovarov.t1.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.pivovarov.t1.model.Metric;

import java.util.List;

@Service
@Slf4j
public class MetricLiveAnalyzerService {

    private final MetricService metricService;

    @Autowired
    public MetricLiveAnalyzerService(MetricService metricService) {
        this.metricService = metricService;
    }

    @Scheduled(fixedRate = 10000L)
    public void loggingAnalyzeMetric() {
        List<Metric> metrics = metricService.getLastMetrics(
                "process.cpu.usage", PageRequest.of(0, 10)).getContent();
        log.info("Average last 10 values of process.cpu.usage: {}", calculateAvgValue(metrics));
    }

    private double calculateAvgValue(List<Metric> metrics) {
        Double sum = 0.0;
        for (Metric m : metrics) {
            sum += m.getMeasurement().getValue();
        }
        return sum / metrics.size();
    }
}
