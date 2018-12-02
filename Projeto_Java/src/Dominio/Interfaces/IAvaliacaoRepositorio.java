package Dominio.Interfaces;

import Dominio.Entidades.Avaliacao;

import java.sql.SQLException;
import java.util.List;

public interface IAvaliacaoRepositorio extends IBaseRepositorio<Avaliacao> {

    List obterTodasPorUsuario(int usuarioId, Integer limit, Integer offset) throws SQLException;
    List<Avaliacao> obterTodasPorServico(int servicoId, Integer limit, Integer offset) throws SQLException;
}
