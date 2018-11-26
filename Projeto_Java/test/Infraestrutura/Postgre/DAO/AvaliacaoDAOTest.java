package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.Avaliacao;
import Dominio.Entidades.Comentario;
import Infraestrutura.Postgre.Util.Persistencia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;


class AvaliacaoDAOTest {

    private Persistencia persistencia = Persistencia.get();
    private Connection conexao = persistencia.getConexao();
    private AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();;
    private ComentarioDAO comentarioDAO = new ComentarioDAO();
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

        conexao.setAutoCommit(false);

        Avaliacao avaliacao = new Avaliacao(5, 1, 1, null);
        avaliacaoDAO.adicionar(avaliacao);

        Comentario comentario = new Comentario(String.valueOf(LocalDateTime.now()), avaliacao.getId());
        comentarioDAO.adicionar(comentario);

        avaliacao.setComentario(comentario);

        System.out.println(avaliacao);

        avaliacaoDAO.atualizar(avaliacao);

        Avaliacao avaliacao2 = avaliacaoDAO.obterPorId(avaliacao.getId());

        System.out.println(avaliacao2);

        assert avaliacao.toString().equals(avaliacao2.toString());

        conexao.rollback();
    }

    @Test
    void obterPorId() throws SQLException {
        Avaliacao avaliacao = avaliacaoDAO.obterPorId(id);
        System.out.println(avaliacao);
        assert avaliacao.getId() == id;
    }

    @Test
    void atualizar() throws SQLException {

        conexao.setAutoCommit(false);


        Avaliacao avaliacao1 = avaliacaoDAO.obterPorId(id);
        System.out.println(avaliacao1);

        //Atualize o comentário;
        Comentario comentario = avaliacao1.getComentario();
        comentario.setComentario(String.valueOf(LocalDateTime.now()));

        //Atualize a nota da avaliação;
        avaliacao1.setNota(3);

        comentarioDAO.atualizar(comentario);
        avaliacaoDAO.atualizar(avaliacao1);

        Avaliacao avaliacao2 = avaliacaoDAO.obterPorId(id);
        System.out.println(avaliacao2);

        assert avaliacao1.toString().equals(avaliacao2.toString());


        conexao.rollback();
    }

    @Test
    void excluirPorId() throws SQLException {

        conexao.setAutoCommit(false);

        Avaliacao avaliacao1 = avaliacaoDAO.obterPorId(id);

        System.out.println(avaliacao1);

        comentarioDAO.excluirPorId(avaliacao1.getComentario().getId());
        avaliacaoDAO.excluirPorId(avaliacao1.getId());

        Avaliacao avaliacao2 = avaliacaoDAO.obterPorId(avaliacao1.getId());
        System.out.println(avaliacao2);

        assert avaliacao2 == null;

        conexao.rollback();
    }


    @Test
    void obterTodos() throws SQLException {
        List<Avaliacao> avaliacoes = avaliacaoDAO.obterTodos(null, null);
        System.out.println(avaliacoes.size());
        assert avaliacoes.size() > 0;
    }

    @Test
    void obterTodasPorUsuario() throws SQLException {
        List<Avaliacao> avaliacoes = avaliacaoDAO.obterTodasPorUsuario(7, null, null);
        System.out.println(avaliacoes.size());
        assert avaliacoes.size() > 0;
    }

    @Test
    void obterTodasPorServico() throws SQLException {
        List<Avaliacao> avaliacoes = avaliacaoDAO.obterTodasPorServico(4, null, null);
        System.out.println(avaliacoes.size());
        assert avaliacoes.size() > 0;
    }
}