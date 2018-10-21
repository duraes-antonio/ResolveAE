package Dominio.Interfaces;

import Dominio.Entidades.Servico;
import Dominio.Enum.ESubtipoServico;
import Dominio.Enum.ETipoServico;

import java.util.List;

public interface IServicoRepositorio extends IRepositorioBase<Servico> {

    List<Servico> obterTodosPorUsuario(int usuarioId);
    List<Servico> obterTodosPorTitulo(String titulo);
    List<Servico> obterTodosPorDescricao(String descricao);
    List<Servico> obterTodosPorValorAte(double valorMax);
    List<Servico> obterTodosPorValor(double valorMin, double valorMax);
    List<Servico> obterTodosPorTipo(ETipoServico tipo);
    List<Servico> obterTodosPorSubtipo(ESubtipoServico subtipo);
}
