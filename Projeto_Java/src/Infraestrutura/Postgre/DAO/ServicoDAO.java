package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.Servico;
import Dominio.Enum.ESubtipoServico;
import Dominio.Enum.ETipoServico;
import Dominio.Interfaces.IServicoRepositorio;
import Infraestrutura.Enum.ETab;
import Infraestrutura.Postgre.Util.Persistencia;
import Infraestrutura.Postgre.Util.SQLProdutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServicoDAO extends AGenericDAO<Servico> implements IServicoRepositorio {


    public static final String ID = ETab.SERVICO.get() + ".id";
    public static final String DESCRICAO = ETab.SERVICO.get() + ".descricao";
    public static final String TITULO = ETab.SERVICO.get() + ".titulo";
    public static final String VALOR = ETab.SERVICO.get() + ".valor";
    public static final String FK_CONTRATO = ETab.SERVICO.get() + ".fk_contrato";
    public static final String FK_TIPO_SERVICO = ETab.SERVICO.get() + ".fk_tipo_servico";
    public static final String FK_USUARIO = ETab.SERVICO.get() + ".fk_usuario";


    public static final List<String> COLUNAS = Arrays.asList(
            DESCRICAO, TITULO, VALOR, FK_TIPO_SERVICO, FK_USUARIO, FK_CONTRATO);

    private Persistencia persistencia = Persistencia.get();
    private Connection conexao = persistencia.getConexao();
    private PreparedStatement psTodos;
    private PreparedStatement psTodosPorUsuario;
    private PreparedStatement psTodosPorTitulo;
    private PreparedStatement psTodosPorDescricao;
    private PreparedStatement psTodosPorValor;
    private PreparedStatement psTodosPorTipo;
    private PreparedStatement psTodosPorSubTipo;

    public String teste() {
        SQLProdutor sqlProd = new SQLProdutor();
        sqlProd.select
                (
                        ID, DESCRICAO, TITULO, VALOR, FK_CONTRATO, FK_TIPO_SERVICO,
                        FK_USUARIO, SubtipoServicoDAO.ID)
                .from(ETab.SERVICO.get()).innerJoin(ETab.SERVICO_SUBTIPO_SERVICO.get())
                .on(ID, ServicoSubtipoServicoDAO.FK_SERVICO)
                .innerJoin(ETab.SUBTIPO_SERVICO.get())
                .on(ServicoSubtipoServicoDAO.FK_SUBTIPO_SERVICO, SubtipoServicoDAO.ID)
                .orderBy(FK_TIPO_SERVICO);
        return sqlProd.toString();
    }

    private List<Servico> obterGenerico(PreparedStatement ps)
            throws SQLException {

        List<Servico> servicos = null;

        try {
            servicos = extrairTodos(persistencia.executarSelecao(ps));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) ps.close();
        }

        return servicos;
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
    public List<Servico> obterTodos(Integer limit, Integer offset)
            throws SQLException {

        String sql = GenericSQL.obterTodos(ETab.INFO_PRO, COLUNAS, ID, limit, offset);
        psTodos = conexao.prepareStatement(sql);
        return obterGenerico(psTodos);
    }

    @Override
    public List<Servico> obterTodosPorUsuario(int usuarioId, Integer limit, Integer offset) {
        return null;
    }

    @Override
    public List<Servico> obterTodosPorTitulo(String titulo, Integer limit, Integer offset) {
        return null;
    }

    @Override
    public List<Servico> obterTodosPorDescricao(String descricao, Integer limit, Integer offset) {
        return null;
    }

    @Override
    public List<Servico> obterTodosPorValor(double valorMin, double valorMax, Integer limit, Integer offset) {
        return null;
    }

    @Override
    public List<Servico> obterTodosPorTipo(ETipoServico tipo, Integer limit, Integer offset) {
        return null;
    }

    @Override
    public List<Servico> obterTodosPorSubtipo(ESubtipoServico subtipo, Integer limit, Integer offset) {
        return null;
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
    protected PreparedStatement preencherPS(PreparedStatement ps, Servico objeto)
            throws SQLException {

        ps.setString(1, objeto.getTitulo());
        ps.setString(2, objeto.getDescricao());
        ps.setDouble(3, objeto.getValor());
        ps.setDouble(4, objeto.getFkTipoServico());
        ps.setDouble(5, objeto.getFkUsuario());
        ps.setDouble(6, objeto.getFkContrato());

        if (objeto.getId() > 0) {
            ps.setInt(COLUNAS.size() + 1, objeto.getId());
        }

        return null;
    }

    /**
     * Monta e retorna o objeto a partir de um resultSet.
     * Não faz validações sobre o resultSet.
     *
     * @param rs ResultSet retornado de uma consulta já executada.
     * @return Objeto montado a partir dos resultados da consulta.
     */
    @Override
    protected Servico construir(ResultSet rs) throws SQLException {

        Servico servico = new Servico(
                rs.getInt(ID),
                rs.getString(TITULO),
                rs.getString(DESCRICAO),
                null,
                rs.getDouble(VALOR),
                rs.getInt(FK_TIPO_SERVICO),
                rs.getInt(FK_USUARIO),
                rs.getInt(FK_CONTRATO)
        );

        List<ESubtipoServico> subtipos = new ArrayList<>();
        subtipos.add(ESubtipoServico.getById(rs.getInt(SubtipoServicoDAO.ID)));

        while (rs.next()) {
            subtipos.add(ESubtipoServico.getById(rs.getInt(SubtipoServicoDAO.ID)));
        }

        servico.setSubtipoServico(subtipos);

        return servico;
    }

    /**
     * Retorna uma string com query de INSERT, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para adicionar um novo objeto.
     */
    @Override
    protected String obterSqlAdicionar() {
        return GenericSQL.adicionar(ETab.SERVICO, COLUNAS);
    }

    /**
     * Retorna uma string com query de UPDATE, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para atualizar um objeto.
     */
    @Override
    protected String obterSqlAtualizar() {
        return GenericSQL.adicionar(ETab.SERVICO, COLUNAS);
    }

    /**
     * Retorna uma string com query de DELETE, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para deletar um objeto.
     */
    @Override
    protected String obterSqlExcluir() {
        return GenericSQL.excluirPorId(ETab.SERVICO, ID);
    }

    /**
     * Retorna uma string com query de SELECT, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para buscar um objeto.
     */
    @Override
    protected String obterSqlSelecionar() {
        return GenericSQL.obterPorId(ETab.SERVICO, COLUNAS, ID);
    }

    /**
     * Define o Id de um objeto.
     *
     * @param objeto Objeto a ter seu ID atualizado.
     * @param id     Id a ser inserido no objeto.
     */
    @Override
    protected void definirId(Servico objeto, int id) {
        objeto.setId(id);
    }
}
