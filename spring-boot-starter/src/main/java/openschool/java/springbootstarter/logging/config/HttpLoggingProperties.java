package openschool.java.springbootstarter.logging.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;

@ConfigurationProperties(prefix = "http.logging")
public record HttpLoggingProperties(
        boolean enabled,
        @DefaultValue("INFO") String logLevel,
        List<String> paths,
        @DefaultValue("{method} {url} {status}") String logFormat
) {
}
