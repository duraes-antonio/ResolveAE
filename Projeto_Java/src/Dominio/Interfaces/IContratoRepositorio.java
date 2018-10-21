package Dominio.Interfaces;

import Dominio.Entidades.Contrato;
import Dominio.Entidades.Servico;

import java.util.Date;
import java.util.List;

public interface IContratoRepositorio extends IRepositorioBase<Contrato> {

    List<Contrato> obterTodosPorUsuario(int usuarioId);
    Contrato obterPorServico(Servico servico);
    List<Contrato> obterTodosPorDescricao(String descricao);
    List<Contrato> obterTodosPorData(Date inicio, Date fim);
    List<Contrato> obterTodosPorValor(double valorMin, double valorMax);
}
