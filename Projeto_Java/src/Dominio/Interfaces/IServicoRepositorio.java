package Dominio.Interfaces;

import Dominio.Entidades.Servico;
import Dominio.Enum.ESubtipoServico;
import Dominio.Enum.ETipoServico;

import java.sql.SQLException;
import java.util.List;

public interface IServicoRepositorio extends IBaseRepositorio<Servico> {

    List<Servico> obterTodosPorUsuario(int usuarioId, Integer limit, Integer offset) throws SQLException;
    List<Servico> obterTodosPorTitulo(String titulo, Integer limit, Integer offset) throws SQLException;
    List<Servico> obterTodosPorDescricao(String descricao, Integer limit, Integer offset) throws SQLException;
    List<Servico> obterTodosPorValor(double valorMin, double valorMax, Integer limit, Integer offset) throws SQLException;
    List<Servico> obterTodosPorTipo(ETipoServico tipo, Integer limit, Integer offset) throws SQLException;
    List<Servico> obterTodosPorSubtipo(ESubtipoServico subtipo, Integer limit, Integer offset) throws SQLException;
    Servico obterPorContrato(Integer fkContrato) throws SQLException;

}
