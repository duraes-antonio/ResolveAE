package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.Avaliacao;
import Dominio.Entidades.Contrato;
import Dominio.Entidades.Servico;
import Dominio.Entidades.ServicoSubtipoServico;
import Infraestrutura.Postgre.Util.Persistencia;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

class ContratoDAOTest {

    ContratoDAO contratoDAO = new ContratoDAO();
    ServicoDAO servicoDAO = new ServicoDAO();
    Connection conexao = Persistencia.get().getConexao();
    Random rand = new Random();
    int id = 1;

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

    @Test
    void excluirPorId() throws SQLException {

        List<Contrato> contratos = contratoDAO.obterTodos(1000, null);
        Contrato contrato = contratoDAO.obterPorId(rand.nextInt((contratos.size() - 2) + 1) + 2);
        Servico servico = servicoDAO.obterPorContrato(contrato.getId(), null, null);
        System.out.println(contrato);

        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
        ServicoSubtipoServicoDAO sssDAO = new ServicoSubtipoServicoDAO();

        for (Avaliacao x : avaliacaoDAO.obterTodasPorServico(servico.getId(), null, null)) {
            avaliacaoDAO.excluirPorId(x.getId());
        }

        for (ServicoSubtipoServico sss : sssDAO.obterPorServico(servico.getId(), null, null)) {
            sssDAO.excluirPorId(sss.getId());
        }

        servicoDAO.excluirPorId(servico.getId());

        contratoDAO.excluirPorId(contrato.getId());
        Contrato contratoExcluido = contratoDAO.obterPorId(contrato.getId());
        System.out.println(contratoExcluido);

        assert contratoExcluido == null;
    }

    @Test
    void obterPorId() throws SQLException {
        Contrato contrato = contratoDAO.obterPorId(1);
        System.out.println(contrato);
        assert contrato.getId() == 1;
    }
}