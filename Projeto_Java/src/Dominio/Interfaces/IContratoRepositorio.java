package Dominio.Interfaces;

import Dominio.Entidades.Contrato;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface IContratoRepositorio extends IBaseRepositorio<Contrato> {

    List<Contrato> obterTodosPorUsuario(int usuarioId, Integer limit, Integer offset) throws SQLException;
//    Contrato obterPorServico(int servicoId, Integer limit, Integer offset) throws SQLException;
    List<Contrato> obterTodosPorDescricao(String descricao, Integer limit, Integer offset) throws SQLException;
    List<Contrato> obterTodosPorData(LocalDate inicio, LocalDate fim, Integer limit, Integer offset) throws SQLException;
}
