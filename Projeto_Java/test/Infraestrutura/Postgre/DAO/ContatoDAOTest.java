package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.Contato;
import Dominio.Enum.ETipoContato;
import Infraestrutura.Postgre.Util.Persistencia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

class ContatoDAOTest {

    private Persistencia persistencia = Persistencia.get();
    private Connection conexao = persistencia.getConexao();
    private ContatoDAO contatoDAO = new ContatoDAO();
    private int id = 1;


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

        conexao.setAutoCommit(false);

        Contato contato = new Contato("garotozero", 1, ETipoContato.FACEBOOK.getId());
        System.out.println(contato);

        contatoDAO.adicionar(contato);

        Contato contato2 = contatoDAO.obterPorId(contato.getId());
        System.out.println(contato2);

        assert "garotozero".equals(contato2.getDescricao());

        conexao.rollback();
    }

    @Test
    void obterPorId() throws SQLException {
        Contato contato = contatoDAO.obterPorId(id);
        System.out.println(contato);
        assert id == contato.getId();
    }

    @Test
    void atualizar() throws SQLException {

        conexao.setAutoCommit(false);

        Contato contato1 = contatoDAO.obterPorId(id);
        contato1.setDescricao(String.valueOf(LocalDateTime.now()));
        System.out.println(contato1);

        contatoDAO.atualizar(contato1);

        Contato contato2 = contatoDAO.obterPorId(id);
        System.out.println(contato2);

        assert contato1.toString().equals(contato2.toString());

        conexao.rollback();
    }

    @Test
    void excluirPorId()
            throws SQLException {

        conexao.setAutoCommit(false);

        Contato contato = contatoDAO.obterPorId(id);
        System.out.println(contato);

        contatoDAO.excluirPorId(contato.getId());
        Contato contatoExcluido = contatoDAO.obterPorId(contato.getId());
        System.out.println(contatoExcluido);

        assert contatoExcluido == null;

        conexao.rollback();
    }


    @Test
    void obterTodos() throws SQLException {
        List<Contato> contatos = contatoDAO.obterTodos(null, null);
        System.out.println(contatos.size());
        assert contatos.size() > 0;
    }

    @Test
    void obterTodosPorTipo() throws SQLException {
        List<Contato> contatos = contatoDAO.obterTodosPorTipo(ETipoContato.FACEBOOK, null,null);
        System.out.println(contatos.size());
        assert contatos.size() > 0;
    }

    @Test
    void obterTodosPorUsuario() throws SQLException {
        List<Contato> contatos = contatoDAO.obterTodosPorUsuario(1, null, null);
        contatos.forEach(System.out::println);
        assert contatos.size() > 0;
    }

    @Test
    void obterTodosPorTipoEUsuario() throws SQLException {
        List<Contato> contatos = contatoDAO.obterTodosPorTipoEUsuario(ETipoContato.TWITTER, 1, 5, 0);
        contatos.forEach(System.out::println);
        assert contatos.size() > 0;
    }
}