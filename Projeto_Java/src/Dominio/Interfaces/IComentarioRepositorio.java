package Dominio.Interfaces;

import Dominio.Entidades.Comentario;

import java.sql.SQLException;
import java.util.List;

public interface IComentarioRepositorio extends IRepositorioBase<Comentario> {

    Comentario obterPorAvaliacao(int avaliacaoId) throws SQLException;
    List<Comentario> obterTodosPorUsuario(int usuarioId) throws SQLException;
    List<Comentario> obterTodosPorServico(int servicoId) throws SQLException;
}
