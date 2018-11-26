package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.InfoProfissional;
import Dominio.Enum.ETipoInfoProfissional;
import Infraestrutura.Postgre.Util.Persistencia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

class InfoProfissionalDAOTest {

    private InfoProfissionalDAO infoProDAO = new InfoProfissionalDAO();
    private Persistencia persistencia = Persistencia.get();
    private Connection conexao = persistencia.getConexao();
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
    void obterTodos() throws SQLException {
        List<InfoProfissional> infos = infoProDAO.obterTodos(null, null);
        System.out.println(infos.size());
        assert infos.size() > 0;
    }

    @Test
    void obterTodosPorTipo() throws SQLException {

        List<InfoProfissional> infos = null;
        infos = infoProDAO.obterTodosPorTipo(ETipoInfoProfissional.TRABALHO,0, 0);
        System.out.println(infos.size());
        assert infos.size() > 0;
    }

    @Test
    void obterTodosPorUsuario() throws SQLException {
        List<InfoProfissional> infos = null;
        infos = infoProDAO.obterTodosPorUsuario(1,0, 0);
        System.out.println(infos.size());
        assert infos.size() > 0;
    }

    @Test
    void obterTodosPorTipoEUsuario() throws SQLException {
        List<InfoProfissional> infos = null;
        infos = infoProDAO.obterTodosPorTipoEUsuario(ETipoInfoProfissional.GRADUACAO,1,0, 0);
        System.out.println(infos.size());
        assert infos.size() > 0;
    }

    @Test
    void obterTodosPorData() throws SQLException {

        LocalDate date1 = LocalDate.of(2000, 1, 1);
        LocalDate date2 = LocalDate.of(2010, 1, 1);

        List<InfoProfissional> infos = infoProDAO.obterTodosPorData(date1, date2,3, 0);
        System.out.println(infos.size());
        assert infos.size() > 0;
    }

    @Test
    void adicionar() throws SQLException {

        conexao.setAutoCommit(false);

        String descricao = "Experiência teste";
        LocalDate data1 = LocalDate.of(2017, 6, 1);
        LocalDate data2 = LocalDate.of(2018, 6, 1);
        InfoProfissional info1 = new InfoProfissional(descricao, data1, data2, 1, 1);
        System.out.println(info1);

        infoProDAO.adicionar(info1);

        InfoProfissional info2 = infoProDAO.obterPorId(info1.getId());
        System.out.println(info2);

        assert info1.toString().equals(info2.toString());

        conexao.rollback();
    }

    @Test
    void obterPorId() throws SQLException {
        InfoProfissional info = infoProDAO.obterPorId(id);
        System.out.println(info);
        assert id == info.getId();
    }

    @Test
    void atualizar() throws SQLException {

        conexao.setAutoCommit(false);

        InfoProfissional info1 = infoProDAO.obterPorId(id);
        info1.setDescricao(String.valueOf(LocalDateTime.now()));
        System.out.println(info1);

        infoProDAO.atualizar(info1);

        InfoProfissional info2 = infoProDAO.obterPorId(info1.getId());

        assert info1.toString().equals(info2.toString());

        conexao.rollback();
    }

    @Test
    void excluirPorId() throws SQLException {

        conexao.setAutoCommit(false);

        InfoProfissional info1 = infoProDAO.obterPorId(id);
        System.out.println(info1);

        infoProDAO.excluirPorId(info1.getId());

        InfoProfissional info2 = infoProDAO.obterPorId(id);
        System.out.println(info2);

        assert info2 == null;

        conexao.rollback();
    }
}