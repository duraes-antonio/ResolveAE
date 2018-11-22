package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.Avaliacao;
import Dominio.Entidades.Servico;
import Dominio.Enum.ESubtipoServico;
import Dominio.Enum.ETipoServico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

class ServicoDAOTest {

    ServicoDAO servicoDAO = new ServicoDAO();
    Servico servico;
    Random rand = new Random();

    @BeforeEach
    void setUp() {
        servico = new Servico(
                "Servico de teste",
                "Descrição de teste",
                ESubtipoServico.getByTipoServico(ETipoServico.BANCO_DE_DADOS),
                35,
                ETipoServico.BANCO_DE_DADOS.getId(),
                2,
                1);
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

        servico.setDescricao(String.valueOf(LocalDateTime.now()));

        servicoDAO.adicionar(servico);
        System.out.println(servico);

        Servico servico2 = servicoDAO.obterPorId(servico.getId());
        System.out.println(servico2);

        assert servico2.getDescricao().equals(servico.getDescricao());
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

        List<Servico> servicos = servicoDAO.obterTodos(10000, null);
        Servico servico = servicoDAO.obterPorId(rand.nextInt() % servicos.size() + 1);
        System.out.println(servico);

        servicoDAO.excluirPorId(servico.getId());
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();

        for (Avaliacao x : avaliacaoDAO.obterTodasPorServico(servico.getId(), null, null)) {
            avaliacaoDAO.excluirPorId(x.getId());
        }
        
        Servico servicoDel = servicoDAO.obterPorId(servico.getId());
        System.out.println(servicoDel);
        assert servicoDel == null;
    }
}