package Dominio.Interfaces;

import Dominio.Entidades.InfoProfissional;
import Dominio.Enum.ETipoInfoProfissional;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface IInfoProfissionalRepositorio extends IBaseRepositorio<InfoProfissional> {

    List<InfoProfissional> obterTodosPorTipo(ETipoInfoProfissional tipo, Integer limit, Integer offset) throws SQLException;
    List<InfoProfissional> obterTodosPorUsuario(int usuarioId, Integer limit, Integer offset) throws SQLException;
    List<InfoProfissional> obterTodosPorTipoEUsuario(ETipoInfoProfissional tipo, int usuarioId, Integer limit, Integer offset) throws SQLException;
    List<InfoProfissional> obterTodosPorData(LocalDate inicio, LocalDate fim, Integer limit, Integer offset) throws SQLException;
}
