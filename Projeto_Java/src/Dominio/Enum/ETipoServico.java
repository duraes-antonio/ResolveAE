package Dominio.Enum;

public enum ETipoServico {
    ANALISE_DE_SISTEMAS("Análise de Sistemas"),
    BANCO_DE_DADOS("Banco de Dados"),
    DESENVOLVIMENTO("Desenvolvimento"),
    DESIGN("Design"),
    MANUTENCAO_E_SUPORTE("Manutenção e Suporte"),
    REDE_DE_COMPUTADORES("Rede de Computadores"),
    SEGURANCA_DA_INFORMACAO("Segurança da Informação");

    private String tipo;

    private ETipoServico(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}
