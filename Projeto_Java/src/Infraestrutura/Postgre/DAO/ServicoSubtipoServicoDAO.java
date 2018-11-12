package Infraestrutura.Postgre.DAO;

import Infraestrutura.Enum.ETab;

public class ServicoSubtipoServicoDAO {

    public static final String ID = ETab.SERVICO_SUBTIPO_SERVICO.get() + ".id";
    public static final String FK_SERVICO = ETab.SERVICO_SUBTIPO_SERVICO.get() + ".fk_servico";
    public static final String FK_SUBTIPO_SERVICO = ETab.SERVICO_SUBTIPO_SERVICO.get() + ".fk_subtipo_servico";
}
