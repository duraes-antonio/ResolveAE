package Dominio.Interfaces;

import Dominio.Entidades.ServicoSubtipoServico;

import java.sql.SQLException;
import java.util.List;

public interface IServicoSubtipoServicoRepositorio extends IBaseRepositorio<ServicoSubtipoServico> {
    List<ServicoSubtipoServico> obterPorServico(int servicoId, Integer limit, Integer offset) throws SQLException;
}
