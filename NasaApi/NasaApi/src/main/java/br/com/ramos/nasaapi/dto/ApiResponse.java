package br.com.ramos.nasaapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Envelope de resposta padronizado para toda a API.
 * Garante consistência em todas as respostas JSON.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private List<String> errors;
    private LocalDateTime timestamp;
    private PageMeta page;

    private ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    // Resposta de sucesso com dados
    public static <T> ApiResponse<T> ok(T data) {
        ApiResponse<T> r = new ApiResponse<>();
        r.success = true;
        r.data = data;
        return r;
    }

    // Resposta de sucesso com dados e mensagem
    public static <T> ApiResponse<T> ok(String message, T data) {
        ApiResponse<T> r = new ApiResponse<>();
        r.success = true;
        r.message = message;
        r.data = data;
        return r;
    }

    // Resposta de sucesso com paginação
    public static <T> ApiResponse<T> ok(T data, PageMeta page) {
        ApiResponse<T> r = new ApiResponse<>();
        r.success = true;
        r.data = data;
        r.page = page;
        return r;
    }

    // Resposta de erro
    public static <T> ApiResponse<T> error(String message) {
        ApiResponse<T> r = new ApiResponse<>();
        r.success = false;
        r.message = message;
        return r;
    }

    // Resposta de erro com lista de erros de validação
    public static <T> ApiResponse<T> error(String message, List<String> errors) {
        ApiResponse<T> r = new ApiResponse<>();
        r.success = false;
        r.message = message;
        r.errors = errors;
        return r;
    }

    // Getters
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public T getData() { return data; }
    public List<String> getErrors() { return errors; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public PageMeta getPage() { return page; }

    /**
     * Metadados de paginação incluídos em respostas de listas.
     */
    public record PageMeta(int number, int size, long totalElements, int totalPages) {}
}