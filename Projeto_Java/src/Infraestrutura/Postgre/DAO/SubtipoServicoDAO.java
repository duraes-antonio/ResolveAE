package Infraestrutura.Postgre.DAO;

import Infraestrutura.Enum.ETab;

public class SubtipoServicoDAO {

    public static final String ID = ETab.SUBTIPO_SERVICO.get() + ".id";
    public static final String NOME = ETab.SUBTIPO_SERVICO.get() + ".nome";
    public static final String FK_TIPO_SERVICO = ETab.SUBTIPO_SERVICO.get() + ".fk_tipo_sevico";

}
