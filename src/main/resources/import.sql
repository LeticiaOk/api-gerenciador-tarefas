-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- Usuários
INSERT INTO usuario (id, nome, email) VALUES (1, 'Letícia Silva', 'leticia@email.com');
INSERT INTO usuario (id, nome, email) VALUES (2, 'Carlos Lima', 'carlos@email.com');

-- Projetos
INSERT INTO projeto (id, nome, descricao) VALUES (1, 'Sistema Acadêmico', 'Gerenciar alunos e turmas');
INSERT INTO projeto (id, nome, descricao) VALUES (2, 'App de Receitas', 'Aplicativo de receitas culinárias');

-- Tarefas
INSERT INTO tarefa (id, titulo, descricao, status, usuario_id, projeto_id)
VALUES (1, 'Modelar banco de dados', 'Criar diagramas e tabelas', 'pendente', 1, 1);

INSERT INTO tarefa (id, titulo, descricao, status, usuario_id, projeto_id)
VALUES (2, 'Criar tela de login', 'Tela com autenticação', 'em andamento', 2, 1);

INSERT INTO tarefa (id, titulo, descricao, status, usuario_id, projeto_id)
VALUES (3, 'Adicionar receitas', 'Cadastrar receitas iniciais', 'concluída', 1, 2);

-- Resetar sequências
ALTER SEQUENCE usuario_seq RESTART WITH 3;
ALTER SEQUENCE projeto_seq RESTART WITH 3;
ALTER SEQUENCE tarefa_seq RESTART WITH 4;
