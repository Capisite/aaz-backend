# Spring Boot API Security Inventory

API de inventário construída com Spring Boot, JPA/Hibernate e autenticação baseada em JWT. Inclui gerenciamento de usuários e produtos, segurança stateless com tokens, e integração com PostgreSQL via Docker Compose.

## Visão Geral

- API REST para cadastrar e consultar produtos
- Fluxo de autenticação com geração e validação de JWT
- Perfis de usuário com `USER` e `ADMIN`
- Segurança stateless com filtro de autenticação e controle de acesso granular

## Tecnologias

- Java `21`
- Spring Boot `4.0.4`
- Spring Security
- JPA/Hibernate
- PostgreSQL
- Lombok

## Serviços Utilizados

- GitHub

## Pré-requisitos

- `Java 21` instalado
- `Docker` e `Docker Compose` instalados (opcional para banco local)
- Porta `5432` disponível para PostgreSQL

## Configuração de Ambiente

- Variável de ambiente para o segredo do JWT:
  - `JWT_SECRET` (opcional; padrão `mude-esta-chave-em-producao`)
- Configurações de banco em `src/main/resources/application.properties`:
  - URL: `jdbc:postgresql://localhost:5432/aaz`
  - Usuário: `postgres`
  - Senha: `postgres`

## Executar a Aplicação

- Via Maven Wrapper (Windows):

```bash
./mvnw.cmd spring-boot:run
```

- Via Maven Wrapper (Linux/Mac):

```bash
./mvnw spring-boot:run
```

## Endpoints Principais

- **Autenticação**
  - `POST /api/v1/auth/login` — recebe `{ "email": "...", "password": "..." }` e retorna `{ "token": "..." }`
  - `POST /api/v1/auth/register` — cria um novo usuário (requer `MANAGER`)
- **Usuários**
  - `GET /api/v1/users/{id}` — requer `MANAGER`
  - `PATCH /api/v1/users/{id}` — requer `MANAGER`
- **Categorias**
  - `GET /api/v1/categories` — lista categorias (público)
  - `GET /api/v1/categories/{id}` — detalha categoria (público)
  - `POST /api/v1/categories` — requer `MANAGER`
  - `PATCH /api/v1/categories/{id}` — requer `MANAGER`
  - `DELETE /api/v1/categories/{id}` — remove categoria (requer `MANAGER`)
- **Produtos**
  - `GET /api/v1/products` — lista produtos (público)
  - `GET /api/v1/products/{id}` — detalha produto (público)
  - `POST /api/v1/products` — requer `MANAGER`
  - `PATCH /api/v1/products/{id}` — requer `MANAGER`
  - `DELETE /api/v1/products/{id}` — arquiva produto (requer `MANAGER`)
  - `PATCH /api/v1/products/{id}/stock/add` — gerencia estoque (requer `MANAGER`)
  - `PATCH /api/v1/products/{id}/stock/remove` — gerencia estoque (requer `MANAGER`)

## Exemplo de Login

```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@email.com","password":"123"}'
```

- Resposta esperada:

```json
{ "token": "<jwt-token>" }
```

- Utilize o token nas requisições protegidas:

```bash
curl http://localhost:8080/api/v1/products -H "Authorization: Bearer <jwt-token>"
```

## Semeadura de Usuário Admin

- Um usuário `MANAGER` pode ser criado via endpoint `POST /api/v1/auth/register` passando o campo `"role": "MANAGER"`.
- Senhas são armazenadas com `BCrypt`.

## Observações de Segurança

- Configure `JWT_SECRET` em produção com um valor forte e mantido em segredo.
- Mantenha o banco protegido e com credenciais seguras.
- O controle de acesso garante que apenas gerentes possam realizar operações de escrita e alteração de estoque em produtos.

## Versionamento

- `1.0.0`

## Autores

- [**Luiz Fernando**](https://www.linkedin.com/in/luizfernando-react-java-fullstack/)

Obrigado por visitar e bons códigos!
