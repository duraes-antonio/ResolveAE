package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.Cep;
import Dominio.Entidades.Endereco;
import Dominio.Enum.EEstado;
import Infraestrutura.Postgre.Util.Persistencia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

class EnderecoDAOTest {

    private Persistencia persistencia = Persistencia.get();
    private Connection conexao = persistencia.getConexao();
    private EnderecoDAO enderecoDAO = new EnderecoDAO();
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
    void adicionar() throws SQLException, IOException {

        conexao.setAutoCommit(false);

        Endereco endereco1 = new Cep(29161699).getEnderecoPorCep();
        endereco1.setFkUsuario(1);
        enderecoDAO.adicionar(endereco1);

        System.out.println(endereco1);

        Endereco endereco2 = enderecoDAO.obterPorId(endereco1.getId());
        assert endereco1.toString().equals(endereco2.toString());

        System.out.println(endereco2);

        conexao.rollback();
    }

    @Test
    void obterPorId() throws SQLException {
        Endereco endereco = enderecoDAO.obterPorId(id);
        System.out.println(endereco);
        assert id == endereco.getId();
    }

    @Test
    void atualizar() throws SQLException {

        conexao.setAutoCommit(false);

        Endereco endereco1 = enderecoDAO.obterPorId(id);
        endereco1.setCidade("Viana");
        enderecoDAO.atualizar(endereco1);
        System.out.println(endereco1);

        Endereco endereco2 = enderecoDAO.obterPorId(id);
        System.out.println(endereco2);

        assert endereco1.toString().equals(endereco2.toString());

        conexao.rollback();
    }

    @Test
    void excluirPorId() throws SQLException {

        conexao.setAutoCommit(false);

        Endereco endereco = enderecoDAO.obterPorId(id);
        System.out.println(endereco);

        enderecoDAO.excluirPorId(endereco.getId());

        Endereco endereco2 = enderecoDAO.obterPorId(id);
        System.out.println(endereco2);

        assert endereco2 == null;

        conexao.rollback();
    }


    @Test
    void obterTodos() throws SQLException {
        List<Endereco> enderecos = enderecoDAO.obterTodos(null, null);
        System.out.println(enderecos.size());
        assert enderecos.size() > 0;
    }

    @Test
    void obterTodosPorBairro() throws SQLException {
        List<Endereco> enderecos = enderecoDAO.obterTodosPorBairro("Carapina", null, null);
        System.out.println(enderecos.size());
        assert enderecos.size() > 0;
    }

    @Test
    void obterTodosPorCidade() throws SQLException {
        List<Endereco> enderecos = enderecoDAO.obterTodosPorCidade("Vila Velha", 3, 0);
        System.out.println(enderecos.size());
        assert enderecos.size() > 0;
    }

    @Test
    void obterTodosPorEstado() throws SQLException {
        List<Endereco> enderecos = enderecoDAO.obterTodosPorEstado(EEstado.ES, null, null);
        System.out.println(enderecos.size());
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

    @Test
    void obterTodosPorUsuario() throws SQLException {
        Endereco endereco = enderecoDAO.obterTodosPorUsuario(1);
        System.out.println(endereco);
        assert endereco != null;
    }
}