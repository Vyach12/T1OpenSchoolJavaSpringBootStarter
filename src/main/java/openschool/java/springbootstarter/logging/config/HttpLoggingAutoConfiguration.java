package openschool.java.springbootstarter.logging.config;

import openschool.java.springbootstarter.logging.filter.HttpLoggingFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Автоконфигурация для логирования HTTP запросов.
 * Конфигурирует фильтр и интерцептор для логирования.
 */
@Configuration
@EnableConfigurationProperties(HttpLoggingProperties.class)
@ConditionalOnProperty(prefix = "spring.http.logging", value = "enabled", havingValue = "true")
public class HttpLoggingAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public HttpLoggingFilter httpLoggingFilter(HttpLoggingProperties properties) {
        return new HttpLoggingFilter(properties);
    }
}
