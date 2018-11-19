package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.Avaliacao;
import Dominio.Entidades.Comentario;
import Infraestrutura.Postgre.Util.Persistencia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


class AvaliacaoDAOTest {

    Persistencia persistencia = Persistencia.get();
    Connection conexao = persistencia.getConexao();
    AvaliacaoDAO avaliacaoDAO;
    ComentarioDAO comentarioDAO;
    int id = 11;

    @BeforeEach
    void setUp() {
        avaliacaoDAO = new AvaliacaoDAO();
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
    void adicionar() throws SQLException {

        try {
            conexao.setAutoCommit(false);

            Avaliacao avaliacao = new Avaliacao(5, 1, 1, null);
            avaliacaoDAO.adicionar(avaliacao);

            Comentario comentario = new Comentario("Teste" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()), avaliacao.getId());
            comentarioDAO.adicionar(comentario);

            avaliacao.setComentario(comentario);
            avaliacaoDAO.atualizar(avaliacao);

            conexao.commit();
        } catch (SQLException e) {
            conexao.rollback();
            e.printStackTrace();
        }
    }

    @Test
    void obterPorId() throws SQLException {
        Avaliacao avaliacao = avaliacaoDAO.obterPorId(1);
        System.out.println(avaliacao);
        assert avaliacao.getId() == 1;
    }

    @Test
    void atualizar() throws SQLException {
        Comentario comentario = comentarioDAO.obterPorId(18);
        comentario.setComentario("666");
        comentarioDAO.atualizar(comentario);
        comentario = null;
        comentario = comentarioDAO.obterPorId(id + 1);
        assert comentario.getComentario().equals("666");
    }

    //@Test
    void excluirPorId() throws SQLException {

        try {
            conexao.setAutoCommit(false);

            Avaliacao avaliacao = avaliacaoDAO.obterPorId(12);

            comentarioDAO.excluirPorId(avaliacao.getComentario().getId());
            avaliacaoDAO.excluirPorId(avaliacao.getId());

            conexao.commit();
            assert true;
        } catch (SQLException e) {
            conexao.rollback();
        }
    }


    @Test
    void obterTodos() {
    }

    @Test
    void obterTodasPorUsuario() {
    }

    @Test
    void obterTodasPorServico() {
    }

}