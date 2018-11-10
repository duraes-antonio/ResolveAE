package Dominio.Interfaces;

import Dominio.Entidades.Comentario;

import java.sql.SQLException;
import java.util.List;

public interface IComentarioRepositorio extends IBaseRepositorio<Comentario> {

    Comentario obterPorAvaliacao(int avaliacaoId) throws SQLException;
    List<Comentario> obterTodosPorUsuario(int usuarioId, Integer limit, Integer offset) throws SQLException;
    List<Comentario> obterTodosPorServico(int servicoId, Integer limit, Integer offset) throws SQLException;
}
