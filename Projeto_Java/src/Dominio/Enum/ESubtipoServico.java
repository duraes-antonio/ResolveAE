package Dominio.Enum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static Dominio.Enum.ETipoServico.*;

public enum ESubtipoServico {

    //Manutenção E Suporte
    MANUTENCAO_PREVENTIVA_E_CORRETIVA("Manutenção Preventiva e Corretiva", MANUTENCAO_E_SUPORTE),
    CONFIGURACAO_E_GERENCIAMENTO_SOFTWARE("Configuração e Gerencimento de Software", MANUTENCAO_E_SUPORTE),
    CONFIGURACAO_SUBSTITUICAO_ANALISE_DE_HARDWARE("Configuração, Substituição e Análise de Hardware", MANUTENCAO_E_SUPORTE),
    LIMPEZA_E_OTIMIZACAO_DE_COMPUTADORES("Limpeza e Otimização de Computadores", MANUTENCAO_E_SUPORTE),

    //Rede de computadores
    ADMINISTRACAO_DE_REDE_DE_COMPUTADORES("Administração de Rede de Computadores", REDE_DE_COMPUTADORES),
    OTIMIZACAO_E_ANALISE("Otimização e Análise", REDE_DE_COMPUTADORES),
    LEVANTAMENTO_E_ANALISE_DE_RISCOS("Levantamento e Análise de Riscos", REDE_DE_COMPUTADORES),
    RECUPERACAO_E_PLANO_DE_CONTIGENCIA("Recuperação e Plano de Contingência", REDE_DE_COMPUTADORES),

    //Banco de dados
    ADMINISTRACAO_DE_BANCO_DE_DADOS("Administração de Banco de Dados", BANCO_DE_DADOS),
    ANALISE_DE_BANCO_DE_DADOS("Análise de Banco de Dados", BANCO_DE_DADOS),
    ANALISE_DE_BUSINESS_INTELLIGENCE("Análise de Business Intelligence", BANCO_DE_DADOS),
    CIENCIA_DE_DADOS("Ciência de Dados", BANCO_DE_DADOS),
    MODELAGEM_DE_BANCO_DE_DADOS("Modelagem de Banco de Dados", BANCO_DE_DADOS),

    //Desenvolvimento
    DESENVOLVIMENTO_MOBILE("Desenvolvimento Mobile", DESENVOLVIMENTO),
    DESENVOLVIMENTO_WEB("Desenvolvimento Web", DESENVOLVIMENTO),
    DESENVOLVIMENTO_DESKTOP("Desenvolvimento Desktop", DESENVOLVIMENTO),
    SISTEMAS_EMBARCADOS("Sistemas Embarcados", DESENVOLVIMENTO),
    FRONTEND("Frontend", DESENVOLVIMENTO),
    FULLSTACK("Fullstack", DESENVOLVIMENTO),

    //Design
    WEB_DESIGN("Web Design", DESIGN),
    DESIGN_GRAFICO("Design Gráfico", DESIGN),
    DESIGN_DE_INTERFACE("Design de Interface", DESIGN),

    //Análise de Sistemas
    LEVANTAMENTO_DE_REQUISITOS("Levantamento de Requisitos", ANALISE_DE_SISTEMAS),
    ELABORACAO_DE_DOCUMENTACAO("Elaboração de Documentação", ANALISE_DE_SISTEMAS),
    MODELAGEM_DE_SISTEMAS("Modelagem de Sistemas", ANALISE_DE_SISTEMAS),

    //Segurança da Informação
    BACKUP_E_RECUPERACAO_DE_DADOS("Backup e Recuperação de Dados", SEGURANCA_DA_INFORMACAO),
    AUDITORIA_E_ANALISE_DE_RISCOS("Auditoria e Análise de Riscos", SEGURANCA_DA_INFORMACAO),
    GERENCIAMENTO_DE_REDES("Gerenciamento de Redes", SEGURANCA_DA_INFORMACAO),
    CONTROLE_DE_ACESSO_A_INFRAESTRUTURA("Controle de Acesso à Infraestrutura", SEGURANCA_DA_INFORMACAO),
    ANALISE_DE_PROCESSOS("Análise de Processos", SEGURANCA_DA_INFORMACAO);

    private String subtipo;
    private ETipoServico tipoServico;
    private static HashMap<Integer, ESubtipoServico> hashEnumId;


    private ESubtipoServico(String subtipo, ETipoServico tipoServico) {
        this.subtipo = subtipo;
        this.tipoServico = tipoServico;
    }

    public String getSubtipo() {
        return subtipo;
    }

    public int getId() {
        return this.ordinal() + 1;
    }

    public ETipoServico getTipoServico() {
        return this.tipoServico;
    }

    public static List<ESubtipoServico> getByTipoServico(ETipoServico tipo) {

        List<ESubtipoServico> subtipos = new ArrayList<>();

        ESubtipoServico[] values = ESubtipoServico.values();

        for (ESubtipoServico subtipo : values) {
            if (subtipo.getTipoServico() == tipo) subtipos.add(subtipo);
        }

        return subtipos;
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
