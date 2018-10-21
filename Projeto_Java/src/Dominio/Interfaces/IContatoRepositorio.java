package Dominio.Interfaces;

import Dominio.Entidades.Contato;
import Dominio.Enum.ETipoContato;

import java.util.List;

public interface IContatoRepositorio extends IRepositorioBase<Contato> {

    List<Contato> obterTodosPorTipo(ETipoContato tipo);
    List<Contato> obterTodosPorUsuario(int usuarioId);
    List<Contato> obterTodosPorTipoEUsuario(ETipoContato tipo, int usuarioId);
}
