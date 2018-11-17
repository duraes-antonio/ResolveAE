package Dominio.Enum;

import java.util.HashMap;

public enum ESubtipoServico {
    MANUTENCAO_PREVENTIVA_E_CORRETIVA("Manutenção Preventiva e Corretiva"),
    CONFIGURACAO_E_GERENCIAMENTO_SOFTWARE("Configuração e Gerencimento de Software"),
    CONFIGURACAO_SUBSTITUICAO_ANALISE_DE_HARDWARE("Configuração, Substituição e Análise de Hardware"),
    LIMPEZA_E_OTIMIZACAO_DE_COMPUTADORES("Limpeza e Otimização de Computadores"),
    ADMINISTRACAO_DE_REDE_DE_COMPUTADORES("Administração de Rede de Computadores"),
    OTIMIZACAO_E_ANALISE("Otimização e Análise"),
    LEVANTAMENTO_E_ANALISE_DE_RISCOS("Levantamento e Análise de Riscos"),
    RECUPERACAO_E_PLANO_DE_CONTIGENCIA("Recuperação e Plano de Contingência"),
    ADMINISTRACAO_DE_BANCO_DE_DADOS("Administração de Banco de Dados"),
    ANALISE_DE_BANCO_DE_DADOS("Análise de Banco de Dados"),
    ANALISE_DE_BUSINESS_INTELLIGENCE("Análise de Business Intelligence"),
    CIENCIA_DE_DADOS("Ciência de Dados"),
    MODELAGEM_DE_BANCO_DE_DADOS("Modelagem de Banco de Dados"),
    DESENVOLVIMENTO_MOBILE("Desenvolvimento Mobile"),
    DESENVOLVIMENTO_WEB("Desenvolvimento Web"),
    DESENVOLVIMENTO_DESKTOP("Desenvolvimento Desktop"),
    SISTEMAS_EMBARCADOS("Sistemas Embarcados"),
    FRONTEND("Frontend"),
    FULLSTACK("Fullstack"),
    WEB_DESIGN("Web Design"),
    DESIGN_GRAFICO("Design Gráfico"),
    DESIGN_DE_INTERFACE("Design de Interface"),
    LEVANTAMENTO_DE_REQUISITOS("Levantamento de Requisitos"),
    ELABORACAO_DE_DOCUMENTACAO("Elaboração de Documentação"),
    MODELAGEM_DE_SISTEMAS("Modelagem de Sistemas"),
    BACKUP_E_RECUPERACAO_DE_DADOS("Backup e Recuperação de Dados"),
    AUDITORIA_E_ANALISE_DE_RISCOS("Auditoria e Análise de Riscos"),
    GERENCIAMENTO_DE_REDES("Gerenciamento de Redes"),
    CONTROLE_DE_ACESSO_A_INFRAESTRUTURA("Controle de Acesso à Infraestrutura"),
    ANALISE_DE_PROCESSOS("Análise de Processos");

    private String subtipo;
    private static HashMap<Integer, ESubtipoServico> hashEnumId;


    private ESubtipoServico(String subtipo) {
        this.subtipo = subtipo;
    }

    public String getSubtipo() {
        return subtipo;
    }

    public int getId() {
        return this.ordinal() + 1;
    }

    public static ESubtipoServico getById(int id) {

        ESubtipoServico subtipoServico = null;

        if (hashEnumId == null) {
            initializeMapping();
        }

        if (hashEnumId.containsKey(id)) {
            subtipoServico = hashEnumId.get(id);
        }

        return subtipoServico;
    }

    public static ESubtipoServico getByString(String texto) {
        ESubtipoServico eTipoContato = null;

        for (ESubtipoServico obj : ESubtipoServico.values()) {

            if (obj.subtipo.equalsIgnoreCase(texto)) {
                eTipoContato = obj;
            }
        }

        return eTipoContato;
    }

    private static void initializeMapping() {

        hashEnumId = new HashMap<>();

        for (ESubtipoServico enumSubtipo : ESubtipoServico.values()) {
            hashEnumId.put(enumSubtipo.ordinal() + 1, enumSubtipo);
        }
    }
}
