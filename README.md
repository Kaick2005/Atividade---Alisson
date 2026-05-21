# 🚀 NASA APOD API — RESTful com Spring Boot
 
API RESTful desenvolvida em **Java + Spring Boot** que consome a API pública da NASA (Astronomy Picture of the Day) e oferece um CRUD completo com autenticação JWT, autorização por perfis, paginação, filtros, rate limiting e documentação automática.
 
---
 
## 🛠️ Tecnologias utilizadas
 
- Java 17
- Spring Boot 3.2.5
- Spring Security 6
- JWT (JJWT 0.12.5)
- H2 Database (banco em memória)
- Spring Data JPA + Hibernate
- Bucket4j (rate limiting)
- Springdoc OpenAPI / Swagger UI
- Spring Boot Actuator
- Maven
---
 
## 📌 Funcionalidades
 
- 🔐 Autenticação real com JWT (login gera token de verdade)
- 👥 Dois perfis de acesso: `ROLE_USER` e `ROLE_ADMIN`
- 🌌 Busca automática da imagem do dia da NASA (APOD)
- 📋 CRUD completo de imagens (Create, Read, Update, Delete)
- 🔍 Filtro por título e ordenação nos resultados
- 📄 Paginação nas listagens
- 🛡️ Rate Limiting: 60 requisições por minuto por IP
- ❌ Tratamento de erros padronizado em todas as rotas
- ✅ Validação de dados de entrada com mensagens de erro claras
- 📚 Documentação interativa via Swagger UI
- 📊 Monitoramento via Spring Actuator
- 🗃️ Console visual do banco H2
---
 
## 👥 Usuários padrão (criados automaticamente)
 
| Usuário | Senha         | Perfil                  | Permissões                           |
|---------|---------------|-------------------------|--------------------------------------|
| user    | password123   | ROLE_USER               | GET e POST                           |
| admin   | admin123      | ROLE_USER + ROLE_ADMIN  | GET, POST, PUT, PATCH e DELETE       |
 
---
 
## 🌐 Endpoints da API
 
### 🔑 Autenticação — /api/v1/auth
 
| Método | Endpoint              | Autenticação | Descrição                    |
|--------|-----------------------|--------------|------------------------------|
| POST   | /api/v1/auth/login    | Pública      | Login e geração do token JWT |
 
**Body do login:**
```json
{
  "username": "user",
  "password": "password123"
}
```
 
**Resposta:**
```json
{
  "token": "eyJhbGci...",
  "type": "Bearer",
  "username": "user"
}
```
 
---
 
### 🌌 Imagens NASA — /api/v1/images
 
| Método | Endpoint               | Role mínima | Descrição                           |
|--------|------------------------|-------------|-------------------------------------|
| POST   | /api/v1/images/fetch   | USER        | Busca imagem do dia na NASA e salva |
| GET    | /api/v1/images         | USER        | Lista imagens com paginação e filtro|
| GET    | /api/v1/images/{id}    | USER        | Busca imagem por ID                 |
| PUT    | /api/v1/images/{id}    | ADMIN       | Atualiza imagem completa            |
| PATCH  | /api/v1/images/{id}    | ADMIN       | Atualiza imagem parcialmente        |
| DELETE | /api/v1/images/{id}    | ADMIN       | Remove imagem                       |
 
**Parâmetros de listagem (GET /api/v1/images):**
 
| Parâmetro | Exemplo           | Descrição          |
|-----------|-------------------|--------------------|
| title     | ?title=nebula     | Filtra pelo título |
| page      | ?page=0           | Número da página   |
| size      | ?size=5           | Itens por página   |
| sort      | ?sort=date,desc   | Ordenação          |
 
---
 
### 📊 Monitoramento — /actuator
 
| Endpoint            | Descrição                |
|---------------------|--------------------------|
| /actuator/health    | Status da aplicação      |
| /actuator/metrics   | Métricas do sistema      |
| /actuator/info      | Informações da aplicação |
 
---
 
## 📋 Estrutura de resposta padronizada
 
Todas as respostas seguem o mesmo envelope JSON:
 
```json
{
  "success": true,
  "message": "Imagem salva com sucesso",
  "data": { ... },
  "timestamp": "2026-05-20T22:57:17"
}
```
 
Em caso de erro:
```json
{
  "success": false,
  "message": "NasaImage não encontrado com id: 99",
  "timestamp": "2026-05-20T22:57:17"
}
```
 
---
 
## 📁 Estrutura do projeto
 
