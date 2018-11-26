package Dominio.Interfaces;

import Dominio.Entidades.Horario;
import Dominio.Enum.EDiaSemana;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

public interface IHorarioRepositorio extends IBaseRepositorio<Horario> {

    List<Horario> obterTodosPorDia(EDiaSemana dia, int usuarioId, Integer limit, Integer offset) throws SQLException;
    List<Horario> obterTodosLivres(Integer limit, Integer offset) throws SQLException;
    List<Horario> obterTodosOcupados(Integer limit, Integer offset) throws SQLException;
    List<Horario> obterTodosPorUsuario(int usuarioId, Integer limit, Integer offset) throws SQLException;
    List<Horario> obterTodosNoIntervalo(LocalTime inicio, LocalTime fim, Integer limit, Integer offset) throws SQLException;
}
