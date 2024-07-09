package openschool.java.springbootstarter.logging.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import openschool.java.springbootstarter.logging.config.HttpLoggingProperties;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * Класс для генерации строк логов HTTP запросов и ответов.
 * <p>
 * Этот класс отвечает за генерацию строк логов входящих HTTP запросов и исходящих ответов
 * в соответствии с настройками, заданными в {@link HttpLoggingProperties}.
 * </p>
 */
@RequiredArgsConstructor
public class LogFormatter {
    /**
     * Генерирует строку лога для HTTP запроса.
     *
     * @param request HTTP запрос
     * @return Сгенерированная строка лога для запроса
     */
    public String createRequestLog(String formatRequest, HttpServletRequest request) {
        var content = new ContentCachingRequestWrapper(request);
        String requestBody = new String(content.getContentAsByteArray(), StandardCharsets.UTF_8);

        return formatRequest
                .replace("{method}", request.getMethod())
                .replace("{headers}", getHeaders(request).toString())
                .replace("{body}", requestBody)
                .replace("{url}", request.getRequestURI());
    }

    /**
     * Генерирует строку лога для HTTP ответа.
     *
     * @param response HTTP ответ
     * @param duration Длительность обработки запроса в миллисекундах
     * @return Сгенерированная строка лога для ответа
     */
    public String createResponseLog(String formatResponse, HttpServletResponse response, long duration) {
        var content = new ContentCachingResponseWrapper(response);
        String responseBody = new String(content.getContentAsByteArray(), StandardCharsets.UTF_8);

        return formatResponse
                .replace("{status}", String.valueOf(response.getStatus()))
                .replace("{headers}", getHeaders(response).toString())
                .replace("{body}", responseBody)
                .replace("{duration}", String.valueOf(duration));
    }

    /**
     * Генерирует строку лога для исходящего HTTP запроса.
     *
     * @param formatRequest Шаблон для форматирования запроса
     * @param request       Исходящий HTTP запрос
     * @param body          Тело исходящего запроса
     * @return Сгенерированная строка лога для запроса
     */
    public String createOutgoingRequestLog(String formatRequest, HttpRequest request, byte[] body) {
        String requestBody = new String(body, StandardCharsets.UTF_8);

        return formatRequest
                .replace("{method}", request.getMethod().toString())
                .replace("{headers}", getHeaders(request).toString())
                .replace("{body}", requestBody)
                .replace("{url}", request.getURI().toString());
    }

    /**
     * Генерирует строку лога для исходящего HTTP ответа.
     *
     * @param formatResponse Шаблон для форматирования ответа
     * @param response       Исходящий HTTP ответ
     * @return Сгенерированная строка лога для ответа
     */
    @SneakyThrows
    public String createOutgoingResponseLog(String formatResponse,
                                            ClientHttpResponse response,
                                            long durationMS) {

        return formatResponse.replace("{status}", String.valueOf(response.getStatusCode().value()))
                .replace("{headers}", getHeaders(response).toString())
                .replace("{duration}", String.valueOf(durationMS));
    }

    /**
     * Возвращает список HTTP заголовков запроса.
     *
     * @param request HTTP запрос
     * @return Список строк, представляющих заголовки запроса
     */
    private List<String> getHeaders(HttpServletRequest request) {
        return Collections.list(request.getHeaderNames()).stream()
                .map(headerName -> headerName + ": " + request.getHeader(headerName))
                .toList();
    }

    /**
     * Возвращает список HTTP заголовков ответа.
     *
     * @param response HTTP ответ
     * @return Список строк, представляющих заголовки ответа
     */
    private List<String> getHeaders(HttpServletResponse response) {
        return response.getHeaderNames().stream()
                .map(headerName -> headerName + ": " + response.getHeader(headerName))
                .toList();
    }

    /**
     * Возвращает список HTTP заголовков для исходящего запроса.
     *
     * @param request Исходящий HTTP запрос
     * @return Список строк, представляющих заголовки запроса
     */
    private List<String> getHeaders(HttpRequest request) {
        return request.getHeaders().entrySet().stream()
                .map(entry -> entry.getKey() + ": " + String.join(", ", entry.getValue()))
                .toList();
    }

    /**
     * Возвращает список HTTP заголовков для ответа от исходящего запроса.
     *
     * @param response Исходящий HTTP ответ
     * @return Список строк, представляющих заголовки ответа
     */
    private List<String> getHeaders(ClientHttpResponse response) {
        return response.getHeaders().entrySet().stream()
                .map(entry -> entry.getKey() + ": " + String.join(", ", entry.getValue()))
                .toList();
    }
}
