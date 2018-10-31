package Infraestrutura.Enum;

/**
 * Contém o nome de todas tabelas no database. Padroniza e centraliza o nome das tabelas.
 */
public enum ETab {

    AVALIACAO("avaliacao"),
    BAIRRO("bairro"),
    CIDADE("cidade"),
    COMENTARIO("comentario"),
    CONTATO("contato"),
    CONTRATO("contrato"),
    DIA_SEMANA("dia_semana"),
    ENDERECO("endereco"),
    ESTADO("estado"),
    HORARIO_PREST("horario_prestacao"),
    INFO_PRO("info_profissional"),
    SERVICO_SUBTIPO_SERVICO("servico_subtipo_servico"),
    SERVICO("servico"),
    SUBTIPO_SERVICO("subtipo_servico"),
    TIPO_CONTATO("tipo_contato"),
    TIPO_INFO_PRO("tipo_info_profissional"),
    TIPO_SERVICO("tipo_servico"),
    USUARIO("usuario");

    private String nome;

    ETab(String nome) {
        this.nome = nome;
    }

    public String get() {
        return nome;
    }
}
