package Infraestrutura.Postgre.Util;

public enum View {
    /**Retorna avaliacao_id, avaliacao_nota, avaliacao_fk_usuario, avaliacao_fk_servico,
     * comentario_id, comentario_comentario.*/
    VIEW_AVALIACAO_COMENTARIO,

    /**Retorna contato_id, contato_descricao, contato_fk_tipo_contato,
     * contato_fk_usuario*/
    VIEW_CONTATO,

    /**Retorna endereco_id, endereco_cep, endereco_bairro, endereco_cidade,
     * endereco_estado, endereco_fk_usuario;*/
    VIEW_ENDERECO,

    /**Retorna horario_prestacao.id, horario_prestacao.inicio,
     * horario_prestacao.fim, horario_prestacao.disponivel, horario_prestacao.fk_usuario;*/
    VIEW_HORARIO,

    /**Retorna info_profissional.id, info_profissional.descricao,
     * info_profissional.data_inicio, info_profissional.data_fim,
     * info_profissional.fk_usuario, tipo_info_profissional.nome;*/
    VIEW_INFO_PROFISSIONAL,

    /**Retorna servico.id, servico.titulo, servico.descricao, servico.valor,
     * servico.fk_tipo_servico, servico.fk_usuario, servico.fk_contrato,
     * subtipo_servico.id*/
    VIEW_SERVICO_SUBTIPO,

    /**Retorna "usuario.id", "usuario.nome", "usuario.email", "usuario.senha",
     * "usuario.sobre" E TODAS colunas da VIEW_AVALIACAO_COMENTARIO, VIEW_CONTATO,
     * VIEW_ENDERECO, VIEW_HORARIO e VIEW_INFO_PROFISSIONAL_USUARIO*/
    VIEW_USUARIO;
}
