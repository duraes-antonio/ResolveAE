/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.SubTipo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elmr
 */
public class SubtipoServicoDAO implements ISubtipoServicoDAO{

    @Override
    public List<SubTipo> getSubtiposByTipo(int tipo) {
        String query = "SELECT * FROM Subtipo_Servico "
                + "WHERER fk_tipo_servico = "+tipo+" ";
        //EXECUCAO DA QUERY
        //FECHAMENTO DA CONEXAO
        List<SubTipo> sbTipos = new ArrayList<>();
        //LEITURA DO DATASET
        return sbTipos;
    }

    @Override
    public void insertSubtipo(SubTipo sbTipo) {
        String query = "INSERT INTO Subtipo_Servico (descricao, fk_tipo_servico) "
                + "VALUES "
                + "(  "+sbTipo.getDescricao()+", "+sbTipo.getIdServico()+"  )";
        //EXECUCAO DA QUERY
        //FECHAMENTO DA CONEXAO
    }

    @Override
    public void updateSubtipo(SubTipo sbTipo) {
        String query = "UPDATE Subtipo_Servico SET descricao = "+sbTipo.getDescricao()+", fk_tipo_servico = "+sbTipo.getIdServico()+" "
                + "WHERE id = "+sbTipo.getIdBase()+" ";
        //EXECUCAO DA QUERY
        //FECHAMENTO DA CONEXAO
    }
    
}
