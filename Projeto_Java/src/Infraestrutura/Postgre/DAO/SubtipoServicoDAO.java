package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.SubtipoServico;
import Dominio.Interfaces.ISubtipoServicoRepositorio;
import Infraestrutura.Enum.ETab;
import Infraestrutura.Postgre.Util.Persistencia;
import Infraestrutura.Postgre.Util.SQLProdutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class SubtipoServicoDAO extends AGenericDAO<SubtipoServico> implements ISubtipoServicoRepositorio {

    public static final String ID = ETab.SUBTIPO_SERVICO.get() + ".id";
    public static final String NOME = ETab.SUBTIPO_SERVICO.get() + ".nome";
    public static final String FK_TIPO_SERVICO = ETab.SUBTIPO_SERVICO.get() + ".fk_tipo_servico";

    public static final List<String> COLUNAS = Arrays.asList(NOME, FK_TIPO_SERVICO);

    Persistencia persistencia = Persistencia.get();
    Connection conexao = persistencia.getConexao();
    PreparedStatement psTodos;

    private List<SubtipoServico> obterVarios(PreparedStatement ps)
            throws SQLException {

        List<SubtipoServico> subtipos = null;

        try {
            subtipos = extrairTodos(persistencia.executarSelecao(ps));
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            if (ps != null) ps.close();
        }

        return subtipos;
    }

    /**
     * Substitui os '?' do PS pelos valores dos atributos da objeto.
     * Substitui os valores para operações de INSERT e UPDATE apenas.
     *
     * @param ps     P. Statement com SQL já pronto com os '?' para serem substituídos.
     * @param subtipo Objeto com os atributos para preencher o statement.
     * @return ps com os '?' substituídos, pronto para execução.
     */
    @Override
    protected PreparedStatement preencherPS(PreparedStatement ps, SubtipoServico subtipo) throws SQLException {

        ps.setString(1, subtipo.getNome());
        ps.setInt(2, subtipo.getFkTipoServico());

        if (subtipo.getId() > 0) {
            ps.setInt(COLUNAS.size() + 1, subtipo.getId());
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
    protected SubtipoServico construir(ResultSet rs) throws SQLException {
        return new SubtipoServico(
                rs.getInt(ID),
                rs.getString(NOME),
                rs.getInt(FK_TIPO_SERVICO)
        );
    }

    /**
     * Retorna uma string com query de INSERT, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para adicionar um novo objeto.
     */
    @Override
    protected String obterSqlAdicionar() {
        return GenericSQL.adicionar(ETab.SUBTIPO_SERVICO, COLUNAS);
    }

    /**
     * Retorna uma string com query de UPDATE, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para atualizar um objeto.
     */
    @Override
    protected String obterSqlAtualizar() {
        return GenericSQL.atualizar(ETab.SUBTIPO_SERVICO, COLUNAS, ID);
    }

    /**
     * Retorna uma string com query de DELETE, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para deletar um objeto.
     */
    @Override
    protected String obterSqlExcluir() {
        return GenericSQL.excluirPorId(ETab.SUBTIPO_SERVICO, ID);
    }

    /**
     * Retorna uma string com query de SELECT, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para buscar um objeto.
     */
    @Override
    protected String obterSqlSelecionar() {
        return GenericSQL.obterPorId(ETab.SUBTIPO_SERVICO, COLUNAS, ID);
    }

    /**
     * Define o Id de um objeto.
     *
     * @param objeto Objeto a ter seu ID atualizado.
     * @param id     Id a ser inserido no objeto.
     */
    @Override
    protected void definirId(SubtipoServico objeto, int id) {
        objeto.setId(id);
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
    public List<SubtipoServico> obterTodos(Integer limit, Integer offset) throws SQLException {

        SQLProdutor sqlProd = new SQLProdutor();
        sqlProd.select(ID, NOME, FK_TIPO_SERVICO).from(ETab.SUBTIPO_SERVICO.get());
        String sql = sqlProd.toString();
        psTodos = conexao.prepareStatement(sql);
        return obterVarios(psTodos);
    }

}
