package openschool.java.springbootstarter.logging.init;

import openschool.java.springbootstarter.logging.exception.HttpLoggingStartupException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.mock.env.MockEnvironment;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class HttpLoggingEnvironmentPostProcessorTest {

    private final HttpLoggingEnvironmentPostProcessor postProcessor = new HttpLoggingEnvironmentPostProcessor();
    private final SpringApplication application = new SpringApplication();

    @Test
    void whenEnabledInvalid_thenThrowException() {
        ConfigurableEnvironment environment = new MockEnvironment()
                .withProperty("http.logging.enabled", "invalid_value");

        assertThrows(HttpLoggingStartupException.class, () ->
                postProcessor.postProcessEnvironment(environment, application));
    }

    @Test
    void whenEnabledTrueAndLevelInvalid_thenThrowException() {
        ConfigurableEnvironment environment = new MockEnvironment()
                .withProperty("http.logging.enabled", "true")
                .withProperty("http.logging.level", "INVALID");

        assertThrows(HttpLoggingStartupException.class, () ->
                postProcessor.postProcessEnvironment(environment, application));
    }

    @Test
    void whenEnabledFalse_thenDoNotCheckLevel() {
        ConfigurableEnvironment environment = new MockEnvironment()
                .withProperty("http.logging.enabled", "false")
                .withProperty("http.logging.level", "INVALID");

        postProcessor.postProcessEnvironment(environment, application);
    }

    @Test
    void whenValidProperties_thenNoException() {
        ConfigurableEnvironment environment = new MockEnvironment()
                .withProperty("http.logging.enabled", "true")
                .withProperty("http.logging.level", "INFO");

        postProcessor.postProcessEnvironment(environment, application);
    }
}
