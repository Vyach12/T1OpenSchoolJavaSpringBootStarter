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
 *
 * Поддерживаемые переменные в форматах логов:
 *
 * <ul>
 *   <li>{method}: HTTP метод запроса (GET, POST и т.д.)</li>
 *   <li>{url}: URL запроса</li>
 *   <li>{headers}: Заголовки запроса или ответа</li>
 *   <li>{body}: Тело запроса или ответа (если есть)</li>
 *   <li>{status}: HTTP статус ответа (200, 404 и т.д.)</li>
 *   <li>{duration}: Время обработки запроса в миллисекундах</li>
 * </ul>
 *
 * Пример форматов логов:
 *
 * <pre>
 * http.logging.log-format.request=Запрос: url={url} method={method} headers={headers} body={body}
 * http.logging.log-format.response=Ответ: headers={headers} status={status} duration={duration}ms body={body}
 * </pre>
 *
 * Исключение:
 * При некорректной настройке выбрасывается {@link HttpLoggingStartupException}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "http.logging")
public class HttpLoggingProperties {
    private boolean enabled = false;
    private String level = "INFO";
    private List<String> paths = new ArrayList<>();
    @NestedConfigurationProperty
    private HttpLogFormat format = new HttpLogFormat();
}