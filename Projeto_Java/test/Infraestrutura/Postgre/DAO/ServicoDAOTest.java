package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.Servico;
import Dominio.Enum.ESubtipoServico;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ServicoDAOTest {

    ServicoDAO servicoDAO = new ServicoDAO();
    Random rand = new Random();

    @Test
    void obterTodos() throws SQLException {

        List<Servico> servicos = servicoDAO.obterTodos(100, 0);
        System.out.println(servicos.size());
        assert servicos.size() > 0;
    }

    @Test
    void obterTodosPorUsuario() throws SQLException {
        List<Servico> servicos = servicoDAO.obterTodosPorUsuario(1, 14, null);

        System.out.println(servicos.size());
        assert servicos.size() > 0;
    }

    @Test
    void obterTodosPorTitulo()
            throws SQLException {

        List<Servico> servicos = servicoDAO.obterTodosPorTitulo(".", 15, 0);

//        servicos.forEach(System.out::println);
        System.out.println(servicos.size());
        assert servicos.size() > 0;
    }

    @Test
    void obterTodosPorDescricao() {
    }

    @Test
    void obterTodosPorValor() {
    }

    @Test
    void obterTodosPorTipo() {
    }

    @Test
    void obterTodosPorSubtipo() {
    }

    @Test
    void adicionar() throws SQLException {

        String descricao = String.valueOf(LocalDateTime.now());
        List<ESubtipoServico> subtipos = new ArrayList<>();
        subtipos.add(ESubtipoServico.DESENVOLVIMENTO_WEB);
        subtipos.add(ESubtipoServico.DESENVOLVIMENTO_DESKTOP);

        Servico servico = new Servico(descricao, descricao, subtipos, 120.00, 1, 1, 1);

        servicoDAO.adicionar(servico);
        System.out.println(servico);
        Servico servico2 = servicoDAO.obterPorId(servico.getId());

        assert servico2.getDescricao().equals(descricao);
    }

    @Test
    void obterPorId() throws SQLException {
        Servico servico = servicoDAO.obterPorId(1);
        System.out.println(servico);
        assert servico.getId() == 1;
    }

    @Test
    void atualizar() throws SQLException {

        List<Servico> servicos = servicoDAO.obterTodos(10000, null);
        Servico servico = servicoDAO.obterPorId(rand.nextInt(servicos.size() - 1));

        String descricao = String.valueOf(LocalDateTime.now());
        servico.setDescricao(descricao);
        servicoDAO.atualizar(servico);
        assert servicoDAO.obterPorId(servico.getId()).getDescricao().equals(descricao);
    }

    @Test
    void excluirPorId() throws SQLException {

        List<Servico> infos = servicoDAO.obterTodos(10000, null);
        Servico servico = servicoDAO.obterPorId(rand.nextInt(infos.size() - 1));
        System.out.println(servico);

        servicoDAO.excluirPorId(servico.getId());

        Servico servicoDel = servicoDAO.obterPorId(servico.getId());
        System.out.println(servicoDel);
        assert servicoDel == null;
    }
}