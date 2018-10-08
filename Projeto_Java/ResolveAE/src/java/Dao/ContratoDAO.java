/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Contrato;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author elmr
 */
public class ContratoDAO implements IContratoDAO{
    @Override
    public List<Contrato> getContratosByUser(int idUsuario) {
        String query = "SELECT * FROM Contrato "
                + "WHERE fk_usuario = "+idUsuario+" ";
        //EXECUCAO DA QUERY
        //FECHAMENTO DA CONEXAO
        List<Contrato> contratos = new ArrayList<>();
        //LEITURA DO DATA SET
        return contratos;
    }    
    @Override
    public void insertContrato(Contrato cnt) {
        String query = "INSERT INTO Contato (data_contrato, data_inicio_prestacao, data_fim_prestacao,data_ult_modif,descricao,fk_usuario) "
                + "VALUES "
                + "("+cnt.getDataCriacao()+", "+cnt.getDataInicioPrestacao()+", "+cnt.getDataFimPrestacao()+", "+cnt.getDataUltimaModificacao()+", "+cnt.getDescricao()+", "+cnt.getIdUsuario()+" )";
        //EXECUCAO DA QUERY
        //FECHAMENTO DA CONEXAO
    }

}
