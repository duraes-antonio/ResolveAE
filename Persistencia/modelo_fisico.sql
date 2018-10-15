CREATE TABLE usuario(
	id SERIAL PRIMARY KEY, 
	nome VARCHAR (150), 
	email VARCHAR (100) UNIQUE, 
	senha VARCHAR (64), 
	sobre VARCHAR (1000)
);

CREATE TABLE estado(
	id SERIAL PRIMARY KEY, 
	nome VARCHAR (40) UNIQUE, 
	sigla CHAR (2) UNIQUE
);

CREATE TABLE cidade(
	id SERIAL PRIMARY KEY, 
	nome VARCHAR (40), 
	fk_estado SERIAL REFERENCES estado(id)
);

CREATE TABLE bairro(
	id SERIAL PRIMARY KEY, 
	nome VARCHAR (60), 
	fk_cidade SERIAL REFERENCES cidade(id)
);

CREATE TABLE endereco(
	id SERIAL PRIMARY KEY, 
	cep CHAR (8), 
	fk_bairro SERIAL REFERENCES bairro(id), 
	fk_usuario SERIAL REFERENCES usuario(id)
);

CREATE TABLE tipo_contato(
	id SERIAL PRIMARY KEY, 
	nome VARCHAR (15) UNIQUE
);

CREATE TABLE contato(
	id SERIAL PRIMARY KEY, 
	descricao VARCHAR (100), 
	fk_usuario SERIAL REFERENCES usuario(id), 
	fk_tipo_contato SERIAL REFERENCES tipo_contato(id)
);

CREATE TABLE tipo_info_profissional(
	id SERIAL PRIMARY KEY, 
	nome VARCHAR (20) UNIQUE
);

CREATE TABLE info_profissional(
	id SERIAL PRIMARY KEY, 
	descricao VARCHAR (200), 
	data_inicio DATE, 
	data_fim DATE, 
	fk_usuario SERIAL REFERENCES usuario(id), 
	fk_tipo_info_prof SERIAL REFERENCES tipo_info_profissional(id)
);

CREATE TABLE contrato(
	id SERIAL PRIMARY KEY, 
	data_inicio DATE, 
	data_fim DATE, 
	data_ult_modif DATE, 
	descricao VARCHAR (1000), 
	horas_contratadas INT, 
	fk_usuario SERIAL REFERENCES usuario(id)
);

CREATE TABLE tipo_servico(
	id SERIAL PRIMARY KEY, 
	nome VARCHAR (30) UNIQUE
);

CREATE TABLE subtipo_servico(
	id SERIAL PRIMARY KEY, 
	nome VARCHAR (50) UNIQUE, 
	fk_tipo_servico SERIAL REFERENCES tipo_servico(id)
);

CREATE TABLE servico(
	id SERIAL PRIMARY KEY, 
	valor FLOAT, 
	titulo VARCHAR (150), 
	fk_contrato SERIAL REFERENCES contrato(id), 
	fk_subtipo_servico SERIAL REFERENCES subtipo_servico(id), 
	fk_tipo_servico SERIAL REFERENCES tipo_servico(id), 
	fk_usuario SERIAL REFERENCES usuario(id)
);

CREATE TABLE servico_subtipo_servico(
	id SERIAL PRIMARY KEY, 
	fk_servico SERIAL REFERENCES servico(id), 
	fk_subtipo_servico SERIAL REFERENCES subtipo_servico(id)
);

CREATE TABLE avaliacao(
	id SERIAL PRIMARY KEY, 
	nota INT, 
	fk_usuario SERIAL REFERENCES usuario(id), 
	fk_servico_prestacao SERIAL REFERENCES servico(id)
);

CREATE TABLE comentario(
	id SERIAL PRIMARY KEY, 
	comentario VARCHAR (500), 
	fk_avaliacao SERIAL REFERENCES avaliacao(id)
);

CREATE TABLE dia_semana(
	id SERIAL PRIMARY KEY, 
	nome VARCHAR (15) UNIQUE
);

CREATE TABLE horario_prestacao(
	id SERIAL PRIMARY KEY, 
	inicio TIME, 
	fim TIME, 
	disponivel BOOLEAN, 
	fk_usuario SERIAL REFERENCES usuario(id), 
	fk_dia_semana SERIAL REFERENCES dia_semana(id)
);