package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.Horario;
import Dominio.Enum.EDiaSemana;
import Infraestrutura.Postgre.Util.Persistencia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

class HorarioDAOTest {

    private Persistencia persistencia = Persistencia.get();
    private Connection conexao;
    private HorarioDAO horarioDAO = new HorarioDAO();
    private int id = 1;

    @BeforeEach
    void setUp() {
        conexao = persistencia.getConexao();
    }

    @AfterEach
    void tearDown() throws SQLException {
        conexao.close();
    }

    @Test
    void obterTodos() throws SQLException {
        List<Horario> horarios = horarioDAO.obterTodos(10000, 0);
        System.out.println(horarios.size());
        System.out.println(horarios.get(horarios.size() - 1));
        assert horarios.size() > 0;
    }

    @Test
    void obterTodosPorDia() throws SQLException {
        List<Horario> horarios = horarioDAO.obterTodosPorDia(
                EDiaSemana.DOMINGO, 13,null, null);
        System.out.println(horarios.size());
        System.out.println(horarios.get(horarios.size() - 1));
        assert horarios.size() > 0;
    }

    @Test
    void obterTodosLivres() throws SQLException {
        List<Horario> horarios = horarioDAO.obterTodosLivres(null, null);
        System.out.println(horarios.size());
        System.out.println(horarios.get(horarios.size() - 1));
        assert horarios.size() > 0;
    }

    @Test
    void obterTodosOcupados() throws SQLException {
        List<Horario> horarios = horarioDAO.obterTodosOcupados(null, null);
        System.out.println(horarios.size());
        assert horarios.size() == 0;
    }

    @Test
    void obterTodosPorUsuario() throws SQLException {
        List<Horario> horarios = horarioDAO.obterTodosPorUsuario(1, null, null);
        System.out.println(horarios.size());
        System.out.println(horarios.get(0));
        assert horarios.size() > 0;
    }

    @Test
    void obterTodosNoIntervalo() throws SQLException {
        List<Horario> horarios = horarioDAO.obterTodosNoIntervalo(
                LocalTime.of(7, 30, 0), LocalTime.of(9, 30, 0), null, null);
        System.out.println(horarios.size());
        System.out.println(horarios.get(horarios.size() - 1));
        assert horarios.size() > 0;
    }

    @Test
    void adicionar() throws SQLException {

        try {
            conexao.setAutoCommit(false);

            Horario horario1 = new Horario(
                    LocalTime.of(7, 45, 30),
                    LocalTime.of(10, 45, 30),
                    true,
                    1,
                    1
            );

            horarioDAO.adicionar(horario1);

            System.out.println(horario1);

            Horario horario2 = horarioDAO.obterPorId(horario1.getId());
            assert horario1.toString().equals(horario2.toString());

            System.out.println(horario2);
        }

        finally {
            conexao.rollback();
        }
    }

    @Test
    void atualizar() throws SQLException {

        try {
            conexao.setAutoCommit(false);

            Horario horario1 = horarioDAO.obterPorId(id);
            System.out.println(horario1);

            horario1.setLivre(false);
            horario1.setHorarioInicio(LocalTime.of(6, 20, 0));

            horarioDAO.atualizar(horario1);

            Horario horario2 = horarioDAO.obterPorId(horario1.getId());
            assert horario1.toString().equals(horario2.toString());

            System.out.println(horario2);
        }

        finally {
            conexao.rollback();
        }
    }

    @Test
    void excluirPorId() throws SQLException {

        try {
            conexao.setAutoCommit(false);

            Horario horario1 = horarioDAO.obterPorId(id);
            System.out.println(horario1);

            horarioDAO.excluirPorId(horario1.getId());

            Horario horario2 = horarioDAO.obterPorId(horario1.getId());
            System.out.println(horario2);

            assert horario2 == null;
        }

        finally {
            conexao.rollback();
        }
    }

    @Test
    void obterPorId() throws SQLException {

        Horario horario1 = horarioDAO.obterPorId(id);
        System.out.println(horario1);

        assert id == horario1.getId();
    }


}