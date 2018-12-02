CREATE OR REPLACE VIEW view_avaliacao_comentario AS
(
	SELECT
		avaliacao.id AS "avaliacao.id",
		avaliacao.nota AS "avaliacao.nota",
		avaliacao.fk_usuario AS "avaliacao.fk_usuario",
		avaliacao.fk_servico AS "avaliacao.fk_servico",
		comentario.id AS "comentario.id",
		comentario.comentario AS "comentario.comentario"
	FROM avaliacao
	LEFT JOIN comentario on avaliacao.id = comentario.fk_avaliacao
	ORDER BY 1
);