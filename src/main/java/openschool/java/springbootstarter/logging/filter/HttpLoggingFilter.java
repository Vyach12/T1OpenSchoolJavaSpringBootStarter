package openschool.java.springbootstarter.logging.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import openschool.java.springbootstarter.logging.config.HttpLoggingProperties;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class HttpLoggingFilter extends OncePerRequestFilter {

    private final HttpLoggingProperties properties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (properties.isEnabled() && shouldLog(request.getRequestURI())) {
            long startTime = System.currentTimeMillis();

            logRequest(request);

            filterChain.doFilter(request, response);

            long duration = System.currentTimeMillis() - startTime;
            logResponse(response, duration);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private boolean shouldLog(String requestURI) {
        List<String> paths = properties.getPaths();
        if (paths == null || paths.isEmpty()) {
            return true;
        }
        return paths.stream().anyMatch(requestURI::startsWith);
    }

    private void logRequest(HttpServletRequest request) {
        log.info("Request: method={}, uri={}, headers={}",
                request.getMethod(),
                request.getRequestURI(),
                getHeaders(request));
    }

    private void logResponse(HttpServletResponse response, long duration) {
        log.info("Response: status={}, headers={}, duration={}ms",
                response.getStatus(),
                getHeaders(response),
                duration);
    }

    //TODO: Глянуть есть ли решение без приведений
    private String getHeaders(HttpServletRequest request) {
        return Collections.list(request.getHeaderNames()).stream()
                .map(headerName -> headerName + ": " + request.getHeader(headerName))
                .collect(Collectors.joining(", "));
    }

    private String getHeaders(HttpServletResponse response) {
        return response.getHeaderNames().stream()
                .map(headerName -> headerName + ": " + response.getHeader(headerName))
                .toString();
    }
}
