# 📌 API Gerenciadora de Tarefas

Este projeto é uma API RESTful desenvolvida com **Quarkus**, que simula um sistema de gerenciamento de tarefas semelhante ao Trello. Com ela, é possível cadastrar usuários, criar projetos e atribuir tarefas a esses projetos e usuários.

---

## 🚀 Instruções para Execução

### ✅ Requisitos:
- Java 17+
- Maven

### ▶️ Passos para rodar localmente:

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/nome-do-repositorio.git
```
2. Acesse a pasta do projeto:
````bash
cd nome-do-repositorio
````
3. Execute o projeto:
```bash
./mvnw quarkus:dev
```

4. Acesse o Swagger UI para testar os endpoints:
```bash
http://localhost:8080/q/swagger-ui
```

## 📁 Entidades e Endpoints

### Usuário:
Representa uma pessoa que pode ser atribuída a tarefas.

| Método | Endpoint                     | Descrição                         |
|--------|------------------------------|-----------------------------------|
| GET    | /usuarios                    | Lista todos os usuários           |
| GET    | /usuarios/{id}              | Busca um usuário pelo ID          |
| POST   | /usuarios                   | Cria um novo usuário              |
| PUT    | /usuarios/{id}              | Atualiza um usuário existente     |
| DELETE | /usuarios/{id}              | Remove um usuário                 |
| GET    | /usuarios/{id}/tarefas      | Lista as tarefas de um usuário    |

---

### Projeto:
Agrupa diversas tarefas sob um mesmo contexto.

| Método | Endpoint                     | Descrição                         |
|--------|------------------------------|-----------------------------------|
| GET    | /projetos                    | Lista todos os projetos           |
| GET    | /projetos/{id}              | Busca um projeto pelo ID          |
| POST   | /projetos                   | Cria um novo projeto              |
| PUT    | /projetos/{id}              | Atualiza um projeto existente     |
| DELETE | /projetos/{id}              | Remove um projeto                 |
| GET    | /projetos/{id}/tarefas      | Lista as tarefas de um projeto    |

---

### Tarefa:
Representa uma atividade a ser realizada, podendo estar associada a um usuário e a um projeto.

| Método | Endpoint                        | Descrição                         |
|--------|----------------------------------|-----------------------------------|
| GET    | /tarefas                        | Lista todas as tarefas            |
| GET    | /tarefas/{id}                  | Busca uma tarefa pelo ID          |
| POST   | /tarefas                       | Cria uma nova tarefa              |
| PUT    | /tarefas/{id}                  | Atualiza uma tarefa existente     |
| DELETE | /tarefas/{id}                  | Remove uma tarefa                 |
| GET    | /tarefas/status/{status}       | Lista tarefas por status          |

## 💡 Exemplos de Uso (cURL)

### Criar um Usuário:

```bash
curl -X POST http://localhost:8080/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Marcela",
    "email": "marcela@email.com"
}'
````

### Criar um Projeto:
```bash

curl -X POST http://localhost:8080/projetos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "App de Estoque",
    "descricao": "Armazenar informações de produtos"
}'
```

### Criar uma Tarefa:
```bash
curl -X POST http://localhost:8080/tarefas \
-H "Content-Type: application/json" \
-d '{
"titulo": "Criar layout",
"descricao": "Desenvolver interface principal",
"status": "PENDENTE",
"idUsuario": 1,
"idProjeto": 1
}'
```