CREATE OR REPLACE VIEW view_usuario
AS(
	SELECT usuario.id AS "usuario.id",
		usuario.nome AS "usuario.nome",
		usuario.email AS "usuario.email",
		usuario.senha AS "usuario.senha",
		usuario.sobre AS "usuario.sobre",
		view_endereco.*,
		view_contato.*,
		view_avaliacao_comentario.*,
		view_horario.*,
		view_info_profissional.*
    
	FROM usuario
		LEFT JOIN view_endereco ON usuario.id = view_endereco."endereco.fk_usuario"
		LEFT JOIN view_avaliacao_comentario ON usuario.id = view_avaliacao_comentario."avaliacao.fk_usuario"
		LEFT JOIN view_contato ON usuario.id = view_contato."contato.fk_usuario"
		LEFT JOIN view_horario ON usuario.id = view_horario."horario_prestacao.fk_usuario"
		LEFT JOIN view_info_profissional ON usuario.id = view_info_profissional."info_profissional.fk_usuario"
);