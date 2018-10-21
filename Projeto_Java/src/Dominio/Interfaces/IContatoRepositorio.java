package Dominio.Interfaces;

import Dominio.Entidades.Contato;
import Dominio.Enum.ETipoContato;

import java.util.List;

public interface IContatoRepositorio extends IRepositorio<Contato> {

    List<Contato> ObterTodosPorTipo(ETipoContato tipo);
    List<Contato> ObterTodosPorUsuario(int usuarioId);
    List<Contato> ObterTodosPorTipoEUsuario(ETipoContato tipo, int usuarioId);
}
