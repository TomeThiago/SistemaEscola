--Se não existir irá criar o banco de dados
CREATE DATABASE escola;

--Se não existir irá criar a schema
CREATE SCHEMA IF NOT EXISTS geral;

--Se não existir irá criar a tabela curso na schema geral
CREATE TABLE IF NOT EXISTS geral.curso(
  codigo SERIAL NOT NULL,
  descricao VARCHAR(50) NOT NULL,
  ementa TEXT,
  PRIMARY KEY(codigo)
);

--Se não existir irá criar a tabela aluno na schema geral
CREATE TABLE IF NOT EXISTS geral.aluno(
  codigo SERIAL NOT NULL,
  nome VARCHAR(50),
  PRIMARY KEY(codigo)
);

--Se não existir irá criar a tabela curso_aluno na schema geral
CREATE TABLE IF NOT EXISTS geral.curso_aluno(
  codigo SERIAL NOT NULL,
  codigo_aluno INTEGER NOT NULL,
  codigo_curso INTEGER NOT NULL,
  FOREIGN KEY (codigo_aluno) REFERENCES geral.aluno(codigo),
  FOREIGN KEY (codigo_curso) REFERENCES geral.curso(codigo),
  PRIMARY KEY (codigo)
);