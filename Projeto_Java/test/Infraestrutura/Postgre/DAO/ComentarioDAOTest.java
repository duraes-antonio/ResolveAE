package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.Comentario;
import Infraestrutura.Postgre.Util.Persistencia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

class ComentarioDAOTest {

    Persistencia persistencia = Persistencia.get();
    Connection conexao = persistencia.getConexao();
    ComentarioDAO comentarioDAO;
    int id = 5;

    @BeforeEach
    void setUp() {
        comentarioDAO = new ComentarioDAO();
    }

    @AfterEach
    void tearDown() {
        try {
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void adicionar() {
        Comentario comentario = new Comentario("Teste" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()), 1);
        try {
            comentarioDAO.adicionar(comentario);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void obterPorId() throws SQLException {
        Comentario comentario = comentarioDAO.obterPorId(id + 1);
        System.out.println(comentario);
        assert comentario.getId() == id + 1;
    }

    @Test
    void atualizar() throws SQLException {
        Comentario comentario = comentarioDAO.obterPorId(id + 1);
        comentario.setComentario("666");
        comentarioDAO.atualizar(comentario);
        comentario = null;
        comentario = comentarioDAO.obterPorId(id + 1);
        assert comentario.getComentario().equals("666");
    }

    @Test
    void excluirPorId() throws SQLException {
        Comentario comentario = comentarioDAO.obterPorId(id - 1);
        System.out.println(comentario);

        assert comentario.getId() == id + 1;

    }


    @Test
    void obterTodos() throws SQLException {
        List<Comentario> comentarios = comentarioDAO.obterTodos(5, null);
        comentarios.forEach(System.out::println);
        assert comentarios.size() > 0;
    }

    @Test
    void obterPorAvaliacao() throws SQLException {
        System.out.println(comentarioDAO.obterPorAvaliacao(6));
    }

    @Test
    void obterTodosPorUsuario() throws SQLException {
        List<Comentario> comentarios = comentarioDAO.obterTodosPorUsuario(5, 5, 0);
        comentarios.forEach(System.out::println);
    }

    @Test
    void obterTodosPorServico() throws SQLException {
        List<Comentario> comentarios = comentarioDAO.obterTodosPorServico(3, 5, 0);
        comentarios.forEach(System.out::println);
    }

}