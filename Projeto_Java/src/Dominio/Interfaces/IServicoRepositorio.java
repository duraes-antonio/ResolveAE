package Dominio.Interfaces;

import Dominio.Entidades.Servico;
import Dominio.Enum.ESubtipoServico;
import Dominio.Enum.ETipoServico;

import java.util.List;

public interface IServicoRepositorio extends IBaseRepositorio<Servico> {

    List<Servico> obterTodosPorUsuario(int usuarioId, Integer limit, Integer offset);
    List<Servico> obterTodosPorTitulo(String titulo, Integer limit, Integer offset);
    List<Servico> obterTodosPorDescricao(String descricao, Integer limit, Integer offset);
    List<Servico> obterTodosPorValor(double valorMin, double valorMax, Integer limit, Integer offset);
    List<Servico> obterTodosPorTipo(ETipoServico tipo, Integer limit, Integer offset);
    List<Servico> obterTodosPorSubtipo(ESubtipoServico subtipo, Integer limit, Integer offset);
}
