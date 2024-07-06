package openschool.java.springbootstarter.logging.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "spring.http.logging")
public class HttpLoggingProperties {
    private boolean enabled = false;
    private String logLevel = "INFO";
    private List<String> paths;
}
