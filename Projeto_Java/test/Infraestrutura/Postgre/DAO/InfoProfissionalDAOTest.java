package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.InfoProfissional;
import Dominio.Enum.ETipoInfoProfissional;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

class InfoProfissionalDAOTest {

    InfoProfissionalDAO infoProDAO = new InfoProfissionalDAO();
    Random rand = new Random();
    int id = 1;

    @Test
    void obterTodos() throws SQLException {

        List<InfoProfissional> infos = infoProDAO.obterTodos(3, 0);

        for (InfoProfissional info: infos) {
            System.out.println(info);
        }

        assert infos.size() > 0;
    }

    @Test
    void obterTodosPorTipo() throws SQLException {

        List<InfoProfissional> infos = null;
        infos = infoProDAO.obterTodosPorTipo(ETipoInfoProfissional.TRABALHO,3, 0);

        for (InfoProfissional info: infos) {
            System.out.println(info);
        }

        assert infos.size() > 0;
    }

    @Test
    void obterTodosPorUsuario() throws SQLException {

        List<InfoProfissional> infos = null;
        infos = infoProDAO.obterTodosPorUsuario(1,3, 0);

        for (InfoProfissional info: infos) {
            System.out.println(info);
        }

        assert infos.size() > 0;
    }

    @Test
    void obterTodosPorTipoEUsuario() throws SQLException {

        List<InfoProfissional> infos = null;
        infos = infoProDAO.obterTodosPorTipoEUsuario(ETipoInfoProfissional.GRADUACAO,1,3, 0);

        for (InfoProfissional info: infos) {
            System.out.println(info);
        }

        assert infos.size() > 0;
    }

    @Test
    void obterTodosPorData() throws SQLException {

        List<InfoProfissional> infos = null;
        LocalDate date1 = LocalDate.of(2000, 1, 1);
        LocalDate date2 = LocalDate.of(2010, 1, 1);

        infos = infoProDAO.obterTodosPorData(date1, date2,3, 0);

        for (InfoProfissional info: infos) {
            System.out.println(info);
        }

        assert infos.size() > 0;
    }

    @Test
    void adicionar() throws SQLException {
        String descricao = "Experiência teste";
        LocalDate data1 = LocalDate.of(2017, 6, 1);
        LocalDate data2 = LocalDate.of(2018, 6, 1);
        InfoProfissional info = new InfoProfissional(descricao, data1, data2, 1, 1);
        infoProDAO.adicionar(info);
        InfoProfissional info2 = infoProDAO.obterPorId(info.getId());

        assert info2.getDescricao().equals(descricao);
    }

    @Test
    void obterPorId() throws SQLException {

        List<InfoProfissional> infos = infoProDAO.obterTodos(10000, null);
        int id = rand.nextInt(infos.size() - 1);
        InfoProfissional info = infoProDAO.obterPorId(id);

        System.out.println(info);
        assert id == info.getId();
    }

    @Test
    void atualizar() throws SQLException {

        List<InfoProfissional> infos = infoProDAO.obterTodos(10000, null);
        InfoProfissional info = infoProDAO.obterPorId(rand.nextInt(infos.size() - 1));

        String descricao = String.valueOf(LocalDateTime.now());
        info.setDescricao(descricao);
        infoProDAO.atualizar(info);
        assert infoProDAO.obterPorId(info.getId()).getDescricao().equals(descricao);
    }

    @Test
    void excluirPorId() throws SQLException {

        List<InfoProfissional> infos = infoProDAO.obterTodos(10000, null);
        InfoProfissional info = infoProDAO.obterPorId(rand.nextInt(infos.size() - 1));
        System.out.println(info);

        infoProDAO.excluirPorId(info.getId());
        InfoProfissional infoDel = infoProDAO.obterPorId(info.getId());
        System.out.println(infoDel);
        assert infoDel == null;
    }
}