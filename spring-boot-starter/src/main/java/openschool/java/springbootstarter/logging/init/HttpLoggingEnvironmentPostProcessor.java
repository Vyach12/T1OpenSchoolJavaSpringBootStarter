package openschool.java.springbootstarter.logging.init;

import lombok.extern.slf4j.Slf4j;
import openschool.java.springbootstarter.logging.exception.HttpLoggingStartupException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * EnvironmentPostProcessor для проверки и установки значений параметров конфигурации
 * логирования HTTP запросов и ответов.
 */
@Slf4j
public class HttpLoggingEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        log.info("Calling HttpLoggingEnvironmentPostProcessor");

        String enabled = environment.getProperty("http.logging.enabled");
        String logLevel = environment.getProperty("http.logging.level");

        if (enabled != null && !Boolean.TRUE.toString().equals(enabled) && !Boolean.FALSE.toString().equals(enabled)) {
            throw new HttpLoggingStartupException("Invalid value for 'http.logging.enabled'");
        }
        // В случае, если будет отключено логирование не проверять след параметры
        if(Boolean.TRUE.toString().equals(enabled)) {
            if (logLevel != null && !logLevel.matches("TRACE|DEBUG|INFO|WARNING|ERROR")) {
                throw new HttpLoggingStartupException("Invalid log level: " + logLevel);
            }
        }
    }
}
