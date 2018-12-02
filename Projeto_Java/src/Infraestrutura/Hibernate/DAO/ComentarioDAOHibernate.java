package Infraestrutura.Hibernate.DAO;

import Dominio.Entidades.Comentario;
import Dominio.Interfaces.IComentarioRepositorio;
import Infraestrutura.Hibernate.Util.FabricaConexao;

import javax.persistence.Query;
import java.util.List;

public class ComentarioDAOHibernate extends AGenericDAOHibernate<Comentario> implements IComentarioRepositorio {

    private FabricaConexao fabricaConexao = FabricaConexao.get();


    public ComentarioDAOHibernate() {
        super(Comentario.class);
    }


    @Override
    public Comentario obterPorAvaliacao(int avaliacaoId) {
        Query query = fabricaConexao.obterConexao().createQuery("from Comentario where fkAvaliacao = :avaliacaoId");
        query.setParameter("avaliacaoId", avaliacaoId);
        List<Comentario> listaResult = query.getResultList();
        Comentario comentario = listaResult.size() > 0 ? listaResult.get(0) : null;
        return comentario;
    }

    @Override
    public List<Comentario> obterTodosPorUsuario(int usuarioId, Integer limit, Integer offset) {
        return null;
    }

    @Override
    public List<Comentario> obterTodosPorServico(int servicoId, Integer limit, Integer offset) {
        Query query = fabricaConexao.obterConexao().createQuery("from Comentario where fkServico = :servicoId");
        query.setParameter("servicoId", servicoId);
        return query.getResultList();
    }

    /**
     * Busca e retorna todos objetos de um determinado tipo.
     *
     * @param limit  Quantidade de resultados a ser retornada.
     * @param offset Quantidade de resultados a pular.
     * @return Lista com todos objetos encontrados.
     */
    @Override
    public List<Comentario> obterTodos(Integer limit, Integer offset) {
        Query query = fabricaConexao.obterConexao().createQuery("from Comentario");
        return query.getResultList();
    }
}
