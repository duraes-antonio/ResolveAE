package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.Endereco;
import Dominio.Enum.EEstado;
import Infraestrutura.Postgre.Util.Persistencia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

class EnderecoDAOTest {

    Persistencia persistencia = Persistencia.get();
    Connection conexao = persistencia.getConexao();
    EnderecoDAO enderecoDAO = new EnderecoDAO();
    Random rand = new Random();
    int id = 1;

    @AfterEach
    void tearDown() {
    }

    @Test
    void adicionar() throws SQLException {
        Endereco endereco = new Endereco("jardim carapina", "serra", "es", 29161699, 1);
        enderecoDAO.adicionar(endereco);
        assert enderecoDAO.obterPorId(endereco.getId()).getBairro().equals(endereco.getBairro());
    }

    @Test
    void obterPorId() throws SQLException {
        Endereco endereco = enderecoDAO.obterPorId(2);
        System.out.println(endereco);
        assert 2 == endereco.getId();
    }

    @Test
    void atualizar() throws SQLException {
        String cidade = "Viana";
        Endereco endereco = enderecoDAO.obterPorId(2);
        System.out.println(endereco);
        endereco.setCidade(cidade);
        enderecoDAO.atualizar(endereco);

        Endereco endereco2 = enderecoDAO.obterPorId(2);
        System.out.println(endereco2);

        assert endereco2.getCidade().equals(cidade);
    }

    @Test
    void excluirPorId() throws SQLException {

        List<Endereco> enderecos = enderecoDAO.obterTodos(1000, null);
        Endereco endereco = enderecoDAO.obterPorId(rand.nextInt((enderecos.size() - 2) + 1) + 2);

        System.out.println(endereco);
        enderecoDAO.excluirPorId(endereco.getId());

        Endereco endereco2 = enderecoDAO.obterPorId(id);
        System.out.println(endereco2);

        assert endereco2 == null;
    }


    @Test
    void obterTodos() throws SQLException {
        List<Endereco> enderecos = enderecoDAO.obterTodos(3, 0);

        for (Endereco endereco: enderecos) {
            System.out.println(endereco);
        }

        assert enderecos.size() > 0;
    }

    @Test
    void obterTodosPorBairro() throws SQLException {
        List<Endereco> enderecos = enderecoDAO.obterTodosPorBairro("Carapina", 3, 0);

        for (Endereco endereco: enderecos) {
            System.out.println(endereco);
        }

        assert enderecos.size() > 0;
    }

    @Test
    void obterTodosPorCidade() throws SQLException {
        List<Endereco> enderecos = enderecoDAO.obterTodosPorCidade("Vila Velha", 3, 0);

        for (Endereco endereco: enderecos) {
            System.out.println(endereco);
        }

        assert enderecos.size() > 0;
    }

    @Test
    void obterTodosPorEstado() throws SQLException {
        List<Endereco> enderecos = enderecoDAO.obterTodosPorEstado(EEstado.ES, 3, 0);

        for (Endereco endereco: enderecos) {
            System.out.println(endereco);
        }

        assert enderecos.size() > 0;
    }

    @Test
    void obterTodosPorCep() throws SQLException {
        List<Endereco> enderecos = enderecoDAO.obterTodosPorCep(29161699, 3, 0);

        for (Endereco endereco: enderecos) {
            System.out.println(endereco);
        }

        assert enderecos.size() > 0;
    }
}