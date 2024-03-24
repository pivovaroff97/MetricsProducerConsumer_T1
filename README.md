# MetricsProducerConsumer_T1

Создать систему мониторинга, которая будет отслеживать работу различных компонентов вашего приложения с помощью Spring Kafka. Эта система будет включать в себя Producer для отправки метрик, Consumer для их обработки и анализа, а также REST API для просмотра метрик.

### Технологии
Spring boot, Kafka, Postgres

### Запуск окружения
    docker-compose up
После запуски контейнеров, запустить [MetricsConsumerApplication.java](metrics-consumer%2Fsrc%2Fmain%2Fjava%2Fru%2Fpivovarov%2Ft1%2FMetricsConsumerApplication.java)

Затем запустить [MetricsProducerApplication.java](metrics-producer%2Fsrc%2Fmain%2Fjava%2Fru%2Fpivovarov%2Ft1%2FMetricsProducerApplication.java) 

### Metric-producer
Scheduler каждые 5 секунд отправляет метрики

Можно отслеживать следующие метрики (names):
* "disk.free"
* "jvm.memory.used"
* "process.cpu.usage"
* "system.cpu.usage" (by default)
* "process.uptime"

Endpoints:

* POST /metrics/add/{name}: Добавить метрику в отслеживание.
* POST /metrics/remove/{name}: Удалить метрику из отслеживания.
* POST /metrics/clear: Удалить все метрики из отслеживания.

### Metric-consumer
Принимает метрики из Kafka топика "metrics-topic"

Endpoints:

* GET /metrics: Получение списка всех метрик.
* GET /metrics/{id}: Получение конкретной метрики по ее идентификатору.