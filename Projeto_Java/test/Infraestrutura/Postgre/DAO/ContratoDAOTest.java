package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.Avaliacao;
import Dominio.Entidades.Contrato;
import Dominio.Entidades.Servico;
import Dominio.Entidades.ServicoSubtipoServico;
import Infraestrutura.Postgre.Util.Persistencia;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

class ContratoDAOTest {

    private ContratoDAO contratoDAO = new ContratoDAO();
    private ServicoDAO servicoDAO = new ServicoDAO();
    private Connection conexao = Persistencia.get().getConexao();
    private int id = 1;

    @Test
    void obterTodos() throws SQLException {
        List<Contrato> contratos = contratoDAO.obterTodos(null, null);
        System.out.println(contratos.size());
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
        List<Contrato> contratos = contratoDAO.obterTodosPorDescricao(" ", null, null);
        System.out.println(contratos.size());
        assert contratos.size() > 0;
    }

    @Test
    void obterTodosPorData() throws SQLException {
        List<Contrato> contratos;
        LocalDate data1 = LocalDate.of(2015, 1, 1);
        LocalDate data2 = LocalDate.of(2018, 1, 1);

        contratos = contratoDAO.obterTodosPorData(data1, data2, null, null);
        System.out.println(contratos.size());

        assert contratos.size() > 0;
    }

    @Test
    void adicionar() throws SQLException {

        conexao.setAutoCommit(false);

        String descricao = String.valueOf(LocalDateTime.now());
        LocalDate inicio = LocalDate.now();
        LocalDate fim = LocalDate.of(2018, 12, 31);

        Contrato contrato1;
        contrato1 = new Contrato(descricao, inicio, fim, LocalDateTime.now(),150,1);
        System.out.println(contrato1);

        contratoDAO.adicionar(contrato1);

        Contrato contrato2 = contratoDAO.obterPorId(contrato1.getId());
        System.out.println(contrato2);

        assert contrato1.toString().equals(contrato2.toString());

        conexao.rollback();
    }

    @Test
    void atualizar() throws SQLException {

        conexao.setAutoCommit(false);

        Contrato contrato1 = contratoDAO.obterPorId(id);
        contrato1.setDescricao(String.valueOf(LocalDateTime.now()));

        contratoDAO.atualizar(contrato1);
        System.out.println(contrato1);

        Contrato contrato2 = contratoDAO.obterPorId(id);
        System.out.println(contrato2);

        assert contrato1.toString().equals(contrato2.toString());

        conexao.rollback();
    }

    @Test
    void excluirPorId() throws SQLException {

        conexao.setAutoCommit(false);

        Contrato contrato1 = contratoDAO.obterPorId(id);
        Servico servico = servicoDAO.obterPorContrato(contrato1.getId());
        System.out.println(contrato1);

        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        ServicoSubtipoServicoDAO sssDAO = new ServicoSubtipoServicoDAO();

        for (Avaliacao aval : avaliacaoDAO.obterTodasPorServico(servico.getId(), null, null)) {
            avaliacaoDAO.excluirPorId(aval.getId());
        }

        for (ServicoSubtipoServico sss : sssDAO.obterPorServico(servico.getId(), null, null)) {
            sssDAO.excluirPorId(sss.getId());
        }

        servicoDAO.excluirPorId(servico.getId());
        contratoDAO.excluirPorId(contrato1.getId());

        Contrato contrat2 = contratoDAO.obterPorId(contrato1.getId());
        System.out.println(contrat2);

        assert contrat2 == null;

        conexao.rollback();
    }

    @Test
    void obterPorId() throws SQLException {
        Contrato contrato = contratoDAO.obterPorId(id);
        System.out.println(contrato);
        assert id == contrato.getId();
    }
}