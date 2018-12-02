--Extensão para calcular similaridade entre textos;
CREATE EXTENSION pg_trgm;

/*
 *Objetivo: Agilizar a busca por um usuário por email, além de agilizar seu login
 *(busca de email e senha).
 *Justificativa: Login é uma atividade que deve ter baixa latência.
 */
CREATE INDEX idx_email_usuario ON usuario USING gin(email gin_trgm_ops);

/*
 *Objetivo: Agilizar a busca por informações profissionais de um usuário.
 *Justificativa: Consulta de perfil de um usuário é realizada frequentemente.
 */
CREATE INDEX idx_fk_usuario_info_pro ON info_profissional(fk_usuario);

/*
 *Objetivo: Agilizar a busca por meios de contato de um usuário.
 *Justificativa: Consulta de perfil de um usuário é realizada frequentemente.
 */
CREATE INDEX idx_fk_usuario_contato ON contato(fk_usuario);

--Objetivo geral: Agilizar a busca por serviços (Funcionalidade chave do sistema)

/*
 *Objetivo: Agilizar a busca por serviços usando fragmentos de seu título.
 *Justificativa: A busca de serviço por seu título é uma funcionalidade core
  e acessada frequentemente.
 */
CREATE INDEX idx_trgm_titulo_servico ON servico
USING gin(titulo gin_trgm_ops);

/*
 *Objetivo: Agilizar a busca por serviços usando uma parte de sua descrição.
 *Justificativa: Segunda opção de busca por serviço que será mais utilizada.
 */
CREATE INDEX idx_trgm_descricao_servico ON servico
USING gin(descricao gin_trgm_ops);

/*
 *Objetivo: Agilizar a busca por serviços por meio da identificação do prestador.
 *Justificativa: Viabilizar e acelerar a busca de serviços por meio de dados do
  responsável por prestá-lo.
 */
CREATE INDEX idx_fk_usuario_servico ON servico(fk_usuario);


/*
 *Objetivo: Agilizar a busca por serviços de determinados subtipos(subtagorias);
 *Justificativa: A busca de serviços por seus subtipos é uma operação crucial
  e muito acessada.
 */
CREATE INDEX idx_fk_servico_sss ON servico_subtipo_servico(fk_servico);
CREATE INDEX idx_fk_subtipo_servico_sss ON servico_subtipo_servico(fk_subtipo_servico);