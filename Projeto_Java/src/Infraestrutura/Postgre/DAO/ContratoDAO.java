package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.Contrato;
import Dominio.Interfaces.IContratoRepositorio;
import Infraestrutura.Enum.ETab;
import Infraestrutura.Postgre.Util.Persistencia;
import Infraestrutura.Postgre.Util.SQLProdutor;

import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


public class ContratoDAO extends AGenericDAO<Contrato> implements IContratoRepositorio {


    //Nome das colunas da tabela CONTATO (nomes usados para montar as querys);
    public static final String ID = ETab.CONTRATO.get() + ".id";
    public static final String DATA_INICIO = ETab.CONTRATO.get() + ".data_inicio";
    public static final String DATA_FIM = ETab.CONTRATO.get() + ".data_fim";
    public static final String DATA_ULT_MODIF = ETab.CONTRATO.get() + ".data_ult_modif";

    public static final String DESCRICAO = ETab.CONTRATO.get() + ".descricao";
    public static final String HORAS_CONTRATADAS = ETab.CONTRATO.get() + ".horas_contratadas";
    public static final String FK_USUARIO = ETab.CONTRATO.get() + ".fk_usuario";

    public static final List<String> COLUNAS = Arrays.asList(
            DATA_INICIO, DATA_FIM, DATA_ULT_MODIF, DESCRICAO,
            HORAS_CONTRATADAS, FK_USUARIO);

    private Persistencia persistencia = Persistencia.get();
    private Connection conexao = persistencia.getConexao();
    private PreparedStatement psTodos;
    private PreparedStatement psTodosPorUsuario;
    private PreparedStatement psTodosPorServico;
    private PreparedStatement psTodosPorDescricao;
    private PreparedStatement psTodosPorData;
    private PreparedStatement psTodosPorValor;


    private List<Contrato> obterGenerico(PreparedStatement ps)
            throws SQLException {

        List<Contrato> contratos = null;

        try {
            contratos = extrairTodos(persistencia.executarSelecao(ps));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) ps.close();
        }

        return contratos;
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
    public List<Contrato> obterTodos(Integer limit, Integer offset)
            throws SQLException {
        SQLProdutor sqlProd = new SQLProdutor();

        sqlProd.select(ID, DATA_INICIO, DATA_FIM, DATA_ULT_MODIF, DESCRICAO,
                HORAS_CONTRATADAS, FK_USUARIO)
                .from(ETab.CONTRATO.get()).limit(limit).offset(offset);

        psTodos = conexao.prepareStatement(sqlProd.toString());
        return obterGenerico(psTodos);
    }

    @Override
    public List<Contrato> obterTodosPorUsuario(int usuarioId, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = new SQLProdutor();

        sqlProd.select(ID, DATA_INICIO, DATA_FIM, DATA_ULT_MODIF, DESCRICAO,
                HORAS_CONTRATADAS, FK_USUARIO)
                .from(ETab.CONTRATO.get()).where(FK_USUARIO).eq()
                .limit(limit).offset(offset);

        psTodosPorUsuario = conexao.prepareStatement(sqlProd.toString());
        psTodosPorUsuario.setInt(1, usuarioId);
        return obterGenerico(psTodosPorUsuario);
    }

//    @Override
//    public Contrato obterPorServico(int servicoId, Integer limit, Integer offset)
//            throws SQLException {
//
//        SQLProdutor sqlProd = new SQLProdutor();
//
//        sqlProd.select(ID, DATA_INICIO, DATA_FIM, DATA_ULT_MODIF, DESCRICAO,
//                HORAS_CONTRATADAS, FK_USUARIO)
//                .from(ETab.CONTRATO.get()).innerJoin(ETab.SERVICO.get())
//                .on(ID, ServicoDAO.FK_CONTRATO).where(ServicoDAO.ID).eq()
//                .limit(limit).offset(offset);
//
//        psTodosPorServico = conexao.prepareStatement(sqlProd.toString());
//        psTodosPorServico.setInt(1, servicoId);
//        List<Contrato> contratos = obterGenerico(psTodosPorServico);
//        return contratos != null && contratos.size() > 0 ? contratos.get(0) : null;
//
//    }

