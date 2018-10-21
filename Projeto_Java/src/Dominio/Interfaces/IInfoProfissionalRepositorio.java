package Dominio.Interfaces;

import Dominio.Entidades.InfoProfissional;
import Dominio.Enum.ETipoInfoProfissional;

import java.util.Date;
import java.util.List;

public interface IInfoProfissionalRepositorio extends IRepositorio<InfoProfissional> {

    List<InfoProfissional> ObterTodosPorTipo(ETipoInfoProfissional tipo);
    List<InfoProfissional> ObterTodosPorNome(String nome);
    List<InfoProfissional> ObterTodosPorUsuario(int usuarioId);
    List<InfoProfissional> ObterTodosPorTipoEUsuario(InfoProfissional tipo, int usuarioId);
    List<InfoProfissional> ObterTodosPorData(Date inicio, Date fim);
}
