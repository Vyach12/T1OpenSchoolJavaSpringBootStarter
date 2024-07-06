package openschool.java.springbootstarter.logging.init;

import openschool.java.springbootstarter.logging.exception.HttpLoggingStartupException;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

public class HttpLoggingFailureAnalyzer extends AbstractFailureAnalyzer<HttpLoggingStartupException> {
    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, HttpLoggingStartupException cause) {
        return new FailureAnalysis(cause.getMessage(), "Please provide valid values for the properties", cause);
    }
}
