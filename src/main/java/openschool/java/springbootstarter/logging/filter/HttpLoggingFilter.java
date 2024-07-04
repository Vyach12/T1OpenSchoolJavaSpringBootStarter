package openschool.java.springbootstarter.logging.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class HttpLoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {

        long startTime = System.currentTimeMillis();

        filterChain.doFilter(request, response);

        long duration = System.currentTimeMillis() - startTime;
        logRequest(request, response, duration);
    }

    private void logRequest(final HttpServletRequest request,
                            final HttpServletResponse response,
                            long duration) {
        String method = request.getMethod();
        String url = request.getRequestURL().toString();
        int status = response.getStatus();

        // Логирование запроса и ответа
        log.info("Method:{}, URL: {}, Status: {}, Duration: {} ms%n", method, url, status, duration);
    }
}
