package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.SubtipoServico;
import Dominio.Enum.ETipoServico;
import Infraestrutura.Postgre.Util.Persistencia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

class SubtipoServicoDAOTest {


    private Persistencia persistencia = Persistencia.get();
    private Connection conexao = persistencia.getConexao();
    private int id = 1;
    private SubtipoServicoDAO subtipoDAO = new SubtipoServicoDAO();

    @AfterEach
    void tearDown() throws SQLException {
        if (!conexao.isClosed()) conexao.close();
    }

    @Test
    void obterTodos() throws SQLException {
        List<SubtipoServico> servicos = subtipoDAO.obterTodos(null, null);
        System.out.println(servicos.size());
        assert servicos.size() > 0;
    }

    @Test
    void adicionar() throws SQLException {

        try {
            conexao.setAutoCommit(false);

            SubtipoServico subtipoServico = new SubtipoServico(
                    "Formatação de computadores",
                    ETipoServico.MANUTENCAO_E_SUPORTE.getId()
            );

            subtipoDAO.adicionar(subtipoServico);

            SubtipoServico ss2 = subtipoDAO.obterPorId(subtipoServico.getId());
            System.out.println(subtipoServico);

            assert subtipoServico.getId() == ss2.getId();

        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            conexao.rollback();
        }
    }

    @Test
    void atualizar() throws SQLException {

        try {
            conexao.setAutoCommit(false);

            SubtipoServico sub1 = subtipoDAO.obterPorId(id);
            sub1.setNome(String.valueOf(LocalDateTime.now()));
            System.out.println(sub1);

            subtipoDAO.atualizar(sub1);

            SubtipoServico sub2 = subtipoDAO.obterPorId(sub1.getId());
            System.out.println(sub2);

            assert sub1.toString().equals(sub2.toString());

            conexao.rollback();

        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            conexao.rollback();
        }
    }

    @Test
    void excluirPorId() throws SQLException {

        try {
            conexao.setAutoCommit(false);

            SubtipoServico sub1 = subtipoDAO.obterPorId(id);
            System.out.println(sub1);

            subtipoDAO.excluirPorId(sub1.getId());

            SubtipoServico sub2 = subtipoDAO.obterPorId(sub1.getId());
            System.out.println(sub2);

            assert sub2 == null;

            conexao.rollback();

        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            conexao.rollback();
        }
    }

    @Test
    void obterPorId() throws SQLException {
        SubtipoServico subtipoServico = subtipoDAO.obterPorId(id);
        System.out.println(subtipoServico);
        assert subtipoServico != null;
    }

}