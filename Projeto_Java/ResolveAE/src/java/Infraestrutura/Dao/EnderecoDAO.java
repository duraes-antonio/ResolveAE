/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Infraestrutura.Dao;

import Dominio.Entidades.Cep;
import Dominio.Entidades.Endereco;

/**
 *
 * @author elmr
 */
public class EnderecoDAO implements IEnderecoDAO{

    @Override
    public Cep getCep(int idUsuario) {
        String query = "SELECT END.Cep FROM Usuario AS U "
                + "INNER JOIN Endereco AS END ON END.fk_usuario = U.id "
                + "WHERE U.id ="+idUsuario+"";
         //EXECUCAO DA QUERY
        //FECHAR CONEXAO
        Cep uCep = new Cep();
        //LEITURA DO DATASET
        
        return uCep;
    }

    @Override
    public String getBairro(int idUsuario) {
        String query = "SELECT B.nome FROM Usuario AS U "
                + "INNER JOIN Endereco AS END ON END.fk_usuario = U.id "
                + "INNER JOIN Bairro AS B ON END.fk_bairro = B.id "
                + "WHERE U.id ="+idUsuario+"";
         //EXECUCAO DA QUERY
        //FECHAR CONEXAO
        String bairro = "";
        //LEITURA DO DATASET
        
        return bairro;
    }

    @Override
    public String getCidade(int idUsuario) {
        String query = "SELECT C.nome FROM Usuario AS U "
                + "INNER JOIN Endereco AS END ON END.fk_usuario = U.id "
                + "INNER JOIN Bairro AS B ON END.fk_bairro = B.id "
                + "INNER JOIN Cidade AS C ON B.fk_cidade = C.id"
                + "WHERE U.id ="+idUsuario+"";
         //EXECUCAO DA QUERY
        //FECHAR CONEXAO
        String cidade = "";
        //LEITURA DO DATASET
        
        return cidade;
    }

    @Override
    public String getEstado(int idUsuario) {
        String query = "SELECT E.nome FROM Usuario AS U "
                + "INNER JOIN Endereco AS END ON END.fk_usuario = U.id "
                + "INNER JOIN Bairro AS B ON END.fk_bairro = B.id "
                + "INNER JOIN Cidade AS C ON B.fk_cidade = C.id "
                + "INNER JOIN Estado AS E ON C.fk_estado = E.id"
                + "WHERE U.id ="+idUsuario+"";
         //EXECUCAO DA QUERY
        //FECHAR CONEXAO
        String estado = "";
        //LEITURA DO DATASET
        
        return estado;
    }

    @Override
    public void insertEndereco(int idUsuario, Endereco end) {
        String query = "INSER INTO ";
         //EXECUCAO DA QUERY
        //FECHAR CONEXAO
        String estado = "";
        //LEITURA DO DATASET
    }

    @Override
    public void updateEndereco(Endereco end) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
