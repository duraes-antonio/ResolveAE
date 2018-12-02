package Infraestrutura.Hibernate.DAO;

import Dominio.Entidades.Avaliacao;
import Dominio.Entidades.Comentario;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class AvaliacaoDAOHibernateTest {

    private AvaliacaoDAOHibernate avaliacaoDAO = new AvaliacaoDAOHibernate();
    private ComentarioDAOHibernate comentarioDAO = new ComentarioDAOHibernate();
    private int id = 4;

    @Test
    void adicionar() {

        Avaliacao avaliacao = new Avaliacao(5, 1, 1, null);
        avaliacaoDAO.adicionar(avaliacao);

        Comentario comentario = new Comentario(String.valueOf(LocalDateTime.now()), avaliacao.getId());
        comentarioDAO.adicionar(comentario);

        avaliacao.setComentario(comentario);

        System.out.println(avaliacao);

        Avaliacao avaliacao2 = avaliacaoDAO.obterPorId(avaliacao.getId());
        Comentario comentario2 = comentarioDAO.obterPorId(comentario.getId());

        avaliacao2.setComentario(comentario2);

        System.out.println(avaliacao2);

        assert avaliacao.toString().equals(avaliacao2.toString());
    }

    @Test
    void obterPorId() {

        Avaliacao avaliacao = avaliacaoDAO.obterPorId(id);
        System.out.println(avaliacao);
        assert avaliacao.getId() == id;
    }

    @Test
    void atualizar() {

        Avaliacao avaliacao1 = avaliacaoDAO.obterPorId(id);
        System.out.println(avaliacao1);

        //Atualize o comentário;
        Comentario comentario = comentarioDAO.obterPorAvaliacao(avaliacao1.getId());
        comentario.setComentario(String.valueOf(LocalDateTime.now()));

        //Atualize a nota da avaliação;
        avaliacao1.setNota(3);
        avaliacao1.setComentario(comentario);

        comentarioDAO.atualizar(comentario);
        avaliacaoDAO.atualizar(avaliacao1);

        Avaliacao avaliacao2 = avaliacaoDAO.obterPorId(id);
        Comentario comentario2 = comentarioDAO.obterPorId(comentario.getId());
        avaliacao2.setComentario(comentario2);

        System.out.println(avaliacao2);

        assert avaliacao1.toString().equals(avaliacao2.toString());

    }

//    @Test
    void excluirPorId() {


        Avaliacao avaliacao1 = avaliacaoDAO.obterPorId(id);
        Comentario comentario1 = comentarioDAO.obterPorAvaliacao(avaliacao1.getId());

        System.out.println(avaliacao1);

        comentarioDAO.excluirPorId(comentario1.getId());
        avaliacaoDAO.excluirPorId(avaliacao1.getId());

        Avaliacao avaliacao2 = avaliacaoDAO.obterPorId(avaliacao1.getId());
        System.out.println(avaliacao2);

        assert avaliacao2 == null;

    }

    @Test
    void obterTodasPorUsuario() {
        List<Avaliacao> avaliacoes = avaliacaoDAO.obterTodasPorUsuario(7, null, null);
        System.out.println(avaliacoes.size());
        assert avaliacoes.size() > 0;
    }

    @Test
    void obterTodasPorServico() {
        List<Avaliacao> avaliacoes = avaliacaoDAO.obterTodasPorServico(4, null, null);
        System.out.println(avaliacoes.size());
        assert avaliacoes.size() > 0;
    }

    @Test
    void obterTodos() {
        List<Avaliacao> avaliacoes = avaliacaoDAO.obterTodos(null, null);
        System.out.println(avaliacoes.size());
        assert avaliacoes.size() > 0;
    }
}