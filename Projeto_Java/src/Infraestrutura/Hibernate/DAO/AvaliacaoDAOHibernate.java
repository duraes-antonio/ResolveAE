package Infraestrutura.Hibernate.DAO;

import Dominio.Entidades.Avaliacao;
import Dominio.Interfaces.IAvaliacaoRepositorio;
import Infraestrutura.Hibernate.Util.FabricaConexao;
import Infraestrutura.Postgre.DAO.ComentarioDAO;

import javax.persistence.Query;
import java.util.List;

public class AvaliacaoDAOHibernate extends AGenericDAOHibernate<Avaliacao>
        implements IAvaliacaoRepositorio {

    private FabricaConexao fabricaConexao = FabricaConexao.get();
    private ComentarioDAO comentarioDAO = new ComentarioDAO();

    public AvaliacaoDAOHibernate() {
        super(Avaliacao.class);
    }

    @Override
    public List<Avaliacao> obterTodasPorUsuario(int usuarioId, Integer limit, Integer offset) {
        Query query = fabricaConexao.obterConexao().createQuery(
                "from Avaliacao where fkUsuario = :usuarioId");
        query.setParameter("usuarioId", usuarioId);
        return query.getResultList();
    }

    @Override
    public List<Avaliacao> obterTodasPorServico(int servicoId, Integer limit, Integer offset) {
        Query query = fabricaConexao.obterConexao().createQuery(
                "from Avaliacao where fkServico = :servicoId");
        query.setParameter("servicoId", servicoId);
        return query.getResultList();
    }
}