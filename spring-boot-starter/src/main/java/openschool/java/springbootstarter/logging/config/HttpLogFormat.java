package openschool.java.springbootstarter.logging.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Формат логирования HTTP запросов и ответов.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpLogFormat {
    private String request = "Request: url={url} method={method} headers={headers}";
    private String response = "Response: headers={headers} status={status} duration={duration}ms";
}