```
src/main/java/br/com/ramos/nasaapi/
│
├── NasaApiApplication.java          Ponto de entrada da aplicação
│
├── client/
│   └── NasaClient.java              Consome a API externa da NASA
│
├── config/
│   ├── DataInitializer.java         Cria usuários padrão no banco
│   └── OpenApiConfig.java           Configuração do Swagger
│
├── controller/
│   ├── AuthController.java          Endpoint de login
│   └── NasaController.java          CRUD de imagens
│
├── dto/
│   ├── ApiResponse.java             Envelope de resposta padronizado
│   ├── AuthRequest.java             Dados de entrada do login
│   ├── AuthResponse.java            Resposta do login com token
│   ├── NasaImageRequest.java        Dados de entrada para imagens
│   └── NasaImageResponse.java       Dados de saída para imagens
│
├── exception/
│   ├── GlobalExceptionHandler.java  Trata todos os erros da API
│   ├── ResourceNotFoundException    Erro 404
│   └── DuplicateResourceException   Erro 409
│
├── filter/
│   └── RateLimitFilter.java         Limita 60 requisições por minuto por IP
│
├── model/
│   ├── NasaImage.java               Entidade da imagem (tabela nasa_images)
│   └── User.java                    Entidade do usuário (tabela users)
│
├── repository/
│   ├── NasaImageRepository.java     Acesso ao banco para imagens
│   └── UserRepository.java          Acesso ao banco para usuários
│
├── security/
│   ├── SecurityConfig.java          Regras de acesso e cadeia de filtros
│   ├── JwtAuthFilter.java           Valida o token em cada requisição
│   ├── JwtUtil.java                 Gera e valida tokens JWT
│   └── CustomUserDetailsService     Carrega usuário do banco
│
└── service/
    └── NasaService.java             Lógica de negócio das imagens
```
 
---
 
## ⚙️ Como executar o projeto
 
### Pré-requisitos
- Java 17 instalado
- IntelliJ IDEA (recomendado) ou qualquer IDE Java
### Executar pelo IntelliJ
1. Abra o projeto no IntelliJ
2. Aguarde o Maven baixar as dependências
3. Abra o arquivo `NasaApiApplication.java`
4. Clique no botão verde ▶ ao lado da classe e selecione **Run**
5. Aguarde aparecer no console: `Started NasaApiApplication`
### Executar pelo terminal
```bash
cd NasaApi
mvnw.cmd spring-boot:run   # Windows
./mvnw spring-boot:run     # Linux ou Mac
```
 
---
 
## 🧪 Sequência de testes pelo Swagger
 
Acesse: **http://localhost:8080/swagger-ui/index.html**
 
**Passo 1 — Login**
- Clique em `POST /api/v1/auth/login` → Try it out
- Cole o body com usuário e senha → Execute
- Copie o valor do campo `token` na resposta
**Passo 2 — Autenticar**
- Clique no cadeado 🔒 Authorize no topo da página
- Cole apenas o token (sem a palavra Bearer) → Authorize → Close
**Passo 3 — Testar os endpoints**
1. `POST /api/v1/images/fetch` → busca imagem da NASA e salva
2. `GET /api/v1/images` → lista todas as imagens
3. `GET /api/v1/images/1` → busca a imagem de ID 1
4. `GET /api/v1/images?title=wolf` → filtra por título
5. Faça login com admin/admin123 e repita o Authorize
6. `PUT /api/v1/images/1` → atualiza a imagem completa
7. `PATCH /api/v1/images/1` → atualiza só um campo
8. `DELETE /api/v1/images/1` → remove a imagem
---
 
## 🗄️ Console do banco H2
 
Acesse: **http://localhost:8080/h2-console**
 
| Campo    | Valor              |
|----------|--------------------|
| JDBC URL | jdbc:h2:mem:nasa   |
| User     | SA                 |
| Password | (deixar vazio)     |
 
O banco é em memória — os dados são apagados ao reiniciar a aplicação.
 
---
 
## ⚠️ Códigos de status utilizados
 
| Código | Quando acontece                               |
|--------|-----------------------------------------------|
| 200    | Requisição bem-sucedida                       |
| 201    | Recurso criado com sucesso                    |
| 204    | Deletado com sucesso                          |
| 400    | Dados inválidos no corpo da requisição        |
| 401    | Token ausente, inválido ou expirado           |
| 403    | Token válido mas sem permissão (role)         |
| 404    | Imagem não encontrada pelo ID                 |
| 409    | Recurso duplicado                             |
| 429    | Rate limit excedido (60 req/min por IP)       |
| 500    | Erro inesperado no servidor                   |
 
---
 
## 👨‍💻 Autor
 
Projeto desenvolvido por **Kaick Ramos de Melo Silva**
