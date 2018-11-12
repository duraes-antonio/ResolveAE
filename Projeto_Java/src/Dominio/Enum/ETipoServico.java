package Dominio.Enum;

import java.util.HashMap;

public enum ETipoServico {
    ANALISE_DE_SISTEMAS("Análise de Sistemas", 6),
    BANCO_DE_DADOS("Banco de Dados", 3),
    DESENVOLVIMENTO("Desenvolvimento", 4),
    DESIGN("Design", 5),
    MANUTENCAO_E_SUPORTE("Manutenção e Suporte", 1),
    REDE_DE_COMPUTADORES("Rede de Computadores", 2),
    SEGURANCA_DA_INFORMACAO("Segurança da Informação", 7);

    private String tipo;
    private int id;
    private static HashMap<Integer, ETipoServico> hashEnumId;


    private ETipoServico(String tipo, int id) {
        this.tipo = tipo;
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public int getId() {
        return id;
    }

    public static ETipoServico getById(int id) {

        ETipoServico tipoServico = null;

        if (hashEnumId == null) {
            initializeMapping();
        }

        if (hashEnumId.containsKey(id)) {
            tipoServico = hashEnumId.get(id);
        }

        return tipoServico;
    }

    private static void initializeMapping() {

        hashEnumId = new HashMap<>();

        for (ETipoServico enumTipo : ETipoServico.values()) {
            hashEnumId.put(enumTipo.getId(), enumTipo);
        }
    }
}
