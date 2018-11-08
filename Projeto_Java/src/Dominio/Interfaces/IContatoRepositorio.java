package Dominio.Interfaces;

import Dominio.Entidades.Contato;
import Dominio.Enum.ETipoContato;

import java.sql.SQLException;
import java.util.List;

public interface IContatoRepositorio extends IRepositorioBase<Contato> {

    List<Contato> obterTodosPorTipo(ETipoContato tipo) throws SQLException;
    List<Contato> obterTodosPorUsuario(int usuarioId) throws SQLException;
    List<Contato> obterTodosPorTipoEUsuario(ETipoContato tipo, int usuarioId);
}
