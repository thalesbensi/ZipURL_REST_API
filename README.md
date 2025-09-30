# 🔗 ZipURL – Encurtador de Links

Uma **API RESTful** desenvolvida com **Java e Spring Boot** para encurtar URLs de forma simples, rápida e segura.  
O projeto também aplica **caching com Redis** para melhorar a performance e reduzir consultas repetidas no banco de dados **MongoDB**.

Criado para consolidar conhecimentos em **Back-end com Java**, explorando arquitetura em camadas, boas práticas e integração de tecnologias modernas.

---

## 🚀 Funcionalidades

- 🔗 **Encurtar URL** a partir de uma URL original
- 🌐 **Redirecionar** automaticamente para a URL original através da URL encurtada
- 📊 **Estatísticas básicas** (quantidade de acessos)
- ⚡ **Caching com Redis** para URLs mais acessadas


---

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot** (Web, Data MongoDB, Validation)
- **MongoDB** – Persistência de URLs
- **Redis** – Cache de URLs
- **Lombok**
- **Swagger/OpenAPI** – Documentação de endpoints
- **Postman** – Testes de API
- **Maven** – Gerenciador de dependências

---

## ⚙️ Como Configurar o Projeto

### 1. Pré-requisitos

- Java 17+
- Maven
- MongoDB
- Redis

---

### 2. Clonar o Repositório

```bash
git clone https://github.com/thalesbensi/ZipURL_REST_API
```

---

### 3. Configurar o Banco de Dados

````properties
# MongoDB
spring.data.mongodb.uri=mongodb://localhost:27017/zipurl
spring.data.mongodb.database=zipurl

# Redis
spring.redis.host=localhost
spring.redis.port=6379
spring.redis.database=0
spring.redis.password=
spring.redis.timeout=60000
````

### 4. Executar a Aplicação

```bash
mvn spring-boot:run
```
A aplicação estará disponível em:
👉 http://localhost:8080

## 🔧 Endpoints Principais

### 🔗 URLs

- **POST** `/api/shorten` – Criar nova URL encurtada
- **GET** `/{shortCode}` – Redirecionar para a URL original
- **GET** `/api/stats/{shortCode}` – Consultar estatísticas da URL

---

### 📥 Payload de Criação

**Requisição:**

```json
{
  "originalUrl": "https://www.exemplo.com/artigo/123"
}
```

**Resposta:**

```json
{
  "shortUrl": "http://localhost:8080/abc123",
  "originalUrl": "https://www.exemplo.com/artigo/123",
}

```

## 📄 Documentação da API
A documentação interativa está disponível em:
👉 http://localhost:8080/swagger-ui.html

---

## 📦 Docker
O projeto inclui um `Dockerfile` para facilitar a criação de uma imagem Docker da aplicação.
Para construir a imagem, execute:

```bash
docker build -t zipurl-api .
```
Para rodar o container, use:

```bash
docker run -d -p 8080:8080 zipurl-api
```
---

### Arquivo docker-compose.yml
Para facilitar o setup com MongoDB e Redis, você pode usar o seguinte `docker-compose.yml`:

```yaml
version: '3.8'

services:
  # MongoDB
  mongodb:
    image: mongo:6.0
    container_name: zipurl-mongodb
    restart: always
    environment:
      MONGO_INITDB_DATABASE: ${MONGO_DB}
    ports:
      - "${MONGO_PORT}:27017"
    volumes:
      - mongodb_data:/data/db

  # Redis
  redis:
    image: redis:7.2
    container_name: zipurl-redis
    restart: always
    ports:
      - "${REDIS_PORT}:6379"
    volumes:
      - redis_data:/data

  # API Spring Boot
  api:
    build: .
    container_name: zipurl-api
    ports:
      - "${SPRING_PORT}:8080"
    depends_on:
      - mongodb
      - redis
    environment:
      SPRING_DATA_MONGODB_URI: mongodb://mongodb:${MONGO_PORT}/${MONGO_DB}
      SPRING_REDIS_HOST: redis
      SPRING_REDIS_PORT: ${REDIS_PORT}

volumes:
  mongodb_data:
  redis_data:
```

### 3. Subir a Aplicação com Docker
```bash
docker-compose up --build
```

#### Isso irá:

 - Subir um container MongoDB

 - Subir um container Redis

 - Subir a API Spring Boot já conectada

### A aplicação estará disponível em:

 - API: http://localhost:8080
 - Swagger UI: http://localhost:8080/swagger-ui.html


## Arquivo .env
Para facilitar a configuração das variáveis de ambiente, você pode criar um arquivo `.env` na raiz do projeto com o seguinte conteúdo:

```# MongoDB
MONGO_DB=zipurl
MONGO_PORT=27017

# Redis
REDIS_HOST=redis
REDIS_PORT=6379

# API
SPRING_PORT=8080
```

