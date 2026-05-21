📦 NASA API RESTful

API RESTful desenvolvida em Java + Spring Boot, com autenticação JWT, segurança via Spring Security, rate limiting e banco de dados em memória H2.

🚀 Tecnologias utilizadas
Java 17+
Spring Boot 3.x
Spring Security
JWT (Json Web Token)
H2 Database
Spring Data JPA
Swagger / OpenAPI
Tomcat Embedded
📌 Funcionalidades
🔐 Autenticação com JWT
👤 Login de usuário
🧾 API RESTful estruturada
🛡️ Segurança com filtros customizados
⛔ Rate Limiting (controle de requisições)
🗃️ Banco de dados H2 em memória
📄 Documentação automática com Swagger
🔑 Autenticação
Login

Endpoint:

POST /api/v1/auth/login
Body (JSON)
{
  "username": "seu_usuario",
  "password": "sua_senha"
}
Response
{
  "token": "jwt-token-gerado",
  "type": "Bearer",
  "username": "seu_usuario"
}
🧪 Como usar o Token

Após o login, utilize o token no header:

Authorization: Bearer SEU_TOKEN_AQUI
🌐 Endpoints principais
🔐 Auth
Método	Endpoint	Descrição
POST	/api/v1/auth/login	Autenticação e geração de token
📚 Swagger (Documentação da API)

Acesse a documentação interativa:

http://localhost:8080/swagger-ui/index.html
🗄️ H2 Database Console

Banco de dados em memória:

http://localhost:8080/h2-console
Configuração padrão:
JDBC URL: jdbc:h2:mem:testdb
User: sa
Password: (vazio ou conforme application.properties)
⚙️ Como executar o projeto
1. Clonar o repositório
git clone https://github.com/seu-usuario/nasa-api.git
2. Entrar na pasta
cd nasa-api
3. Rodar o projeto
./mvnw spring-boot:run

ou

mvn spring-boot:run
🔐 Segurança (Resumo da arquitetura)

O sistema possui:

JwtAuthFilter → intercepta requisições e valida token
RateLimitFilter → limita número de requisições por IP
Spring Security → controla acesso aos endpoints
Stateless session (sem login via sessão)
🧠 Fluxo de autenticação
Usuário envia login
Sistema valida credenciais
JWT é gerado
Cliente envia token nas próximas requisições
Filter valida token antes de acessar endpoints
⚠️ Possíveis erros comuns
Token inválido ou ausente
Verifique o header:
Authorization: Bearer <token>
Username null no login
Falha na autenticação ou no UserDetailsService
403 Forbidden
Token inválido ou endpoint protegido
📁 Estrutura do projeto (resumo)
src/main/java/br/com/ramos/nasaapi
│
├── controller
├── service
├── security
│   ├── JwtAuthFilter
│   ├── JwtService
│   └── SecurityConfig
├── filter
│   └── RateLimitFilter
├── repository
├── model
└── config
👨‍💻 Autor

Projeto desenvolvido por Kaick Ramos de Melo Silva
