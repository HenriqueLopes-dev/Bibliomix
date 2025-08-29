# 📚 Bibliomix - Biblioteca de Livros Usados

**API REST** desenvolvida em **Java + Spring Boot + PostgreSQL**, que simula uma plataforma colaborativa de **empréstimo, troca, venda e doação de livros usados**.  
O projeto foi construído com foco em **boas práticas de arquitetura**, **regras de negócio reais** e **organização de código**, servindo como demonstração de domínio no ecossistema Spring.

---

## 🚀 Tecnologias utilizadas

- Java 21
- Spring Boot (Web, Data JPA, Validation, Security, Mail)
- PostgreSQL (persistência de dados)
- Lombok e Mapstruct (para reduzir boilerplate)
- Docker + Docker Compose (infraestrutura e banco)
- Swagger / OpenAPI (documentação da API)

---

## 📖 Contexto do Projeto

A proposta do sistema é facilitar o compartilhamento de livros usados entre usuários de diferentes formas:

- 📖 **Empréstimo** → um usuário empresta um livro para outro, com prazo de devolução.  
- 🔄 **Troca** → dois usuários trocam livros entre si.  
- 💰 **Venda** → um usuário vende um livro, movimentando saldo na plataforma.  
- 🎁 **Doação** → um usuário disponibiliza um livro gratuitamente, que pode ser aceito por outro.  

Cada uma dessas ações é registrada como uma **transação**, respeitando regras de negócio específicas.

---

## 📐 Modelagem das Entidades

- **Usuário** → quem cadastra e recebe livros, possui saldo para transações de venda.  
- **Autor** → representado separadamente, para evitar duplicações e permitir consultas específicas.  
- **Livro** → título, isbn, autor(es), ano, dono atual e status *(DISPONÍVEL, EMPRESTADO, VENDIDO, TROCADO, DOADO)*.  
- **Transação** → descreve as operações *(EMPRÉSTIMO, VENDA, TROCA, DOAÇÃO)*, contendo remetente, receptor, valor, prazos e histórico.  

---

## 📜 Regras de Negócio

- Um livro só pode estar em uma **transação ativa por vez**.  
- **Empréstimos** precisam ter prazo de devolução.  
- **Trocas** exigem dois livros disponíveis, de usuários diferentes.  
- **Vendas** verificam se o comprador tem saldo suficiente *(saldo é atualizado após a operação)*.  
- **Doações** podem ser criadas sem receptor, mas só são finalizadas quando outro usuário aceita.  
- O **histórico de transações** fica disponível para consulta.  

---

## 🔗 Endpoints Principais

### Usuários
- `POST /usuarios` → Criar usuário  
- `GET /usuarios/{id}` → Detalhes do usuário  
- `PUT /usuarios/{id}/saldo` → Adicionar saldo  

### Autores
- `POST /autores` → Criar autor  
- `GET /autores` → Listar autores  

### Livros
- `POST /livros` → Cadastrar livro  
- `GET /livros/disponiveis` → Listar livros disponíveis  
- `PUT /livros/{id}/status` → Atualizar status (ex: devolução)  

### Transações
- `POST /transacoes/emprestimo` → Criar empréstimo  
- `POST /transacoes/venda` → Realizar venda  
- `POST /transacoes/troca` → Trocar livros  
- `POST /transacoes/doacao` → Criar doação aberta  
- `PUT /transacoes/doacao/{id}/aceitar` → Aceitar doação existente  
- `GET /transacoes/historico/{usuarioId}` → Histórico do usuário  

---

## 🛠️ Como executar o projeto

### Com Docker
```bash
# Clonar repositório
git clone https://github.com/HenriqueLopes-dev/Bibliomix.git
cd Bibliomix

# Subir aplicação e banco
docker-compose up --build
