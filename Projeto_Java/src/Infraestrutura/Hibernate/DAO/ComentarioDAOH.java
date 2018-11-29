package Infraestrutura.Hibernate.DAO;

import Dominio.Entidades.Comentario;
import Dominio.Interfaces.IComentarioRepositorio;

import java.sql.SQLException;
import java.util.List;

public class ComentarioDAOH extends AGenericDAOH<Comentario>
        implements IComentarioRepositorio{


    public ComentarioDAOH() {
        super(Comentario.class);
    }

    @Override
    public Comentario obterPorAvaliacao(int avaliacaoId) throws SQLException {
        return null;
    }

    @Override
    public List<Comentario> obterTodosPorUsuario(int usuarioId, Integer limit, Integer offset) throws SQLException {
        return null;
    }

    @Override
    public List<Comentario> obterTodosPorServico(int servicoId, Integer limit, Integer offset) throws SQLException {
        return null;
    }

    /**
     * Busca e retorna todos objetos de um determinado tipo.
     *
     * @param limit  Quantidade de resultados a ser retornada.
     * @param offset Quantidade de resultados a pular.
     * @return Lista com todos objetos encontrados.
     * @throws SQLException
     */
    @Override
    public List<Comentario> obterTodos(Integer limit, Integer offset) throws SQLException {
        return null;
    }
}
