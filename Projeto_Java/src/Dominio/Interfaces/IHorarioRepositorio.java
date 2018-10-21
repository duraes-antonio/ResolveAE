package Dominio.Interfaces;

import Dominio.Entidades.Horario;
import Dominio.Entidades.Usuario;
import Dominio.Enum.EDiaSemana;
import Dominio.Enum.EEstado;
import Dominio.Enum.ETipoContato;
import Dominio.Enum.ETipoInfoProfissional;

import java.time.LocalTime;
import java.util.List;

public interface IHorarioRepositorio extends IRepositorio<Horario> {

    List<Horario> ObterTodosPorDia(EDiaSemana dia, int usuarioId);
    List<Horario> ObterTodosLivres();
    List<Horario> ObterTodosOcupados();
    List<Horario> ObterTodosPorCep(int cep);
    List<Horario> ObterTodosNoIntervalo(LocalTime inicio, LocalTime fim);
}
