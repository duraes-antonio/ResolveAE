package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.ServicoSubtipoServico;
import Dominio.Interfaces.IServicoSubtipoServicoRepositorio;
import Infraestrutura.Enum.ETab;
import Infraestrutura.Postgre.Util.Persistencia;
import Infraestrutura.Postgre.Util.SQLProdutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ServicoSubtipoServicoDAO extends AGenericDAO<ServicoSubtipoServico>
        implements IServicoSubtipoServicoRepositorio {

    public static final String ID = ETab.SERVICO_SUBTIPO_SERVICO.get() + ".id";
    public static final String FK_SERVICO = ETab.SERVICO_SUBTIPO_SERVICO.get() + ".fk_servico";
    public static final String FK_SUBTIPO_SERVICO = ETab.SERVICO_SUBTIPO_SERVICO.get() + ".fk_subtipo_servico";

    public static final List<String> COLUNAS = Arrays.asList(FK_SERVICO, FK_SUBTIPO_SERVICO);

    private Persistencia persistencia = Persistencia.get();
    private Connection conexao = persistencia.getConexao();
    private PreparedStatement psTodos;
    private PreparedStatement psTodosPorServico;
    private PreparedStatement psTodosIntervaloServico;


    private List<ServicoSubtipoServico> obterVarios(PreparedStatement ps)
            throws SQLException {

        List<ServicoSubtipoServico> sss = null;

        try {
            sss = extrairTodos(persistencia.executarSelecao(ps));
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            if (ps != null) ps.close();
        }

        return sss;
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
    public List<ServicoSubtipoServico> obterTodos(Integer limit, Integer offset) throws SQLException {
        String sql = GenericSQL.obterTodos(ETab.SERVICO_SUBTIPO_SERVICO, COLUNAS, ID, limit, offset);
        psTodos = conexao.prepareStatement(sql);
        return obterVarios(psTodos);
    }

    @Override
    public List<ServicoSubtipoServico> obterPorServico(int servicoId, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = new SQLProdutor();
        sqlProd.select(ID, FK_SERVICO, FK_SUBTIPO_SERVICO);
        sqlProd.from(ETab.SERVICO_SUBTIPO_SERVICO.get()).where(FK_SERVICO).eq();

        psTodosPorServico = conexao.prepareStatement(sqlProd.toString());
        psTodosPorServico.setInt(1, servicoId);
        return obterVarios(psTodosPorServico);
    }

    public List<ServicoSubtipoServico> obterPorIntervaloServico(int servicoIdMin, int servicoIdMax, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = new SQLProdutor();
        sqlProd.select(ID, FK_SERVICO, FK_SUBTIPO_SERVICO);
        sqlProd.from(ETab.SERVICO_SUBTIPO_SERVICO.get());
        sqlProd.where(FK_SERVICO).grteq().and(FK_SERVICO).leq();

        psTodosIntervaloServico = conexao.prepareStatement(sqlProd.toString());
        psTodosIntervaloServico.setInt(1, servicoIdMin);
        psTodosIntervaloServico.setInt(2, servicoIdMax);
        return obterVarios(psTodosIntervaloServico);
    }

    /**
     * Substitui os '?' do PS pelos valores dos atributos da objeto.
     * Substitui os valores para operações de INSERT e UPDATE apenas.
     *
     * @param ps     P. Statement com SQL já pronto com os '?' para serem substituídos.
     * @param objeto Objeto com os atributos para preencher o statement.
     * @return ps com os '?' substituídos, pronto para execução.
     */
    @Override
    protected PreparedStatement preencherPS(PreparedStatement ps, ServicoSubtipoServico objeto)
            throws SQLException {

        ps.setInt(1, objeto.getFkServico());
        ps.setInt(2, objeto.getFkSubtipoServico());

        if (objeto.getId() > 0) {
            ps.setInt(COLUNAS.size() + 1, objeto.getId());
        }

        return ps;
    }

    /**
     * Monta e retorna o objeto a partir de um resultSet.
     * Não faz validações sobre o resultSet.
     *
     * @param rs ResultSet retornado de uma consulta já executada.
     * @return Objeto montado a partir dos resultados da consulta.
     */
    @Override
    protected ServicoSubtipoServico construir(ResultSet rs)
            throws SQLException {

        return new ServicoSubtipoServico(
                rs.getInt(ID),
                rs.getInt(FK_SERVICO),
                rs.getInt(FK_SUBTIPO_SERVICO)
        );
    }

    /**
     * Retorna uma string com query de INSERT, com '?' p/ ser substituído.
     *
     * @return String com comando SQL para adicionar um novo objeto.
     */
    @Override
    protected String obterSqlAdicionar() {
        return GenericSQL.adicionar(ETab.SERVICO_SUBTIPO_SERVICO, COLUNAS);
    }

    /**
     * Retorna uma string com query de UPDATE, com '?' p/ ser substituído.
     *
     * @return String com comando SQL para atualizar um objeto.
     */
    @Override
    protected String obterSqlAtualizar() {
        return GenericSQL.atualizar(ETab.SERVICO_SUBTIPO_SERVICO, COLUNAS, ID);
    }

    /**
     * Retorna uma string com query de DELETE, com '?' p/ ser substituído.
     *
     * @return String com comando SQL para deletar um objeto.
     */
    @Override
    protected String obterSqlExcluir() {
        return GenericSQL.excluirPorId(ETab.SERVICO_SUBTIPO_SERVICO, ID);
    }

    /**
     * Retorna uma string com query de SELECT, com '?' p/ ser substituído.
     *
     * @return String com comando SQL para buscar um objeto.
     */
    @Override
    protected String obterSqlSelecionar() {
        return GenericSQL.obterPorId(ETab.SERVICO_SUBTIPO_SERVICO, COLUNAS, ID);
    }

    /**
     * Define o Id de um objeto.
     *
     * @param objeto Objeto a ter seu ID atualizado.
     * @param id     Id a ser inserido no objeto.
     */
    @Override
    protected void definirId(ServicoSubtipoServico objeto, int id) {
        objeto.setId(id);
    }
}
