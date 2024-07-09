package openschool.java.springbootstarter.logging.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Формат логирования HTTP запросов и ответов.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpLogFormat {
    /**
     * Формат вывода логов для входящего http запроса.
     * Поддерживаемые переменные в форматах логов запроса:
     *
     * <ul>
     *   <li>{method}: HTTP метод запроса (GET, POST и т.д.)</li>
     *   <li>{url}: URL запроса</li>
     *   <li>{headers}: Заголовки запроса или ответа</li>
     *   <li>{body}: Тело запроса (если есть)</li>
     * </ul>
     * <p>
     * Пример форматов логов:
     *
     * <pre>
     * http.logging.log-format.incoming-request=Запрос: url={url} method={method} headers={headers} body={body}
     * </pre>
     */
    private String incomingRequest = "Incoming Request: url={url} method={method} headers={headers} body={body}";
    /**
     * Формат вывода логов для входящего http ответа.
     * Поддерживаемые переменные в форматах логов ответа:
     * <ul>
     *   <li>{headers}: Заголовки запроса или ответа</li>
     *   <li>{body}: Тело ответа (если есть)</li>
     *   <li>{status}: HTTP статус ответа</li>
     *   <li>{duration}: Время обработки запроса в миллисекундах</li>
     * </ul>
     * <p>
     * Пример форматов логов:
     *
     * <pre>
     * http.logging.log-format.incoming-response=Ответ: headers={headers} status={status} duration={duration}ms body={body}
     * </pre>
     */
    private String incomingResponse = "Incoming Response: headers={headers} body={body} status={status} duration={duration}ms";
    /**
     * Формат вывода логов для исходящего http запроса.
     * Поддерживаемые переменные в форматах логов запроса:
     *
     * <ul>
     *   <li>{method}: HTTP метод запроса (GET, POST и т.д.)</li>
     *   <li>{url}: URL запроса</li>
     *   <li>{headers}: Заголовки запроса или ответа</li>
     *   <li>{body}: Тело запроса (если есть)</li>
     * </ul>
     * <p>
     * Пример форматов логов:
     *
     * <pre>
     * http.logging.log-format.outgoing-request=Запрос: url={url} method={method} headers={headers} body={body}
     * </pre>
     */
    private String outgoingRequest = "Outgoing Request: url={url} method={method} headers={headers} body={body}";
    /**
     * Формат вывода логов для http ответа исходящего запроса.
     * Поддерживаемые переменные в форматах логов ответа:
     * <ul>
     *   <li>{headers}: Заголовки запроса или ответа</li>
     *   <li>{status}: HTTP статус ответа</li>
     *   <li>{duration}: Время обработки запроса в миллисекундах</li>
     * </ul>
     * <p>
     * Пример форматов логов:
     *
     * <pre>
     * http.logging.log-format.outgoing-response=Ответ: headers={headers} status={status} duration={duration}ms body={body}
     * </pre>
     */
    private String outgoingResponse = "Outgoing Response: headers={headers} status={status} duration={duration}ms";

}