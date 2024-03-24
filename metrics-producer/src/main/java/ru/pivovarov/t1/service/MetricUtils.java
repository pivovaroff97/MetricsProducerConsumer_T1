package ru.pivovarov.t1.service;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class MetricUtils {

    private Set<String> allMetrics = new HashSet<>(){{
        add("disk.free");
        add("jvm.memory.used");
        add("process.cpu.usage");
        add("system.cpu.usage");
        add("process.uptime");
    }};
    private Set<String> activeMetrics = new HashSet<>(){{
        add("process.cpu.usage");
    }};

    public void addMetric(String name) {
        activeMetrics.add(name);
    }

    public void removeMetric(String name) {
        activeMetrics.remove(name);
    }

    public void removeAllMetrics() {
        activeMetrics.clear();
    }

    public Set<String> getAllMetrics() {
        return allMetrics;
    }

    public Set<String> getActiveMetrics() {
        return activeMetrics;
    }
}
