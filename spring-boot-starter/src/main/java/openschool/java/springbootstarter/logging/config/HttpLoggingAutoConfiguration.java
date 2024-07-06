package openschool.java.springbootstarter.logging.config;

import lombok.extern.slf4j.Slf4j;
import openschool.java.springbootstarter.logging.filter.HttpLoggingFilter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Автоконфигурация для логирования HTTP запросов.
 * Конфигурирует фильтр для логирования.
 */
@Slf4j
@AutoConfiguration
@EnableConfigurationProperties(HttpLoggingProperties.class)
@ConditionalOnProperty(prefix = "logging", value = "enabled", havingValue = "true")
public class HttpLoggingAutoConfiguration {

    @Bean
    public HttpLoggingFilter httpLoggingFilter(HttpLoggingProperties properties) {
        log.info("Initializing HttpLoggingFilter with properties: {}", properties);
        return new HttpLoggingFilter(properties);
    }
}
