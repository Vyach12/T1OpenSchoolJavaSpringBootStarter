# Spring Boot HTTP Logging Starter
## Обзор
Стартер Spring Boot HTTP Logging предоставляет функциональность для логирования всех входящих и исходящих HTTP запросов и ответов в вашем приложении на базе Spring Boot. Этот стартер обладает высокой настраиваемостью, что позволяет вам изменять форматы логов, уровни логирования и пути, которые необходимо логировать.

## Особенности
Логирование HTTP запросов и ответов.
Настройка форматов логов для запросов и ответов.
Установка уровней логирования.
Определение путей, которые нужно логировать.

## Установка
Установите стартер Spring Boot HTTP Logging в ваше локальное Maven-репозитори:

``` sh
cd /spring-boot-starter
mvn clean install 
```

Добавьте зависимость в pom.xml вашего Spring Boot приложения:

```xml
<dependency>
    <groupId>openschool.java</groupId>
    <artifactId>spring-boot-starter-logging</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Конфигурация
Стартер можно настроить через application.properties или application.yml в вашем Spring Boot приложении.

application.properties:
``` properties
http.logging.enabled=true
http.logging.paths=/api,/admin
http.logging.log-level=DEBUG
http.logging.log-format.request={method} {url}
http.logging.log-format.response={status} {duration}ms
```

application.yml:
```yaml
http:
    logging:
        enabled: true
        paths: /api,/admin
        level: DEBUG
        format:
            request: "{method} {url}"
            response: "{status} {duration}ms"
```
## Настройка
- Enabled: Включает или отключает логирование.
- Paths: Указывает, какие пути нужно логировать.
- Level: Устанавливает уровень логирования (TRACE, DEBUG, INFO, WARN, ERROR).
  - Format: Настраивает формат логов для запросов и ответов.
    -  Запрос (request)
          - {method}: HTTP метод запроса (GET, POST и т.д.).
          - {url}: URL запроса.
          - {headers}: Заголовки запроса.
          - {body}: Тело запроса (если есть).
      - Ответы (response):
          - {status}: HTTP статус ответа. 
          - {headers}: Заголовки ответа. 
          - {body}: Тело ответа (если есть). 
          - {duration}: Время обработки запроса в миллисекундах.

### Пример настроек форматов:
```properties
http.logging.log-format.request={method} {url} {headers}
http.logging.log-format.response={status} {duration}ms {headers}
```