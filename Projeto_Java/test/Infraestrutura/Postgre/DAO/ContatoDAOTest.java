package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.Contato;
import Dominio.Enum.ETipoContato;
import Infraestrutura.Postgre.Util.Persistencia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

class ContatoDAOTest {

    Persistencia persistencia = Persistencia.get();
    Connection conexao = persistencia.getConexao();
    ContatoDAO contatoDAO = new ContatoDAO();
    int id = 1;
    Random rand = new Random();

    @BeforeEach
    void setUp() {
        id++;
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
        Contato contato = new Contato("garotozero", 1, ETipoContato.FACEBOOK.getId());
        contatoDAO.adicionar(contato);
        Contato contato2 = contatoDAO.obterPorId(contato.getId());

        assert "garotozero".equals(contato2.getDescricao());
    }

    @Test
    void obterPorId() throws SQLException {
        Contato contato = contatoDAO.obterPorId(id);
        System.out.println(contato);
        assert id == contato.getId();
    }

    @Test
    void atualizar() throws SQLException {
        Contato contato = contatoDAO.obterPorId(id);
        contato.setDescricao("Teste");
        contatoDAO.atualizar(contato);
        assert contatoDAO.obterPorId(id).getDescricao().equals("Teste");
    }

    @Test
    void excluirPorId()
            throws SQLException {

        List<Contato> contatos = contatoDAO.obterTodos(1000, null);
        Contato contato = contatoDAO.obterPorId(rand.nextInt((contatos.size() - 2) + 1) + 2);
        System.out.println(contato);

        contatoDAO.excluirPorId(contato.getId());
        Contato contatoExcluido = contatoDAO.obterPorId(contato.getId());
        System.out.println(contatoExcluido);

        assert contatoExcluido == null;
    }


    @Test
    void obterTodos() throws SQLException {
        List<Contato> contatos = contatoDAO.obterTodos(10, 0);
        assert contatos.size() > 0;
    }

    @Test
    void obterTodosPorTipo() throws SQLException {
        List<Contato> contatos = contatoDAO.obterTodosPorTipo(ETipoContato.FACEBOOK, 3, 0);
        contatos.forEach(System.out::println);
        assert contatos.size() > 0;
    }

    @Test
    void obterTodosPorUsuario() throws SQLException {
        List<Contato> contatos = contatoDAO.obterTodosPorUsuario(1, 5, 0);
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