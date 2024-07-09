package openschool.java.springbootstarter.logging.intercept;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import openschool.java.springbootstarter.logging.config.HttpLoggingProperties;
import openschool.java.springbootstarter.logging.filter.HttpLoggingFilter;
import openschool.java.springbootstarter.logging.util.LogFormatter;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class HttpLoggingInterceptor implements ClientHttpRequestInterceptor {
    private final LogFormatter logFormatter;
    private final HttpLoggingProperties properties;
    private final Logger logger = Logger.getLogger(HttpLoggingFilter.class.getName());

    @Override
    @NonNull
    public ClientHttpResponse intercept(@NonNull HttpRequest request,
                                        byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        String requestLog = logFormatter.createOutgoingRequestLog(
                properties.getFormat().getOutgoingRequest(),
                request,
                body
        );
        Level logLevel = Level.parse(properties.getLevel());

        logger.log(logLevel, requestLog);

        ClientHttpResponse response = execution.execute(request, body);

        String responseLog = logFormatter.createOutgoingResponseLog(
                properties.getFormat().getOutgoingResponse(),
                response
        );
        logger.log(logLevel, responseLog);

        return response;
    }
}
