package Infraestrutura.Hibernate.DAO;

import Dominio.Entidades.Servico;
import Dominio.Enum.ESubtipoServico;
import Dominio.Enum.ETipoServico;
import Dominio.Interfaces.IServicoRepositorio;
import Infraestrutura.Hibernate.Util.FabricaConexao;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class ServicoDAOH implements IServicoRepositorio {

    private EntityManager em = FabricaConexao.obterConexao();

    @Override
    public List<Servico> obterTodosPorUsuario(int usuarioId, Integer limit, Integer offset) throws SQLException {
        return em.createQuery(
                String.format("FROM Servico WHERE fkUsuario = %d", usuarioId)
        ).getResultList();
    }

    @Override
    public List<Servico> obterTodosPorTitulo(String titulo, Integer limit, Integer offset) throws SQLException {
        return null;
    }

    @Override
    public List<Servico> obterTodosPorDescricao(String descricao, Integer limit, Integer offset) throws SQLException {
        return null;
    }

    @Override
    public List<Servico> obterTodosPorValor(double valorMin, double valorMax, Integer limit, Integer offset) throws SQLException {
        return null;
    }

    @Override
    public List<Servico> obterTodosPorTipo(ETipoServico tipo, Integer limit, Integer offset) throws SQLException {
        return null;
    }

    @Override
    public List<Servico> obterTodosPorSubtipo(ESubtipoServico subtipo, Integer limit, Integer offset) throws SQLException {
        return null;
    }

    @Override
    public Servico obterPorContrato(Integer fkContrato) throws SQLException {
        return null;
    }

    /**
     * Persiste o objeto em um meio não volátil de armazenamento.
     *
     * @param entidade Objeto a ser persistido.
     * @return Id gerado após a inserção do objeto.
     * @throws SQLException
     */
    @Override
    public Servico adicionar(Servico entidade) throws SQLException {
        return null;
    }

    /**
     * Atualiza os dados de um objeto já existente no meio de persistência.
     *
     * @param entidade Objeto a ser atualizado.
     * @throws SQLException
     */
    @Override
    public void atualizar(Servico entidade) throws SQLException {

    }

    /**
     * Remove um objeto do meio de persistência dado seu identificador.
     *
     * @param id Identificador do objeto a ser removido.
     * @throws SQLException
     */
    @Override
    public void excluirPorId(int id) throws SQLException {

    }

    /**
     * Busca e retorna o objeto que possuir o identificador recebido.
     *
     * @param id Identificador do objeto a ser buscado.
     * @return Objeto já construído e com atributos preenchidos, inclusive ID.
     * @throws SQLException
     */
    @Override
    public Servico obterPorId(int id) throws SQLException {
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
    public List<Servico> obterTodos(Integer limit, Integer offset) throws SQLException {
        return null;
    }
}
