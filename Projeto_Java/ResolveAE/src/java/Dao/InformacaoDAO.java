/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Endereco;
import Model.Experiencia;
import Model.Telefone;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 20161BSI0314
 */
public class InformacaoDAO implements IInformacaoDao {



    @Override
    public List<Telefone> getContatos(int idInformacao) {
        //ABERTURA DE CONEXAO COM O BANCO
        String query = "SELECT C.* FROM Contatos AS C "
                + "INNER JOIN Informacoes AS I ON C.IdInformacoes = I.IdInformacoes"
                + "WHERE I.IdInformacoes="+idInformacao+"";
        //EXECUCAO DA QUERY
        //FECHAR CONEXAO
        List<Telefone> contatos = new ArrayList<>();
        //LEITURA DO DATASET
        
        return contatos;
    }

    @Override
    public List<Experiencia> getInfosProfissionais(int idInformacao) {
        //ABERTURA DE CONEXAO COM O BANCO
        String query = "SELECT IP.* FROM InfoProfissional AS IP "
                + "INNER JOIN Informacoes AS I ON C.IdInformacoes = I.IdInformacoes"
                + "WHERE I.IdInformacoes="+idInformacao+"";
        //EXECUCAO DA QUERY
        //FECHAR CONEXAO
        List<Experiencia> experiencia = new ArrayList<>();
        //LEITURA DO DATASET
        
        return experiencia;
    }

    @Override
    public Endereco getEndereco(int idInformacao) {
        //ABERTURA DE CONEXAO COM O BANCO
        String query = "SELECT End.Cep,B.Nome,C.Nome,E.Nome FROM Endereco AS End "
                + "INNER JOIN Bairro AS B ON B.IdBairro =  End.IdBairro"
                + "INNER JOIN Cidade AS C ON C.IdCidade = B.IdCidade"
                + "INNER JOIN Estado AS E ON E.IdEstado = C.IdEstado"
                + "WHERE End.IdInformacao ="+idInformacao+"";
        //EXECUCAO DA QUERY
        //FECHAR CONEXAO
        Endereco endereco = new Endereco();
        //LEITURA DO DATASET
        
        return endereco;
    }
    
}
