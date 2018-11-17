package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.InfoProfissional;
import Dominio.Enum.ETipoInfoProfissional;
import Dominio.Interfaces.IInfoProfissionalRepositorio;
import Infraestrutura.Enum.ETab;
import Infraestrutura.Postgre.Util.Persistencia;
import Infraestrutura.Postgre.Util.SQLProdutor;

import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class InfoProfissionalDAO extends AGenericDAO<InfoProfissional>
        implements IInfoProfissionalRepositorio {

    public static final String ID = ETab.INFO_PRO.get() + ".id";
    public static final String DESCRICAO = ETab.INFO_PRO.get() + ".descricao";
    public static final String DATA_INICIO = ETab.INFO_PRO.get() + ".data_inicio";
    public static final String DATA_FIM = ETab.INFO_PRO.get() + ".data_fim";
    public static final String FK_USUARIO = ETab.INFO_PRO.get() + ".fk_usuario";
    public static final String FK_TIPO_INFO_PROF = ETab.INFO_PRO.get() + ".fk_tipo_info_prof";

    public static final List<String> COLUNAS = Arrays.asList(
            DESCRICAO, DATA_INICIO, DATA_FIM, FK_USUARIO, FK_TIPO_INFO_PROF);

    private Persistencia persistencia = Persistencia.get();
    private Connection conexao = persistencia.getConexao();
    private PreparedStatement psTodos;
    private PreparedStatement psTodosPorTipo;
    private PreparedStatement psTodosPorUsuario;
    private PreparedStatement psTodosPorTipoEUsuario;
    private PreparedStatement psTodosPorData;


    private List<InfoProfissional> obterGenerico(PreparedStatement ps)
            throws SQLException {

        List<InfoProfissional> infos = null;

        try {
            infos = extrairTodos(persistencia.executarSelecao(ps));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) ps.close();
        }

        return infos;
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
    public List<InfoProfissional> obterTodos(Integer limit, Integer offset)
            throws SQLException {

        String sql = GenericSQL.obterTodos(ETab.INFO_PRO, COLUNAS, ID, limit, offset);
        psTodos = conexao.prepareStatement(sql);
        return obterGenerico(psTodos);
    }

    @Override
    public List<InfoProfissional> obterTodosPorTipo(ETipoInfoProfissional tipo, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = new SQLProdutor();
        sqlProd.select(COLUNAS).from(ETab.INFO_PRO.get())
                .where(FK_TIPO_INFO_PROF).eq().limit(limit).offset(offset);

        psTodosPorTipo = conexao.prepareStatement(sqlProd.toString());
        psTodosPorTipo.setInt(1, tipo.getId());
        return obterGenerico(psTodosPorTipo);
    }

    @Override
    public List<InfoProfissional> obterTodosPorUsuario(int usuarioId, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = new SQLProdutor();
        sqlProd.select(COLUNAS).from(ETab.INFO_PRO.get())
                .where(FK_USUARIO).eq().limit(limit).offset(offset);

        psTodosPorUsuario = conexao.prepareStatement(sqlProd.toString());
        psTodosPorUsuario.setInt(1, usuarioId);
        return obterGenerico(psTodosPorUsuario);
    }

    @Override
    public List<InfoProfissional> obterTodosPorTipoEUsuario(ETipoInfoProfissional tipo, int usuarioId, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = new SQLProdutor();
        sqlProd.select(COLUNAS).from(ETab.INFO_PRO.get())
                .where(FK_USUARIO).eq().and(FK_TIPO_INFO_PROF).eq()
                .limit(limit).offset(offset);

        psTodosPorTipoEUsuario = conexao.prepareStatement(sqlProd.toString());
        psTodosPorTipoEUsuario.setInt(1, usuarioId);
        psTodosPorTipoEUsuario.setInt(2, tipo.getId());
        return obterGenerico(psTodosPorTipoEUsuario);
    }

    @Override
    public List<InfoProfissional> obterTodosPorData(LocalDate inicio, LocalDate fim, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = new SQLProdutor();
        sqlProd.select(COLUNAS).from(ETab.INFO_PRO.get())
                .where(DATA_INICIO).grteq().and(DATA_FIM).leq()
                .limit(limit).offset(offset);

        psTodosPorData = conexao.prepareStatement(sqlProd.toString());
        psTodosPorData.setDate(1, Date.valueOf(inicio));
        psTodosPorData.setDate(2, Date.valueOf(fim));

        return obterGenerico(psTodosPorData);
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
    protected PreparedStatement preencherPS(PreparedStatement ps, InfoProfissional objeto) throws SQLException {

        ps.setString(COLUNAS.indexOf(DESCRICAO) + 1, objeto.getDescricao());
        ps.setDate(COLUNAS.indexOf(DATA_INICIO) + 1, Date.valueOf(objeto.getDataInicio()));
        ps.setDate(COLUNAS.indexOf(DATA_FIM) + 1, Date.valueOf(objeto.getDataFim()));
        ps.setInt(COLUNAS.indexOf(FK_USUARIO) + 1, objeto.getFkUsuario());
        ps.setInt(COLUNAS.indexOf(FK_TIPO_INFO_PROF) + 1, objeto.getFkTipoInfo());

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
    protected InfoProfissional construir(ResultSet rs) throws SQLException {
        return new InfoProfissional(
                rs.getInt(ID),
                rs.getString(DESCRICAO),
                rs.getDate(DATA_INICIO).toLocalDate(),
                rs.getDate(DATA_FIM).toLocalDate(),
                rs.getInt(FK_TIPO_INFO_PROF),
                rs.getInt(FK_USUARIO)
        );
    }

    /**
     * Retorna uma string com query de INSERT, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para adicionar um novo objeto.
     */
    @Override
    protected String obterSqlAdicionar() {
        return GenericSQL.adicionar(ETab.INFO_PRO, COLUNAS);
    }

    /**
     * Retorna uma string com query de UPDATE, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para atualizar um objeto.
     */
    @Override
    protected String obterSqlAtualizar() {
        return GenericSQL.atualizar(ETab.INFO_PRO, COLUNAS, ID);
    }

    /**
     * Retorna uma string com query de DELETE, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para deletar um objeto.
     */
    @Override
    protected String obterSqlExcluir() {
        return GenericSQL.excluirPorId(ETab.INFO_PRO, ID);
    }

    /**
     * Retorna uma string com query de SELECT, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para buscar um objeto.
     */
    @Override
    protected String obterSqlSelecionar() {
        return GenericSQL.obterPorId(ETab.INFO_PRO, COLUNAS, ID);
    }

    /**
     * Define o Id de um objeto.
     *
     * @param objeto Objeto a ter seu ID atualizado.
     * @param id     Id a ser inserido no objeto.
     */
    @Override
    protected void definirId(InfoProfissional objeto, int id) {
        objeto.setId(id);
    }
}
