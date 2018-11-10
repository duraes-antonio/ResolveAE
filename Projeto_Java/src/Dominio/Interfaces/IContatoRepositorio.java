package Dominio.Interfaces;

import Dominio.Entidades.Contato;
import Dominio.Enum.ETipoContato;

import java.sql.SQLException;
import java.util.List;

public interface IContatoRepositorio extends IBaseRepositorio<Contato> {

    List<Contato> obterTodosPorTipo(ETipoContato tipo, Integer limit, Integer offset) throws SQLException;
    List<Contato> obterTodosPorUsuario(int usuarioId, Integer limit, Integer offset) throws SQLException;
    List<Contato> obterTodosPorTipoEUsuario(ETipoContato tipo, int usuarioId, Integer limit, Integer offset) throws SQLException;
}
