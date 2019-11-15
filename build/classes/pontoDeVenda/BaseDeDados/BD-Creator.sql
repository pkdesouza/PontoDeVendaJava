DROP DATABASE IF EXISTS PontoDeVenda;
CREATE DATABASE PontoDeVenda;
USE PontoDeVenda;

CREATE TABLE cliente(
    codcli int auto_increment not null,
    nome varchar(35),
    bonus long,
    perfil char,
    condicao char,
    primary key(codcli)
);

CREATE TABLE localidade(
    codlocal int auto_increment not null,
    nome varchar(35),
    endereco varchar(80),
    telefone varchar(14),
    primary key (codlocal)
);

CREATE TABLE produto(
    codprod  int auto_increment not null,
    codlocal int,
    descricao varchar(35),
    qte_estoque long,
    preco_unitario double,
    
    primary key (codprod),
    foreign key (codlocal) references localidade(codlocal)
);

CREATE TABLE venda(
    codcli  int not null,
    codlocal int not null,
    codprod int not null,
    qte_venda long,
    valor_total double,
    data_venda date,
    
    primary key (codcli, codlocal, codprod),
    foreign key (codcli) references cliente(codcli),
    foreign key (codlocal) references localidade(codlocal),
    foreign key (codprod) references produto(codprod)
);

CREATE TABLE desconto(
    id_desconto int auto_increment,
    codprod int,
    percentual int,
    qtd_min long,
    qtd_max long,
    
    primary key (id_desconto),
    foreign key (codprod) references produto(codprod)
);

INSERT INTO cliente(nome, bonus, perfil, condicao) VALUES ('Doria', 1000, 'G', 'A');
INSERT INTO cliente(nome, bonus, perfil, condicao) VALUES ('Wagner', 150, 'G', 'A');
INSERT INTO cliente(nome, bonus, perfil, condicao) VALUES ('Randolfo', 300, 'M', 'A');
INSERT INTO cliente(nome, bonus, perfil, condicao) VALUES ('Joseval', 1000, 'P', 'A');
INSERT INTO cliente(nome, bonus, perfil, condicao) VALUES ('Patrick', 250, 'G', 'A');


INSERT INTO localidade(nome, endereco, telefone) VALUES ('Maracana', 'Rua são francisco xavier', '20550-011');
INSERT INTO localidade(nome, endereco, telefone) VALUES ('Centro', 'Rua da Quitanda', '20091-000');
INSERT INTO localidade(nome, endereco, telefone) VALUES ('Méier', 'Rua Dias Cruz', '4109-0459');
INSERT INTO localidade(nome, endereco, telefone) VALUES ('Barra da Tijuca', 'Av. do Pepê', '22620-170');

/*Maracana*/
INSERT INTO produto(codlocal, descricao, qte_estoque, preco_unitario) VALUES (1, 'Smart TV 32 Samsung', 100, 1200.00);
INSERT INTO produto(codlocal, descricao, qte_estoque, preco_unitario) VALUES (1, 'Geladeira Brastemp duoFlex', 30, 2400.00);
INSERT INTO produto(codlocal, descricao, qte_estoque, preco_unitario) VALUES (1, 'Smart TV 50 4K LG', 50, 3000.00);

/*Centro*/
INSERT INTO produto(codlocal, descricao, qte_estoque, preco_unitario) VALUES (2, 'Caixa de som portátil Pulse', 300, 200.00);
INSERT INTO produto(codlocal, descricao, qte_estoque, preco_unitario) VALUES (2, 'Microondas eletrolux 20L', 150, 500.00);
INSERT INTO produto(codlocal, descricao, qte_estoque, preco_unitario) VALUES (2, 'IPhone X', 10, 4000.00);

/*Méier*/
INSERT INTO produto(codlocal, descricao, qte_estoque, preco_unitario) VALUES (3, 'Cafeteira', 60, 180.00);
INSERT INTO produto(codlocal, descricao, qte_estoque, preco_unitario) VALUES (3, 'Fogão 4 bocas mundial', 80, 450.00);
INSERT INTO produto(codlocal, descricao, qte_estoque, preco_unitario) VALUES (3, 'Ferro eletrico', 10, 60.00);

/*Barra da Tijuca*/
INSERT INTO produto(codlocal, descricao, qte_estoque, preco_unitario) VALUES (4, 'Notebook 8gb I7 500gb', 30, 1149.00);
INSERT INTO produto(codlocal, descricao, qte_estoque, preco_unitario) VALUES (4, 'Cama box solteiro premium', 50, 699.00);
INSERT INTO produto(codlocal, descricao, qte_estoque, preco_unitario) VALUES (4, 'Guarda roupa 6 portas com espelho', 10, 1029.00);

/*Desconto*/

INSERT INTO desconto(codprod, percentual, qtd_min, qtd_max) VALUES (1, 5, 10, 50);
INSERT INTO desconto(codprod, percentual, qtd_min, qtd_max) VALUES (4, 20, 5, 50);
INSERT INTO desconto(codprod, percentual, qtd_min, qtd_max) VALUES (7, 20, 10, 80);
INSERT INTO desconto(codprod, percentual, qtd_min, qtd_max) VALUES (8, 20, 10, 70);
INSERT INTO desconto(codprod, percentual, qtd_min, qtd_max) VALUES (9, 8, 10, 90);
INSERT INTO desconto(codprod, percentual, qtd_min, qtd_max) VALUES (12, 10, 2, 80);





