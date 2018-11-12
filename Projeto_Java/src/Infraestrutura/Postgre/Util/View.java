package Infraestrutura.Postgre.Util;

public enum View {
    /**Retorna avaliacao_id, avaliacao_nota, avaliacao_fk_usuario, avaliacao_fk_servico,
     * comentario_id, comentario_comentario.*/
    VIEW_AVALIACAO_COMENTARIO,

    /**Retorna servico.id, servico.titulo, servico.descricao, servico.valor,
     * servico.fk_tipo_servico, servico.fk_usuario, servico.fk_contrato,
     * subtipo_servico.id*/
    VIEW_SERVICO_SUBTIPO,

    VIEW_AVALIACOES_SERVICO,
    VIEW_DADOS_CONTRATO,

    /**Retorna endereco_id, endereco_cep, endereco_bairro, endereco_cidade,
     * endereco_estado, endereco_fk_usuario;*/
    VIEW_ENDERECO,
    VIEW_ENDERECO_USUARIO,
    VIEW_INFO_PROFISSIONAL_USUARIO,
    VIEW_SUBTIPO_SERVICO;
}
