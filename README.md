# Projeto DIO Spring Boot

API REST desenvolvida em Java com Spring Boot como parte de um projeto de estudo da Digital Innovation One (DIO). O objetivo principal da aplicação é demonstrar a criação de uma API básica para gerenciamento de usuários, com validações, tratamento de exceções e documentação via Swagger.

## Visão geral

Este projeto implementa uma API simples de usuários com operações de:

- listagem de usuários
- consulta por nome de usuário
- criação de usuário
- atualização de usuário
- exclusão de usuário
- resposta inicial da aplicação em endpoint raiz
- validação de campos obrigatórios
- documentação OpenAPI/Swagger

## Tecnologias utilizadas

- Java 21
- Spring Boot 3.3.4
- Spring Web
- Maven
- Springdoc OpenAPI (Swagger)
- JUnit 5

## Estrutura do projeto

```text
Projeto-Spring-boot-DIO---CI-T/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── dio/
│   │   │       └── projeto_dio_spring/
│   │   │           ├── controller/
│   │   │           │   ├── UserController.java
│   │   │           │   └── WelcomeController.java
│   │   │           ├── doc/
│   │   │           │   └── SwaggerConfig.java
│   │   │           ├── handler/
│   │   │           │   ├── BusinessException.java
│   │   │           │   ├── CampoObrigatorioException.java
│   │   │           │   ├── GlobalExceptionHandler.java
│   │   │           │   └── ResponseError.java
│   │   │           ├── model/
│   │   │           │   └── User.java
│   │   │           ├── repository/
│   │   │           │   └── UserRepository.java
│   │   │           ├── service/
│   │   │           │   └── UserService.java
│   │   │           └── ProjetoDioSpringApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── dio/
│               └── projeto_dio_spring/
│                   └── ProjetoDioSpringApplicationTests.java
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## Componentes principais

### 1. Aplicação principal
Arquivo: `src/main/java/dio/projeto_dio_spring/ProjetoDioSpringApplication.java`

- marca a classe com `@SpringBootApplication`
- inicia a aplicação Spring Boot

### 2. Modelo `User`
Arquivo: `src/main/java/dio/projeto_dio_spring/model/User.java`

Representa o usuário da API com os atributos:

- `id`
- `login`
- `password`

A classe possui getters, setters e `toString()` para facilitar logs e depuração.

### 3. Repositório
Arquivo: `src/main/java/dio/projeto_dio_spring/repository/UserRepository.java`

A camada de acesso a dados está simulada em memória e inclui métodos como:

- `save(User user)`
- `update(User user)`
- `remove(Integer id)`
- `listAll()`
- `finById(Integer id)`
- `finByName(String name)`

Ela também valida campos obrigatórios antes de persistir o usuário:

- se `login` for nulo
- se `password` for nulo

Quando algum campo obrigatório falta, é lançada a exceção `CampoObrigatorioException`.

### 4. Controladores

#### `WelcomeController`
Arquivo: `src/main/java/dio/projeto_dio_spring/controller/WelcomeController.java`

Cria o endpoint raiz da aplicação:

- `GET /`

Resposta:

```text
Welcome to my Spring Boot web API
```

#### `UserController`
Arquivo: `src/main/java/dio/projeto_dio_spring/controller/UserController.java`

Possui os endpoints abaixo:

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/users` | lista todos os usuários |
| GET | `/users/{username}` | busca um usuário pelo username |
| POST | `/` | salva um usuário |
| PUT | `/` | atualiza um usuário |
| DELETE | `/users/{id}` | remove um usuário pelo id |

> Observação: a API usa o mesmo endpoint raiz para criação e atualização (`POST /` e `PUT /`), sem separar recursos por `/users` em ambos os casos.

### 5. Tratamento de exceções
Arquivos:

- `handler/BusinessException.java`
- `handler/CampoObrigatorioException.java`
- `handler/GlobalExceptionHandler.java`
- `handler/ResponseError.java`

A aplicação usa `@RestControllerAdvice` para tratar exceções globais da API. Quando há erro de negócio, o retorno é serializado em JSON com detalhes do erro e status HTTP apropriado.

O status configurado para `BusinessException` é:

- `409 Conflict`

### 6. Swagger/OpenAPI
Arquivo: `src/main/java/dio/projeto_dio_spring/doc/SwaggerConfig.java`

A aplicação configura a documentação da API com o Springdoc OpenAPI, permitindo visualizar e testar os endpoints pela interface do Swagger UI.

URL padrão:

```text
http://localhost:8080/swagger-ui/index.html
```

## Endpoints da API

### GET `/`
Retorna uma mensagem de boas-vindas.

Exemplo de resposta:

```text
Welcome to my Spring Boot web API
```

### GET `/users`
Lista todos os usuários cadastrados no repositório em memória.

Exemplo de resposta:

```json
[
  {
    "id": null,
    "login": "gleyson",
    "password": "password"
  },
  {
    "id": null,
    "login": "frank",
    "password": "masterpass"
  }
]
```

### GET `/users/{username}`
Busca um usuário pelo nome de login.

Exemplo:

```text
GET /users/gleyson
```

Resposta:

```json
{
  "id": null,
  "login": "gleyson",
  "password": "password"
}
```

### POST `/`
Cria um usuário.

Corpo da requisição:

```json
{
  "login": "joao",
  "password": "123456"
}
```

### PUT `/`
Atualiza um usuário.

Corpo da requisição:

```json
{
  "login": "novoLogin",
  "password": "novaSenha"
}
```

### DELETE `/users/{id}`
Remove um usuário pelo id.

Exemplo:

```text
DELETE /users/1
```

## Validação de campos obrigatórios

O repositório verifica valores nulos antes de salvar o usuário. Quando o `login` ou `password` não for informado, a aplicação lança uma exceção e retorna um erro no padrão JSON.

Exemplo de erro:

```json
{
  "status": "error",
  "error": "O campo Login é obrigatório",
  "statusCode": 409
}
```

## Como executar o projeto

### Pré-requisitos

- Java 21
- Maven
- Git

### Clonar o repositório

```bash
git clone https://github.com/seu-usuario/Projeto-Spring-boot-DIO---CI-T.git
cd Projeto-Spring-boot-DIO---CI-T
```

### Executar a aplicação

Usando o Maven Wrapper:

```bash
bash mvnw spring-boot:run
```

Ou, se o arquivo `mvnw` estiver com permissão de execução:

```bash
./mvnw spring-boot:run
```

### Acessar a API

Após iniciar a aplicação, a API ficará disponível em:

```text
http://localhost:8080
```

Documentação Swagger em:

```text
http://localhost:8080/swagger-ui/index.html
```

## Como testar

Para executar os testes do projeto:

```bash
bash mvnw test
```

## Observações

- O projeto é um exemplo didático e não utiliza banco de dados relacional.
- Os dados são armazenados em memória de forma simulada.
- O código mostra os conceitos básicos de uma API REST com Spring Boot, validação, tratamento de exceções e documentação automática.

## Licença

Este projeto foi desenvolvido como exemplo de estudo e pode ser adaptado livremente para fins acadêmicos ou educacionais.
