--Retorna todos valores necessários p/ CRIAR um SERVICO e identificar seus subtipos;
CREATE OR REPLACE VIEW view_servico_subtipo AS
	SELECT
		servico.id AS "servico.id",
		servico.titulo AS "servico.titulo",
		--servico.descricao AS "servico.descricao",
		servico.valor AS "servico.valor",
		servico.fk_tipo_servico AS "servico.fk_tipo_servico",
		servico.fk_usuario AS "servico.fk_usuario",
		servico.fk_contrato AS "servico.fk_contrato",
		subtipo_servico.id AS "subtipo_servico.id"
	FROM servico
		INNER JOIN servico_subtipo_servico ON servico.id = servico_subtipo_servico.fk_servico
		INNER JOIN subtipo_servico ON servico_subtipo_servico.fk_subtipo_servico = subtipo_servico.id;