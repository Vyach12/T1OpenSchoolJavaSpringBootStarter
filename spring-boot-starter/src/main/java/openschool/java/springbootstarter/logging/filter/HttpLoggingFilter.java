package openschool.java.springbootstarter.logging.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import openschool.java.springbootstarter.logging.config.HttpLoggingProperties;
import openschool.java.springbootstarter.logging.util.LogFormatter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Фильтр для логирования HTTP запросов и ответов.
 * <p>
 * Фильтр логирует входящие HTTP запросы и исходящие ответы, если они соответствуют заданным критериям.
 * Логирование выполняется один раз для каждого запроса.
 * </p>
 */
@RequiredArgsConstructor
public class HttpLoggingFilter extends OncePerRequestFilter {

    private final HttpLoggingProperties properties;
    private final LogFormatter logFormatter;
    private final Logger logger = Logger.getLogger(HttpLoggingFilter.class.getName());

    /**
     * Основная логика фильтрации.
     * <p>
     * Этот метод обертывает стандартную обработку запроса и ответа, добавляя логирование
     * при выполнении условий, указанных в {@link HttpLoggingProperties}.
     * </p>
     *
     * @param request     HTTP запрос
     * @param response    HTTP ответ
     * @param filterChain Цепочка фильтров
     * @throws ServletException Если произошла ошибка сервлета
     * @throws IOException      Если произошла ошибка ввода/вывода
     */
    @Override
    protected void doFilterInternal(@NonNull final HttpServletRequest request,
                                    @NonNull final HttpServletResponse response,
                                    @NonNull final FilterChain filterChain)
            throws ServletException, IOException {

        if (properties.isEnabled() && shouldLog(request.getRequestURI())) {

            Level logLevel = Level.parse(properties.getLevel());

            String requestLog = logFormatter.createRequestLog(properties.getFormat().getRequest(), request);
            logger.log(logLevel, requestLog);

            long startTime = System.currentTimeMillis();
            filterChain.doFilter(request, response);
            long durationMs = System.currentTimeMillis() - startTime;

            String responseLog = logFormatter.createResponseLog(properties.getFormat().getResponse(), response, durationMs);
            logger.log(logLevel, responseLog);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    /**
     * Определяет, нужно ли логировать запрос на основе его URI.
     *
     * @param requestURI URI HTTP запроса
     * @return true, если запрос должен быть залогирован, иначе false
     */
    private boolean shouldLog(String requestURI) {
        List<String> paths = properties.getPaths();
        if (paths == null || paths.isEmpty()) {
            return true;
        }
        return paths.stream().anyMatch(requestURI::startsWith);
    }
}
