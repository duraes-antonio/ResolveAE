package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.Contrato;
import Dominio.Entidades.Servico;
import Infraestrutura.Postgre.Util.Persistencia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

class ContratoDAOTest {

    ContratoDAO contratoDAO = new ContratoDAO();
    ServicoDAO servicoDAO = new ServicoDAO();
    Connection conexao = Persistencia.get().getConexao();
    int id = 2;

    @BeforeEach
    void setUp() {
        id++;
    }

    @Test
    void obterTodos() throws SQLException {
        List<Contrato> contratos = contratoDAO.obterTodos(5, null);

        for (Contrato contrato: contratos) {
            System.out.println(contrato);
        }

        assert contratos.size() > 0;
    }

    @Test
    void obterTodosPorUsuario() throws SQLException {
        List<Contrato> contratos = contratoDAO.obterTodosPorUsuario(4, 3, null);

        for (Contrato contrato: contratos) {
            System.out.println(contrato);
        }

        assert contratos.size() > 0;
    }

    @Test
    void obterTodosPorDescricao() throws SQLException {
        List<Contrato> contratos = null;
        contratos = contratoDAO.obterTodosPorDescricao("imagine", 5, null);

        for (Contrato contrato: contratos) {
            System.out.println(contrato);
        }

        assert contratos.size() > 0;
    }

    @Test
    void obterTodosPorData() throws SQLException, FileNotFoundException {
        List<Contrato> contratos = null;
        LocalDate localDate1 = LocalDate.of(2015, 1, 1);
        LocalDate localDate2 = LocalDate.of(2018, 1, 1);

        contratos = contratoDAO.obterTodosPorData(localDate1, localDate2, 3, null);

        for (Contrato contrato: contratos) {
            System.out.println(contrato);
        }

        assert contratos.size() > 0;
    }

    @Test
    void adicionar() throws SQLException {
        String descricao = "1245678";
        LocalDate inicio = LocalDate.now();
        LocalDate fim = LocalDate.of(2018, 12, 31);
        Contrato contrato = null;
        contrato = new Contrato(descricao, inicio, fim, LocalDateTime.now(),150,1);
        contratoDAO.adicionar(contrato);

        Contrato contratoObtido = null;
        contratoObtido = contratoDAO.obterPorId(contrato.getId());
        System.out.println(contrato);
        assert contratoObtido.getDescricao().equals(descricao);
    }

    @Test
    void atualizar() throws SQLException {
        String descricao = String.valueOf(LocalDateTime.now());
        Contrato contrato = contratoDAO.obterPorId(1);
        contrato.setDescricao(descricao);
        contratoDAO.atualizar(contrato);
        System.out.println(contrato);
        assert contratoDAO.obterPorId(1).getDescricao().equals(descricao);
    }

    //TODO depende do DAO servico
    //@Test
    void excluirPorId() throws SQLException {

        try {
            conexao.setAutoCommit(false);

            Contrato contrato = contratoDAO.obterPorId(id);
            Servico servico = null;//servicoDAO.obterPorContrato(contrato.getId());

            servicoDAO.excluirPorId(servico.getId());
            contratoDAO.excluirPorId(contrato.getId());

            conexao.commit();
            assert true;
        }

        catch (SQLException e) {
            conexao.rollback();
        }
    }

    @Test
    void obterPorId() throws SQLException {
        Contrato contrato = contratoDAO.obterPorId(1);
        System.out.println(contrato);
        assert contrato.getId() == 1;
    }
}