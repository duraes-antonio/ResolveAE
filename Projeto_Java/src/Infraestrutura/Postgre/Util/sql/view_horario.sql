CREATE OR REPLACE VIEW view_horario AS
  (SELECT horario_prestacao.id            AS "horario_prestacao.id",
          horario_prestacao.inicio        AS "horario_prestacao.inicio",
          horario_prestacao.fim           AS "horario_prestacao.fim",
          horario_prestacao.disponivel    AS "horario_prestacao.disponivel",
          horario_prestacao.fk_usuario    AS "horario_prestacao.fk_usuario",
          horario_prestacao.fk_dia_semana AS "horario_prestacao.fk_dia_semana"
   FROM horario_prestacao);