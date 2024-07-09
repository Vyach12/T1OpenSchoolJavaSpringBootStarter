package openschool.java.springbootstarter.logging.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class LogFormatterTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpRequest outgoingRequest;

    @Mock
    private ClientHttpResponse outgoingResponse;

    private LogFormatter logFormatter;

    @BeforeEach
    public void setUp() {
        logFormatter = new LogFormatter();
    }

    @Test
    public void testCreateRequestLog() {
        when(request.getMethod()).thenReturn("GET");
        when(request.getRequestURI()).thenReturn("/test");
        when(request.getHeaderNames()).thenReturn(Collections.enumeration(List.of("header1")));
        when(request.getHeader("header1")).thenReturn("value1");

        String expectedLog = "GET /test [header1: value1] ";
        String format = "{method} {url} {headers} {body}";
        String actualLog = logFormatter.createRequestLog(format, request);

        assertEquals(expectedLog, actualLog);
    }

    @Test
    public void testCreateResponseLog() {
        when(response.getStatus()).thenReturn(HttpStatus.OK.value());
        when(response.getHeaderNames()).thenReturn(List.of("header1"));
        when(response.getHeader("header1")).thenReturn("value1");

        String expectedLog = "200 [header1: value1]  123";
        String format = "{status} {headers} {body} {duration}";
        String actualLog = logFormatter.createResponseLog(format, response, 123);

        assertEquals(expectedLog, actualLog);
    }

    @Test
    public void testCreateOutgoingRequestLog() {
        when(outgoingRequest.getMethod()).thenReturn(HttpMethod.POST);
        when(outgoingRequest.getURI()).thenReturn(java.net.URI.create("http://example.com/test"));

        HttpHeaders headers = new HttpHeaders();
        headers.add("header1", "value1");
        when(outgoingRequest.getHeaders()).thenReturn(headers);

        byte[] body = "outgoing request body".getBytes(StandardCharsets.UTF_8);

        String format = "{method} {url} {headers} {body}";
        String expectedLog = "POST http://example.com/test [header1: value1] outgoing request body";
        String actualLog = logFormatter.createOutgoingRequestLog(format, outgoingRequest, body);

        assertEquals(expectedLog, actualLog);
    }

    @Test
    public void testCreateOutgoingResponseLog() throws IOException {
        when(outgoingResponse.getStatusCode()).thenReturn(HttpStatus.OK);

        HttpHeaders headers = new HttpHeaders();
        headers.add("header1", "value1");
        when(outgoingResponse.getHeaders()).thenReturn(headers);

        String format = "{status} {headers} {duration}";
        String expectedLog = "200 [header1: value1] 456";
        String actualLog = logFormatter.createOutgoingResponseLog(format, outgoingResponse, 456);

        assertEquals(expectedLog, actualLog);
    }
}