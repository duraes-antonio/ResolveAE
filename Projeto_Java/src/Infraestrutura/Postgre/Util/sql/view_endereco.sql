--Endereço completo (cep, bairro, cidade, estado [sigla]);
CREATE OR REPLACE VIEW VIEW_ENDERECO
AS(
	SELECT
		endereco.id AS "endereco.id",
		endereco.cep AS "endereco.cep",
		bairro.nome AS "endereco.bairro",
		cidade.nome AS "endereco.cidade",
		estado.sigla AS "endereco.estado",
		endereco.fk_usuario AS "endereco.fk_usuario"
	FROM endereco
	INNER JOIN bairro ON bairro.id = endereco.fk_bairro
	INNER JOIN cidade ON cidade.id = bairro.fk_cidade
	INNER JOIN estado ON estado.id = cidade.fk_estado
);