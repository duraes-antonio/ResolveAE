package Dominio.Interfaces;

import Dominio.Entidades.InfoProfissional;
import Dominio.Enum.ETipoInfoProfissional;

import java.util.Date;
import java.util.List;

public interface IInfoProfissionalRepositorio extends IRepositorioBase<InfoProfissional> {

    List<InfoProfissional> obterTodosPorTipo(ETipoInfoProfissional tipo);
    List<InfoProfissional> obterTodosPorNome(String nome);
    List<InfoProfissional> obterTodosPorUsuario(int usuarioId);
    List<InfoProfissional> obterTodosPorTipoEUsuario(InfoProfissional tipo, int usuarioId);
    List<InfoProfissional> obterTodosPorData(Date inicio, Date fim);
}
