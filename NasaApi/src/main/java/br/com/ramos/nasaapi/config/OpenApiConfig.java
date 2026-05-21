package br.com.ramos.nasaapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/**
 * Configura a documentação Swagger/OpenAPI.
 * Acesse em: http://localhost:8080/swagger-ui.html
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "NASA APOD API",
                version = "v1",
                description = "API RESTful para gerenciamento de imagens do NASA Astronomy Picture of the Day (APOD). " +
                        "Faça login em /api/v1/auth/login para obter um token JWT e autorize clicando em 'Authorize'.",
                contact = @Contact(name = "Ramos", email = "ramos@example.com"),
                license = @License(name = "MIT")
        ),
        servers = @Server(url = "http://localhost:8080", description = "Servidor local")
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = "Insira o token JWT obtido em /api/v1/auth/login"
)
public class OpenApiConfig {
}