    @Override
    public List<Contrato> obterTodosPorDescricao(String descricao, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = new SQLProdutor();

        sqlProd.select(ID, DATA_INICIO, DATA_FIM, DATA_ULT_MODIF, DESCRICAO,
                HORAS_CONTRATADAS, FK_USUARIO)
                .from(ETab.CONTRATO.get()).where(DESCRICAO).ilike()
                .limit(limit).offset(offset);

        psTodosPorDescricao = conexao.prepareStatement(sqlProd.toString());
        psTodosPorDescricao.setString(1, "%" + descricao + "%");
        return obterGenerico(psTodosPorDescricao);
    }

    @Override
    public List<Contrato> obterTodosPorData(LocalDate inicio, LocalDate fim, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = new SQLProdutor();

        sqlProd.select(ID, DATA_INICIO, DATA_FIM, DATA_ULT_MODIF, DESCRICAO, HORAS_CONTRATADAS, FK_USUARIO);
        sqlProd.from(ETab.CONTRATO.get()).where(DATA_INICIO).grteq();
        sqlProd.and(DATA_FIM).leq().orderBy(2).limit(limit).offset(offset);

        String sql = sqlProd.toString();
        psTodosPorData = conexao.prepareStatement(sql);
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
    protected PreparedStatement preencherPS(PreparedStatement ps, Contrato objeto) throws SQLException {

        if (objeto.getDataInicio() != null) {
            ps.setDate(1, Date.valueOf(objeto.getDataInicio()));
        } else {
            ps.setDate(1, null);
        }

        if (objeto.getDataFim() != null) {
            ps.setDate(2, Date.valueOf(objeto.getDataFim()));
        } else {
            ps.setDate(2, null);
        }

        if (objeto.getDataUltimaModif() != null) {
            ps.setTimestamp(3, Timestamp.valueOf(objeto.getDataUltimaModif()));
        } else {
            ps.setTimestamp(3, null);
        }
        ps.setString(4, objeto.getDescricao());
        ps.setInt(5, objeto.getHorasContratadas());
        ps.setInt(6, objeto.getFkUsuario());

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
    protected Contrato construir(ResultSet rs) throws SQLException {
        return new Contrato(
                rs.getInt(ID),
                rs.getString(DESCRICAO),
                rs.getDate(DATA_INICIO).toLocalDate(),
                rs.getDate(DATA_FIM).toLocalDate(),
                rs.getTimestamp(DATA_ULT_MODIF).toLocalDateTime(),
                rs.getInt(HORAS_CONTRATADAS),
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
        return GenericSQL.adicionar(ETab.CONTRATO, COLUNAS);
    }

    /**
     * Retorna uma string com query de UPDATE, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para atualizar um objeto.
     */
    @Override
    protected String obterSqlAtualizar() {
        return GenericSQL.atualizar(ETab.CONTRATO, COLUNAS, ID);
    }

    /**
     * Retorna uma string com query de DELETE, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para deletar um objeto.
     */
    @Override
    protected String obterSqlExcluir() {
        return GenericSQL.excluirPorId(ETab.CONTRATO, ID);
    }

    /**
     * Retorna uma string com query de SELECT, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para buscar um objeto.
     */
    @Override
    protected String obterSqlSelecionar() {
        return GenericSQL.obterPorId(ETab.CONTRATO, COLUNAS, ID);
    }

    /**
     * Define o Id de um objeto.
     *
     * @param objeto Objeto a ter seu ID atualizado.
     * @param id     Id a ser inserido no objeto.
     */
    @Override
    protected void definirId(Contrato objeto, int id) {
        objeto.setId(id);
    }
}
