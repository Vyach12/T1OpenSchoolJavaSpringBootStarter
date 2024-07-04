package openschool.java.springbootstarter.logging.config;

import openschool.java.springbootstarter.logging.filter.HttpLoggingFilter;
import openschool.java.springbootstarter.logging.interceptor.HttpLoggingInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Автоконфигурация для логирования HTTP запросов.
 * Конфигурирует фильтр и интерцептор для логирования.
 */
@Configuration
public class LoggingAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public HttpLoggingFilter httpLoggingFilter() {
        return new HttpLoggingFilter();
    }

    @Bean
    @ConditionalOnMissingBean
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        return new HttpLoggingInterceptor();
    }
}
