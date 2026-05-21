# 📦 NASA API RESTful

API RESTful desenvolvida em **Java + Spring Boot**, com autenticação JWT, segurança via Spring Security, rate limiting e banco de dados em memória H2.

---

## 🚀 Tecnologias utilizadas

- Java 17+
- Spring Boot 3.x
- Spring Security
- JWT (Json Web Token)
- H2 Database
- Spring Data JPA
- Swagger / OpenAPI
- Tomcat Embedded

---

## 📌 Funcionalidades

- 🔐 Autenticação com JWT
- 👤 Login de usuário
- 🧾 API RESTful estruturada
- 🛡️ Segurança com filtros customizados
- ⛔ Rate Limiting (controle de requisições)
- 🗃️ Banco de dados H2 em memória
- 📄 Documentação automática com Swagger

---

## 🔑 Autenticação

### Login

**Endpoint:**
```
POST /api/v1/auth/login
```

**Body (JSON):**
```json
{
  "username": "seu_usuario",
  "password": "sua_senha"
}
```

**Response:**
```json
{
  "token": "jwt-token-gerado",
  "type": "Bearer",
  "username": "seu_usuario"
}
```

### 🧪 Como usar o Token

Após o login, utilize o token no header de todas as requisições protegidas:

```
Authorization: Bearer SEU_TOKEN_AQUI
```

---

## 🌐 Endpoints principais

### 🔐 Auth

| Método | Endpoint              | Descrição                          |
|--------|-----------------------|------------------------------------|
| POST   | `/api/v1/auth/login`  | Autenticação e geração de token    |

---

## 📚 Swagger — Documentação da API

Acesse a documentação interativa em:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🗄️ H2 Database Console

Banco de dados em memória disponível em:

```
http://localhost:8080/h2-console
```

**Configuração padrão:**

| Campo    | Valor                    |
|----------|--------------------------|
| JDBC URL | `jdbc:h2:mem:testdb`     |
| User     | `sa`                     |
| Password | *(vazio ou conforme `application.properties`)* |

---

## ⚙️ Como executar o projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/seu-usuario/nasa-api.git
```

### 2. Entrar na pasta

```bash
cd nasa-api
```

### 3. Rodar o projeto

```bash
./mvnw spring-boot:run
```

ou

```bash
mvn spring-boot:run
```

---

## 🔐 Segurança — Resumo da arquitetura

O sistema possui:

- **JwtAuthFilter** → intercepta requisições e valida o token
- **RateLimitFilter** → limita o número de requisições por IP
- **Spring Security** → controla o acesso aos endpoints
- **Sessão stateless** (sem autenticação via sessão/cookie)

### 🧠 Fluxo de autenticação

```
1. Usuário envia credenciais via login
2. Sistema valida as credenciais
3. JWT é gerado e retornado
4. Cliente envia o token nas próximas requisições
5. Filter valida o token antes de acessar os endpoints
```

---

## ⚠️ Possíveis erros comuns

### Token inválido ou ausente
Verifique se o header está correto:
```
Authorization: Bearer <token>
```

### Username null no login
Falha na autenticação ou no `UserDetailsService`. Confirme que o usuário existe na base de dados.

### 403 Forbidden
Token inválido, expirado ou endpoint protegido sem autenticação adequada.

---

## 📁 Estrutura do projeto

```
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
```

---

## 👨‍💻 Autor

Projeto desenvolvido por **Kaick Ramos de Melo Silva**
