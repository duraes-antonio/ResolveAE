package Infraestrutura.Hibernate.DAO;

import Dominio.Entidades.Endereco;
import Dominio.Enum.EEstado;
import Dominio.Interfaces.IEnderecoRepositorio;
import Infraestrutura.Hibernate.Util.FabricaConexao;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class EnderecoDAOHibernate extends AGenericDAOHibernate<Endereco>
        implements IEnderecoRepositorio {

    private EntityManager em = FabricaConexao.obterConexao();

    public EnderecoDAOHibernate() {
        super(Endereco.class);
    }

    @Override
    public List<Endereco> obterTodosPorBairro(String bairro, Integer limit, Integer offset) throws SQLException {
        return null;
    }

    @Override
    public List<Endereco> obterTodosPorCidade(String cidade, Integer limit, Integer offset) throws SQLException {
        return null;
    }

    @Override
    public List<Endereco> obterTodosPorEstado(EEstado estado, Integer limit, Integer offset) throws SQLException {
        return null;
    }

    @Override
    public List<Endereco> obterTodosPorCep(int cep, Integer limit, Integer offset) throws SQLException {
        return null;
    }

    @Override
    public Endereco obterTodosPorUsuario(int fkUsuario) throws SQLException {
        return (Endereco) em.createQuery(String.format("FROM Endereco WHERE fkUsuario = %d", fkUsuario)).getSingleResult();
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
    public List<Endereco> obterTodos(Integer limit, Integer offset) throws SQLException {
        return null;
    }
}
