package ru.pivovarov.t1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pivovarov.t1.model.Metric;

@Repository
public interface MetricRepository extends JpaRepository<Metric, Long> {
    Page<Metric> findByNameOrderByCreatedAtDesc(String name, Pageable pageable);
}
