# 📚 Bibliomix - Biblioteca de Livros Usados

**API REST** desenvolvida em **Java + Spring Boot + PostgreSQL**, que simula uma plataforma colaborativa de **empréstimo, troca, venda e doação de livros usados**.  
O projeto foi construído com foco em **boas práticas de arquitetura**, **regras de negócio reais** e **organização de código**, servindo como demonstração de domínio no ecossistema Spring.

### Visitar código [(clique aqui)](/src/main/java/io/github/HenriqueLopes_dev/BiblioMix/BiblioMix)

---

## Tecnologias utilizadas

- Java 21
- Spring Boot (Web, Data JPA, Validation, Security, Hateoas)
- Banco de dados PostgreSQL
- Lombok e Mapstruct (para reduzir boilerplate)
- Docker + Docker Compose (infraestrutura e banco)
- Swagger / OpenAPI (documentação da API)

---

## Modelagem das Entidades

- **Usuário** → cadastra e recebe livros. Cada usuário possui um saldo para realizar transações de venda.
- **Autor** → modelado separadamente para evitar duplicações e permitir consultas específicas sobre cada autor.  
- **Livro** → contém informações como título, ISBN, autor(es), ano de publicação, dono atual e status.
  - Status possíveis: DISPONÍVEL, EMPRESTADO, VENDIDO, TROCADO, DOADO

---

## 🔗 Endpoints Principais

### users
- `POST /users` → Criar usuário  
- `GET /users/{id}` → Obter detalhes do usuário  
- `PUT /users/{id}` → Atualizar dados do usuário
- `DELETE /users/{id}` → Excluir usuário
- `GET /users` → Listar usuários e pesquisar (parametros de pesquisa disponiveis: name, email, page e page-size)

### authors
- `POST /authors` → Criar autor  
- `GET /authors/{id}` → Obter detalhes do autor  
- `PUT /authors/{id}` → Atualizar dados do autor
- `DELETE /authors/{id}` → Excluir autor
- `GET /authors` → Listar autores e pesquisar (parametros de pesquisa disponiveis: nationality, date-of-birth, page e page-size)

### books
- `POST /books` → Criar livro  
- `GET /books/{id}` → Obter detalhes do livro  
- `PUT /books/{id}` → Atualizar dados do livro
- `DELETE /books/{id}` → Excluir livro
- `GET /books` → Listar livros e pesquisar (parametros de pesquisa disponiveis: title, published-date, isbn, status, page e page-size)

---

## 🛠️ Como executar o projeto

### Com Docker
```bash
# Clonar repositório
git clone https://github.com/HenriqueLopes-dev/Bibliomix.git
cd Bibliomix

# Subir aplicação e banco
docker-compose up --build
