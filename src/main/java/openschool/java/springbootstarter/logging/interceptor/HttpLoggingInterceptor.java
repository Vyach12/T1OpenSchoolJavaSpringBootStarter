package openschool.java.springbootstarter.logging.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Интерцептор для логирования HTTP запросов и ответов.
 */
@Component
@Slf4j
public class HttpLoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        long duration = System.currentTimeMillis() - startTime;

        logRequest(request, response, duration);
    }

    /**
     * Логирует информацию о HTTP запросе и ответе.
     *
     * @param request  HTTP запрос
     * @param response HTTP ответ
     * @param duration время обработки запроса в миллисекундах
     */
    private void logRequest(HttpServletRequest request,
                            HttpServletResponse response,
                            long duration) {
        String method = request.getMethod();
        String url = request.getRequestURL().toString();
        int status = response.getStatus();

        // Логирование запроса и ответа
        System.out.printf("Method: %s, URL: %s, Status: %d, Duration: %d ms%n", method, url, status, duration);
    }
}
