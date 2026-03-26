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
  - `POST /api/auth/login` — recebe `{ "email": "...", "password": "..." }` e retorna `{ "token": "..." }`
  - `POST /api/auth/register` — cria um novo usuário (campos: `fullName`, `username`, `email`, `password`, `role`)
- **Usuários**
  - `GET /api/users/{id}` — requer autenticação
  - `PUT /api/users/{id}` — requer autenticação
  - `DELETE /api/users/{id}` — requer autenticação
- **Produtos**
  - `GET /api/products` — lista produtos (requer autenticação)
  - `GET /api/products/{id}` — detalha produto (requer autenticação)
  - `POST /api/products` — requer `ADMIN`
  - `PUT /api/products/{id}` — requer `ADMIN`
  - `DELETE /api/products/{id}` — requer `ADMIN`
  - `PATCH /api/products/{id}/stock/add` — gerencia estoque (requer `ADMIN`)
  - `PATCH /api/products/{id}/stock/remove` — gerencia estoque (requer `ADMIN`)

## Exemplo de Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@email.com","password":"123"}'
```

- Resposta esperada:

```json
{ "token": "<jwt-token>" }
```

- Utilize o token nas requisições protegidas:

```bash
curl http://localhost:8080/api/products -H "Authorization: Bearer <jwt-token>"
```

## Semeadura de Usuário Admin

- Um usuário `ADMIN` pode ser criado via endpoint `POST /api/auth/register` passando o campo `"role": "ADMIN"`.
- Senhas são armazenadas com `BCrypt`.

## Observações de Segurança

- Configure `JWT_SECRET` em produção com um valor forte e mantido em segredo.
- Mantenha o banco protegido e com credenciais seguras.
- O controle de acesso garante que apenas administradores possam realizar operações de escrita e alteração de estoque em produtos.

## Versionamento

- `1.0.0`

## Autores

- [**Luiz Fernando**](https://www.linkedin.com/in/luizfernando-react-java-fullstack/)

Obrigado por visitar e bons códigos!
