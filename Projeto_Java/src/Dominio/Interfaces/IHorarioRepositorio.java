package Dominio.Interfaces;

import Dominio.Entidades.Horario;
import Dominio.Enum.EDiaSemana;

import java.time.LocalTime;
import java.util.List;

public interface IHorarioRepositorio extends IRepositorioBase<Horario> {

    List<Horario> obterTodosPorDia(EDiaSemana dia, int usuarioId);
    List<Horario> obterTodosLivres();
    List<Horario> obterTodosOcupados();
    List<Horario> obterTodosPorCep(int cep);
    List<Horario> obterTodosNoIntervalo(LocalTime inicio, LocalTime fim);
}
