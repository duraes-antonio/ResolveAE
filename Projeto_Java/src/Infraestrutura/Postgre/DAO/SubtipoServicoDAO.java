/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Infraestrutura.Postgre.DAO;

import Infraestrutura.Enum.ETab;

/**
 * @author elmr
 */
public class SubtipoServicoDAO {

    //    @Override
//    public List<SubTipo> getSubtiposByTipo(int tipo) {
//        String query = "SELECT * FROM Subtipo_Servico "
//                + "WHERER fk_tipo_servico = "+tipo+" ";
//        //EXECUCAO DA QUERY
//        //FECHAMENTO DA CONEXAO
//        List<SubTipo> sbTipos = new ArrayList<>();
//        //LEITURA DO DATASET
//        return sbTipos;
//    }
//
//    @Override
//    public void insertSubtipo(SubTipo sbTipo) {
//        String query = "INSERT INTO Subtipo_Servico (descricao, fk_tipo_servico) "
//                + "VALUES "
//                + "(  "+sbTipo.getDescricao()+", "+sbTipo.getIdServico()+"  )";
//        //EXECUCAO DA QUERY
//        //FECHAMENTO DA CONEXAO
//    }
//
//    @Override
//    public void updateSubtipo(SubTipo sbTipo) {
//        String query = "UPDATE Subtipo_Servico SET descricao = "+sbTipo.getDescricao()+", fk_tipo_servico = "+sbTipo.getIdServico()+" "
//                + "WHERE id = "+sbTipo.getIdBase()+" ";
//        //EXECUCAO DA QUERY
//        //FECHAMENTO DA CONEXAO
//    }
    public static final String ID = ETab.SUBTIPO_SERVICO.get() + ".id";
    public static final String NOME = ETab.SUBTIPO_SERVICO.get() + ".nome";
    public static final String FK_TIPO_SERVICO = ETab.SUBTIPO_SERVICO.get() + ".fk_tipo_sevico";

}
