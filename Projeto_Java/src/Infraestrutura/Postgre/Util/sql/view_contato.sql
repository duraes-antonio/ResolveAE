--Retorna os contatos com os nomes das colunas usados na aplicação
CREATE OR REPLACE VIEW view_contato
AS(
	SELECT
		contato.id AS "contato.id",
		contato.descricao AS "contato.descricao",
		contato.fk_tipo_contato AS "contato.fk_tipo_contato",
		contato.fk_usuario AS "contato.fk_usuario"
	FROM contato
);