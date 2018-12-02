--INFORMACOES PROFISSIONAIS COM BASE NO USUARIO.
CREATE OR REPLACE VIEW VIEW_INFO_PROFISSIONAL AS
  (SELECT ip.id                AS "info_profissional.id",
          ip.descricao         AS "info_profissional.descricao",
          ip.data_inicio       AS "info_profissional.data_inicio",
          ip.data_fim          AS "info_profissional.data_fim",
          ip.fk_usuario        AS "info_profissional.fk_usuario",
          ip.fk_tipo_info_prof AS "info_profissional.fk_tipo_info_prof",
          tip.nome             AS "tipo_info_profissional.nome"

   FROM info_profissional AS ip
          INNER JOIN tipo_info_profissional AS tip ON ip.fk_tipo_info_prof = tip.id);