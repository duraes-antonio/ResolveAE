package Infraestrutura.Hibernate.DAO;

import Dominio.Entidades.Avaliacao;
import Dominio.Interfaces.IAvaliacaoRepositorio;
import Infraestrutura.Hibernate.Util.FabricaConexao;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class AvaliacaoDAOH extends AGenericDAOH<Avaliacao> implements IAvaliacaoRepositorio {

    EntityManager entityManager = FabricaConexao.obterConexao();

    public AvaliacaoDAOH() {
        super(Avaliacao.class);
    }

    @Override
    public List<Avaliacao> obterTodasPorUsuario(int usuarioId, Integer limit, Integer offset) throws SQLException {
        return entityManager.createQuery(
                String.format("from Avaliacao a where a.fkUsuario = %d", usuarioId)
        ).getResultList();
    }

    @Override
    public List<Avaliacao> obterTodasPorServico(int servicoId, Integer limit, Integer offset) throws SQLException {
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
    public List<Avaliacao> obterTodos(Integer limit, Integer offset) throws SQLException {
        return null;
    }
}