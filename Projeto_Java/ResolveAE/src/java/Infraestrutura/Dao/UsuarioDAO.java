/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Infraestrutura.Dao;

import Dominio.Entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 20161BSI0314
 */
public class UsuarioDAO implements IUsuarioDAO {

    @Override
    public List<Usuario> getAll() {
        //ABERTURA DE CONEXAO COM O BANCO
        String query = "SELECT * FROM USUARIO";
        //EXECUCAO DA QUERY
        //FECHAR CONEXAO
        List<Usuario> users = new ArrayList<>();
        //LEITURA DO DATASET
        
        return users;
    }

    @Override
    public List<Usuario> getAllByBairro(String bairro) {
        //ABERTURA DA CONEXAO COM O BANCO
        String query = "SELECT U.* FROM Usuario AS U "
                + "INNER JOIN Informacoes AS I ON I.IdUsuario = U.IdUsuario "
                + "INNER JOIN Endereco AS E ON E.IdInformacoes = I.IdInformacoes "
                + "INNER JOIN Bairro AS B ON E.IdBairro = B.IdBairro "
                + "WHERE B.Nome like = '"+bairro+"'";
        //EXECUCAO DA QUERY
        List<Usuario> users = new ArrayList<>();
        //LEITURA DO DATASET
        return users;
    }

    @Override
    public List<Usuario> getAllByCidade(String cidade) {
        //ABERTURA DA CONEXAO COM O BANCO
        String query = "SELECT U.* FROM Usuario AS U "
                + "INNER JOIN Informacoes AS I ON I.IdUsuario = U.IdUsuario "
                + "INNER JOIN Endereco AS E ON E.IdInformacoes = I.IdInformacoes "
                + "INNER JOIN Bairro AS B ON E.IdBairro = B.IdBairro "
                + "INNER JOIN Cidade AS C ON C.IdCidade = B.IdCidade"
                + "WHERE C.Nome like '"+cidade+"'";
        //EXECUCAO DA QUERY
        List<Usuario> users = new ArrayList<>();
        //LEITURA DO DATASET
        return users;
    }

    @Override
    public List<Usuario> getAllByEstado(String estado) {
        //ABERTURA DA CONEXAO COM O BANCO
        String query = "SELECT U.* FROM Usuario AS U "
                + "INNER JOIN Informacoes AS I ON I.IdUsuario = U.IdUsuario "
                + "INNER JOIN Endereco AS E ON E.IdInformacoes = I.IdInformacoes "
                + "INNER JOIN Bairro AS B ON E.IdBairro = B.IdBairro "
                + "INNER JOIN Cidade AS C ON C.IdCidade = B.IdCidade"
                + "INNER JOIN Estado AS E ON E.IdEstado = C.IdEstado"
                + "WHERE E.Nome like '"+estado+"'";               
        //EXECUCAO DA QUERY
        List<Usuario> users = new ArrayList<>();
        //LEITURA DO DATASET
        return users;
    }

    @Override
    public Usuario getEmail(String email) {
        //ABERTURA DA CONEXAO COM O BANCO
        String query = "SELECT U.* FROM Usuario AS U "
                + "WHERE E.Nome like '"+email+"'";               
        //EXECUCAO DA QUERY
        Usuario user = new Usuario();
        //LEITURA DO DATASET
        return user;
    }

    @Override
    public void updateUsuario(Usuario user) {
        //ABERTURA DA CONEXAO COM O BANCO
        String query = "UPDATE Usuario SET Nome='"+user.getNome()+"', Email='"+user.getLogin()+"', SobreUsuario='"+user.getSobre()+"'"
                + "WHERE IdUsuario="+user.getIdUsuario()+"";
        //EXECUCAO DA QUERY
    }

    @Override
    public void insertUsario(Usuario user) {
        //ABERTURA DA CONEXAO COM O BANCO
        String query = "INSERT Usuario(Nome,Email,SobreUsuario,Senha)"
                + "VALUES"
                + " (Nome='"+user.getNome()+"', Email='"+user.getLogin()+"', SobreUsuario='"+user.getSobre()+"', Senha='"+user.getSenha()+"')";
        //EXECUCAO DA QUERY
    }

}
