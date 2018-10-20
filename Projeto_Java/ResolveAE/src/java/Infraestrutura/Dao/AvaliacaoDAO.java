/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Infraestrutura.Dao;

import Dominio.Entidades.Avaliacao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 20161BSI0314
 */
public class AvaliacaoDAO implements IAvaliacaoDAO{

    @Override
    public List<Avaliacao> getAvaliacoesByUser(int idUsuario) {
        String query = "SELECT * FROM Avaliacao AS A"
                + "WHERE A.IdUsuario="+idUsuario+"";
        //ABERTURA DE CONEXAO COM O BANCO
        //EXECUCAO DA QUERY
        //FECHAMENTO DA CONEXAO
        List<Avaliacao> avaliacoes = new ArrayList<>();
        //LEITURA DO DATASET
        return avaliacoes;
    }

    @Override
    public List<Avaliacao> getAvaliacaoesByServico(int idServicoPrestado) {
        String query = "SELECT * FROM Avaliacao AS A"
                + "WHERE A.IdServicoPrestado="+idServicoPrestado+"";
        //ABERTURA DE CONEXAO COM O BANCO
        //EXECUCAO DA QUERY
        //FECHAMENTO DA CONEXAO
        List<Avaliacao> avaliacoes = new ArrayList<>();
        //LEITURA DO DATASET
        return avaliacoes;
    }

    @Override
    public void insertAvaliacao(Avaliacao avaliacao) {
        String query = "INSERT INTO Avaliacoes (Nota,Comentario,IdUsuario,IdServicoPrestado)"
                + "VALUES"
                + "("+avaliacao.getNota()+","+avaliacao.getComentario()+","+avaliacao.getIdUsuario()+","+avaliacao.getIdServicoPrestado()+")";
        //ABERTURA DE CONEXAO COM O BANCO
        //EXECUCAO DA QUERY
        //FECHAMENTO DA CONEXAO
    }
    
}
