package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.Avaliacao;
import Dominio.Entidades.Servico;
import Dominio.Entidades.ServicoSubtipoServico;
import Dominio.Enum.ESubtipoServico;
import Dominio.Enum.ETipoServico;
import Infraestrutura.Postgre.Util.Persistencia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

class ServicoDAOTest {

    ServicoDAO servicoDAO = new ServicoDAO();
    Servico servico = new Servico(
            "Servico de teste",
            "Descrição de teste",
            ESubtipoServico.getByTipoServico(ETipoServico.BANCO_DE_DADOS),
            35,
            ETipoServico.BANCO_DE_DADOS.getId(),
            2,
            1);
    Persistencia persistencia = Persistencia.get();
    Connection conexao;
    int id = 1;

    @BeforeEach
    void setUp() {
        conexao = persistencia.getConexao();
    }

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
    void obterTodos() throws SQLException {

        List<Servico> servicos = servicoDAO.obterTodos(null, null);
        System.out.println(servicos.size());
        assert servicos.size() > 0;
    }

    @Test
    void obterTodosPorUsuario() throws SQLException {
        List<Servico> servicos = servicoDAO.obterTodosPorUsuario(1, null, null);
        System.out.println(servicos.size());
        assert servicos.size() > 0;
    }

    @Test
    void obterTodosPorContrato() throws SQLException {
        Servico servico = servicoDAO.obterPorContrato(1);
        System.out.println(servico);

        assert servico.getFkContrato() == 1;
    }

    @Test
    void obterTodosPorTitulo()
            throws SQLException {

        List<Servico> servicos = servicoDAO.obterTodosPorTitulo(".", null, null);

        System.out.println(servicos.size());
        assert servicos.size() > 0;
    }

    @Test
    void obterTodosPorDescricao()
            throws SQLException {

        List<Servico> servicos = servicoDAO.obterTodosPorDescricao("python", null, 0);
        System.out.println(servicos.size());
        assert servicos.size() > 0;
    }

    @Test
    void obterTodosPorValor()
            throws SQLException {
        List<Servico> servicos = servicoDAO.obterTodosPorValor(30, 60, null, 0);
        System.out.println(servicos.size());
        assert servicos.size() > 0;
    }

    @Test
    void obterTodosPorTipo()
            throws SQLException {

        List<Servico> servicos = servicoDAO.obterTodosPorTipo(ETipoServico.BANCO_DE_DADOS, null, null);
        System.out.println(servicos.size());
        assert servicos.size() > 0;
    }

    @Test
    void obterTodosPorSubtipo()
            throws SQLException {

        List<Servico> servicos = servicoDAO.obterTodosPorSubtipo(ESubtipoServico.ADMINISTRACAO_DE_BANCO_DE_DADOS, null, 0);
        System.out.println(servicos.size());
        assert servicos.size() > 0;
    }

    @Test
    void adicionar()
            throws SQLException {

        try {

            conexao.setAutoCommit(false);

            servico.setDescricao(String.valueOf(LocalDateTime.now()));

            servicoDAO.adicionar(servico);
            System.out.println(servico);

            Servico servico2 = servicoDAO.obterPorId(servico.getId());
            System.out.println(servico2);

            assert servico2.getDescricao().equals(servico.getDescricao());
        }

        finally {
            conexao.rollback();
        }
    }

    @Test
    void obterPorId() throws SQLException {
        Servico servico = servicoDAO.obterPorId(1);
        System.out.println(servico);
        assert servico.getId() == 1;
    }

    @Test
    void atualizar() throws SQLException {

        Servico servico1 = servicoDAO.obterPorId(1);
        String descricao = String.valueOf(LocalDateTime.now());

        servico1.setDescricao(descricao);

        servicoDAO.atualizar(servico1);
        Servico servico2 = servicoDAO.obterPorId(servico1.getId());

        assert servico2.getDescricao().equals(descricao);
    }

    @Test
    void excluirPorId() throws SQLException {

        try {
            conexao.setAutoCommit(false);

            Servico servico = servicoDAO.obterPorId(id);

            AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
            ServicoSubtipoServicoDAO sssDAO = new ServicoSubtipoServicoDAO();

            for (Avaliacao x : avaliacaoDAO.obterTodasPorServico(servico.getId(), null, null)) {
                avaliacaoDAO.excluirPorId(x.getId());
            }

            for (ServicoSubtipoServico sss : sssDAO.obterPorServico(servico.getId(), null, null)) {
                sssDAO.excluirPorId(sss.getId());
            }

            servicoDAO.excluirPorId(servico.getId());

            Servico servicoDel = servicoDAO.obterPorId(servico.getId());
            System.out.println(servicoDel);
            assert servicoDel == null;
        }

        finally {
            conexao.rollback();
        }
    }
}