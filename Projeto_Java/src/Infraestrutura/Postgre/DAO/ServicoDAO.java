package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.Servico;
import Dominio.Entidades.ServicoSubtipoServico;
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
import java.util.Collections;
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
    private PreparedStatement psAdicionar;
    private PreparedStatement psTodos;
    private PreparedStatement psTodosPorUsuario;
    private PreparedStatement psTodosPorTitulo;
    private PreparedStatement psTodosPorDescricao;
    private PreparedStatement psTodosPorValor;
    private PreparedStatement psTodosPorTipo;
    private PreparedStatement psTodosPorSubTipo;

    ServicoSubtipoServicoDAO sssDAO;
    
    public ServicoDAO() {
        sssDAO = new ServicoSubtipoServicoDAO();
    }

    private List<Servico> obterGenerico(PreparedStatement ps)
            throws SQLException {

        List<Servico> servicos = null;

        try {
            ResultSet rs = persistencia.executarSelecao(ps);
            servicos = extrairTodos(rs);
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            if (ps != null) ps.close();
        }

        return servicos;
    }

    private SQLProdutor obterSQlGeral() {

        SQLProdutor sqlProd = new SQLProdutor();
        sqlProd.select(
                ID, DESCRICAO, TITULO, VALOR, FK_TIPO_SERVICO,
                FK_USUARIO, FK_CONTRATO, ServicoSubtipoServicoDAO.FK_SUBTIPO_SERVICO);
        sqlProd.from(ETab.SERVICO.get()).innerJoin(ETab.SERVICO_SUBTIPO_SERVICO.get());
        sqlProd.on(ID, ServicoSubtipoServicoDAO.FK_SERVICO);

        return sqlProd;
    }

    /**
     * Busca e retorna todos servicos de um determinado tipo.
     *
     * @param limit  Quantidade de resultados a ser retornada.
     * @param offset Quantidade de resultados a pular.
     * @return Lista com todos servicos encontrados.
     * @throws SQLException
     */
    @Override
    public List<Servico> obterTodos(Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = obterSQlGeral();
        sqlProd.orderBy(1).limit(limit).offset(offset);
        String sql = sqlProd.toString();
        psTodos = conexao.prepareStatement(sql);
        return obterGenerico(psTodos);
    }

    @Override
    public List<Servico> obterTodosPorUsuario(int usuarioId, Integer limit, Integer offset)
            throws SQLException {
        SQLProdutor sqlProd = obterSQlGeral();
        sqlProd.where(FK_USUARIO).eq().orderBy(1).limit(limit).offset(offset);
        String sql = sqlProd.toString();

        psTodosPorUsuario = conexao.prepareStatement(sql);
        psTodosPorUsuario.setInt(1, usuarioId);
        return obterGenerico(psTodosPorUsuario);
    }

    @Override
    public List<Servico> obterTodosPorTitulo(String titulo, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = obterSQlGeral();
        sqlProd.where(TITULO).ilike().orderBy(1).limit(limit).offset(offset);

        String sql = sqlProd.toString();
        psTodosPorTitulo = conexao.prepareStatement(sql);
        psTodosPorTitulo.setString(1, "%" + titulo + "%");
        return obterGenerico(psTodosPorTitulo);
    }

    @Override
    public List<Servico> obterTodosPorDescricao(String descricao, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = obterSQlGeral();
        sqlProd.where(DESCRICAO).ilike().orderBy(1).limit(limit).offset(offset);

        String sql = sqlProd.toString();
        psTodosPorDescricao = conexao.prepareStatement(sql);
        psTodosPorDescricao.setString(1, "%" + descricao + "%");
        return obterGenerico(psTodosPorDescricao);
    }

    @Override
    public List<Servico> obterTodosPorValor(double valorMin, double valorMax, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = obterSQlGeral();
        sqlProd.where(VALOR).grteq().and().leq().orderBy(1).limit(limit).offset(offset);

        String sql = sqlProd.toString();
        psTodosPorValor = conexao.prepareStatement(sql);
        psTodosPorValor.setDouble(1, valorMin);
        psTodosPorValor.setDouble(2, valorMin);

        return obterGenerico(psTodosPorValor);
    }

    @Override
    public List<Servico> obterTodosPorTipo(ETipoServico tipo, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = obterSQlGeral();
        sqlProd.where(FK_TIPO_SERVICO).eq().orderBy(1).limit(limit).offset(offset);

        String sql = sqlProd.toString();
        psTodosPorTipo = conexao.prepareStatement(sql);
        psTodosPorTipo.setInt(1, tipo.getId());

        return obterGenerico(psTodosPorTipo);
    }

    @Override
    public List<Servico> obterTodosPorSubtipo(ESubtipoServico subtipo, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = obterSQlGeral();
        sqlProd.where(FK_TIPO_SERVICO).eq().orderBy(1).limit(limit).offset(offset);

        String sql = sqlProd.toString();
        psTodosPorSubTipo = conexao.prepareStatement(sql);
        psTodosPorSubTipo.setInt(1, subtipo.getId());

        return obterGenerico(psTodosPorSubTipo);
    }

    /**
     * Persiste o servico em um meio não volátil de armazenamento.
     *
     * @param servico servico a ser persistido.
     * @return T servico atualizado com Id.
     * @throws SQLException
     */
    @Override
    public Servico adicionar(Servico servico) throws SQLException {

        try {
            ServicoSubtipoServico sss = new ServicoSubtipoServico();

            //Salve o serviço;
            super.adicionar(servico);

            // Para cada subtipo, relacione-o com o serviço na tabela "servico_subtipo_servico";
            for (ESubtipoServico subtipo: servico.getSubtipos()) {
                sss.setFkServico(servico.getId());
                sss.setFkSubtipoServico(subtipo.getId());
                sssDAO.adicionar(sss);
                sss.setId(0);
            }
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            if (psAdicionar != null) psAdicionar.close();
        }

        return servico;
    }


    /**
     * Substitui os '?' do PS pelos valores dos atributos da servico.
     * Substitui os valores para operações de INSERT e UPDATE apenas.
     *
     * @param ps     P. Statement com SQL já pronto com os '?' para serem substituídos.
     * @param servico servico com os atributos para preencher o statement.
     * @return ps com os '?' substituídos, pronto para execução.
     */
    @Override
    protected PreparedStatement preencherPS(PreparedStatement ps, Servico servico)
            throws SQLException {

        ps.setString(1, servico.getTitulo());
        ps.setString(2, servico.getDescricao());
        ps.setDouble(3, servico.getValor());
        ps.setDouble(4, servico.getFkTipoServico());
        ps.setDouble(5, servico.getFkUsuario());
        ps.setDouble(6, servico.getFkContrato());

        if (servico.getId() > 0) {
            ps.setInt(COLUNAS.size() + 1, servico.getId());
        }

        return null;
    }

    private Servico construirServico(ResultSet rs)
            throws SQLException {

        return new Servico(
                rs.getInt(ID),
                rs.getString(TITULO),
                rs.getString(DESCRICAO),
                rs.getDouble(VALOR),
                rs.getInt(FK_TIPO_SERVICO),
                rs.getInt(FK_USUARIO),
                rs.getInt(FK_CONTRATO)
        );
    }

    /**
     * Monta e retorna o servico a partir de um resultSet.
     * Não faz validações sobre o resultSet.
     *
     * @param rs ResultSet retornado de uma consulta já executada.
     * @return servico montado a partir dos resultados da consulta.
     */
    protected Servico construir(ResultSet rs)
            throws SQLException {
        Servico servico = construirServico(rs);

        List<ServicoSubtipoServico> sssList = sssDAO.obterPorServico(servico.getId(), null, null);
        sssList.forEach(x -> servico.addSubtipoServico(ESubtipoServico.getById(x.getFkSubtipoServico())));

        return servico;
    }

    /**
     * Monta e retorna uma lista de objetos a partir de um resultSet.
     *
     * @param rs ResultSet retornado de uma consulta já executada.
     * @return Objeto montado a partir dos resultados da consulta.
     */
    public List<Servico> extrairTodos(ResultSet rs)
            throws SQLException {

        List<Servico> objetos = new ArrayList<>();
        Servico servico;
        int indice;

        // Enquanto houver registros;
        while (rs.next()) {

            // Construa o serviço a partir do registro;
            servico = construir(rs);

            // Verifique se o serviço já está na lista e guarde seu índice;
            indice = Collections.binarySearch(objetos, servico);

            if (indice > -1) {
                servico.addSubtipoServico(ESubtipoServico.getById(rs.getInt(ServicoSubtipoServicoDAO.FK_SUBTIPO_SERVICO)));

                objetos.get(indice).addSubtipoServico(servico.getSubtipos().get(0));
            }

            else {
                objetos.add(servico);
            }
        }

        return objetos;
    }

    public Servico obterPorId(int id)
            throws SQLException {

        List<ServicoSubtipoServico> ssss;
        ssss = sssDAO.obterPorServico(id, null, null);
        Servico servico = super.obterPorId(id);

        // Para cada subtipo, relacione-o com o serviço na tabela "servico_subtipo_servico";
        for (ServicoSubtipoServico sss: ssss) {
            servico.getSubtipos().add(ESubtipoServico.getById(sss.getFkSubtipoServico()));
        }

        return servico;
    }

    /**
     * Retorna uma string com query de INSERT, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para adicionar um novo servico.
     */
    @Override
    protected String obterSqlAdicionar() {
        return GenericSQL.adicionar(ETab.SERVICO, COLUNAS);
    }

    /**
     * Retorna uma string com query de UPDATE, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para atualizar um servico.
     */
    @Override
    protected String obterSqlAtualizar() {
        return GenericSQL.adicionar(ETab.SERVICO, COLUNAS);
    }

    /**
     * Retorna uma string com query de DELETE, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para deletar um servico.
     */
    @Override
    protected String obterSqlExcluir() {
        return GenericSQL.excluirPorId(ETab.SERVICO, ID);
    }

    /**
     * Retorna uma string com query de SELECT, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para buscar um servico.
     */
    @Override
    protected String obterSqlSelecionar() {
        return GenericSQL.obterPorId(ETab.SERVICO, COLUNAS, ID);
    }

    /**
     * Define o Id de um servico.
     *
     * @param servico servico a ter seu ID atualizado.
     * @param id     Id a ser inserido no servico.
     */
    @Override
    protected void definirId(Servico servico, int id) {
        servico.setId(id);
    }
}
