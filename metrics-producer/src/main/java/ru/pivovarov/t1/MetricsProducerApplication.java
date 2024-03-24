package ru.pivovarov.t1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MetricsProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetricsProducerApplication.class, args);
	}

}
