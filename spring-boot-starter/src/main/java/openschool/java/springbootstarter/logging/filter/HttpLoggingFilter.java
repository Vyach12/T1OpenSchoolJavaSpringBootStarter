package openschool.java.springbootstarter.logging.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import openschool.java.springbootstarter.logging.config.HttpLoggingProperties;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class HttpLoggingFilter extends OncePerRequestFilter {

    private final HttpLoggingProperties properties;
    private final Logger logger = Logger.getLogger(HttpLoggingFilter.class.getName());

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain)
            throws ServletException, IOException {

        if (properties.isEnabled() && shouldLog(request.getRequestURI())) {
            logRequest(request);

            long startTime = System.currentTimeMillis();
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
        String logMessage = properties.getFormat().getRequest()
                .replace("{method}", request.getMethod())
                .replace("{headers}", getHeaders(request).toString())
                .replace("{url}", request.getRequestURI());

        logger.log(Level.parse(properties.getLevel()), logMessage);
    }

    private void logResponse(HttpServletResponse response, long duration) {
        String logMessage = properties.getFormat().getResponse()
                .replace("{status}", String.valueOf(response.getStatus()))
                .replace("{headers}", getHeaders(response).toString())
                .replace("{duration}", String.valueOf(duration));

        logger.log(Level.parse(properties.getLevel()), logMessage);
    }

    private List<String> getHeaders(HttpServletRequest request) {
        return Collections.list(request.getHeaderNames()).stream()
                .map(headerName -> headerName + ": " + request.getHeader(headerName))
                .toList();
    }

    private List<String> getHeaders(HttpServletResponse response) {
        return response.getHeaderNames().stream()
                .map(headerName -> headerName + ": " + response.getHeader(headerName))
                .toList();
    }
}
