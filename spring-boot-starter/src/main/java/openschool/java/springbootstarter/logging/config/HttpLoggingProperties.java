package openschool.java.springbootstarter.logging.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import openschool.java.springbootstarter.logging.exception.HttpLoggingStartupException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Параметры конфигурации для HTTP логирования.
 * Исключение:
 * При некорректной настройке выбрасывается {@link HttpLoggingStartupException}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "http.logging")
public class HttpLoggingProperties {
    /**
     * Разрешено ли логирование.
     */
    private boolean enabled = false;
    /**
     * Уровень логирования.
     * Допустимые значения: TRACE, DEBUG, INFO, WARNING, ERROR.
     * Выбрасывает HttpLoggingStartupException если будет указан некорретный уровень логирования.
     */
    private String level = "INFO";
    /**
     * Адрес, который нужно подвергнуть логированию.
     */
    private List<String> paths = new ArrayList<>();
    /**
     * Формат представления логов.
     */
    @NestedConfigurationProperty
    private HttpLogFormat format = new HttpLogFormat();
}