drop database livros;
CREATE DATABASE livros;

USE livros;

CREATE TABLE IF NOT EXISTS livro (
id_livro INT auto_increment primary KEY, 
titulo VARCHAR(200),
autor VARCHAR(70),
genero VARCHAR(45),
data_lancamento DATE,
sinopse VARCHAR(350),
adaptacao BOOLEAN
);

select titulo from livro where titulo = "null";

CREATE TABLE IF NOT EXISTS adaptacao (
id_adaptacao INT AUTO_INCREMENT PRIMARY KEY,
nome_adaptacao VARCHAR(200),
diretor VARCHAR(100),
tipo_adaptacao VARCHAR(45),
data_lancamento DATE
);

CREATE TABLE usuario (
id_usuario INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100),
email VARCHAR(50),
senha CHAR(6),
telefone CHAR(11)
);

CREATE TABLE resenha (
id_resenha INT AUTO_INCREMENT PRIMARY KEY, 
titulo VARCHAR(70),
resenha VARCHAR(500),
quantidade_estrelas INT,
fk_livro INT,
FOREIGN KEY (fk_livro) REFERENCES livro(id_Livro),
fk_usuario INT, 
FOREIGN KEY (fk_usuario) REFERENCES usuario(id_usuario)
);




