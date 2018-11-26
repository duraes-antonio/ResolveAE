package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.Comentario;
import Infraestrutura.Postgre.Util.Persistencia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

class ComentarioDAOTest {

    private Persistencia persistencia = Persistencia.get();
    private Connection conexao = persistencia.getConexao();
    private ComentarioDAO comentarioDAO = new ComentarioDAO();;
    private int id = 1;

    @AfterEach
    void tearDown() {

        try {
            conexao.close();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void adicionar() throws SQLException {

        Comentario comentario = new Comentario(String.valueOf(LocalDateTime.now()), 1);

        conexao.setAutoCommit(false);

        comentarioDAO.adicionar(comentario);
        comentario = comentarioDAO.obterPorId(comentario.getId());

        System.out.println(comentario);

        assert comentario.toString().equals(comentario.toString());

        conexao.rollback();
    }

    @Test
    void obterPorId() throws SQLException {
        Comentario comentario = comentarioDAO.obterPorId(id);
        System.out.println(comentario);
        assert comentario.getId() == id;
    }

    @Test
    void atualizar() throws SQLException {

        conexao.setAutoCommit(false);

        Comentario comentario = comentarioDAO.obterPorId(id);
        comentario.setComentario("777");
        System.out.println(comentario);

        comentarioDAO.atualizar(comentario);

        Comentario comentario2 = comentarioDAO.obterPorId(id);
        System.out.println(comentario2);

        assert comentario2.getComentario().equals(comentario.getComentario());

        conexao.rollback();
    }

    @Test
    void excluirPorId() throws SQLException {

        conexao.setAutoCommit(false);

        Comentario comentario = comentarioDAO.obterPorId(id);
        System.out.println(comentario);

        comentarioDAO.excluirPorId(comentario.getId());
        Comentario comentarioExcluido = comentarioDAO.obterPorId(comentario.getId());
        System.out.println(comentarioExcluido);

        assert comentarioExcluido == null;

        conexao.rollback();
    }


    @Test
    void obterTodos() throws SQLException {
        List<Comentario> comentarios = comentarioDAO.obterTodos(null, null);
        System.out.println(comentarios.size());
        assert comentarios.size() > 0;
    }

    @Test
    void obterPorAvaliacao() throws SQLException {
        Comentario comentario = comentarioDAO.obterPorAvaliacao(6);
        System.out.println(comentario);
        assert comentario.getFkAvalicao() == 6;
    }

    @Test
    void obterTodosPorUsuario() throws SQLException {
        List<Comentario> comentarios = comentarioDAO.obterTodosPorUsuario(7, null, null);
        System.out.println(comentarios.size());
        assert comentarios.size() > 0;
    }

    @Test
    void obterTodosPorServico() throws SQLException {
        List<Comentario> comentarios = comentarioDAO.obterTodosPorServico(4, null, null);
        System.out.println(comentarios.size());
        assert comentarios.size() > 0;
    }

}