package br.com.ramos.nasaapi.filter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Rate limiter por IP usando Bucket4j (token bucket algorithm).
 * Limite: 60 requisições por minuto por IP.
 */
@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(RateLimitFilter.class);

    // Mapa de buckets por IP (em produção, usar Redis/cache distribuído)
    private final ConcurrentHashMap<String, Bucket> buckets = new ConcurrentHashMap<>();

    private Bucket resolveBucket(String ip) {
        return buckets.computeIfAbsent(ip, key -> {
            Bandwidth limit = Bandwidth.builder()
                    .capacity(60)
                    .refillGreedy(60, Duration.ofMinutes(1))
                    .build();
            return Bucket.builder().addLimit(limit).build();
        });
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        // Só aplica rate limit nas rotas da API
        if (!request.getRequestURI().startsWith("/api/")) {
            chain.doFilter(request, response);
            return;
        }

        String ip = getClientIp(request);
        Bucket bucket = resolveBucket(ip);

        if (bucket.tryConsume(1)) {
            response.addHeader("X-RateLimit-Remaining",
                    String.valueOf(bucket.getAvailableTokens()));
            chain.doFilter(request, response);
        } else {
            log.warn("Rate limit excedido para IP: {}", ip);
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(
                    "{\"success\":false,\"message\":\"Muitas requisições. Tente novamente em 1 minuto.\",\"timestamp\":\""
                            + java.time.LocalDateTime.now() + "\"}"
            );
        }
    }

    private String getClientIp(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            return xForwardedFor.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}