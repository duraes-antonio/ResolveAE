package Infraestrutura.Postgre.DAO;


import Dominio.Entidades.Contato;
import Dominio.Enum.ETipoContato;
import Dominio.Interfaces.IContatoRepositorio;
import Infraestrutura.Enum.ETab;
import Infraestrutura.Postgre.Util.Persistencia;
import Infraestrutura.Postgre.Util.SQLProdutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ContatoDAO extends AGenericDAO<Contato> implements IContatoRepositorio {
    //Nome das colunas da tabela CONTATO (nomes usados para montar as querys);
    public static final String ID = ETab.CONTATO.get() + ".id";
    public static final String DESCRICAO = ETab.CONTATO.get() + ".descricao";
    public static final String FK_USUARIO = ETab.CONTATO.get() + ".fk_usuario";
    public static final String FK_TIPO_CONTATO = ETab.CONTATO.get() + ".fk_tipo_contato";

    public static final List<String> COLUNAS = Arrays.asList(DESCRICAO, FK_USUARIO, FK_TIPO_CONTATO);

    private Persistencia persistencia = Persistencia.get();
    private Connection conexao = persistencia.getConexao();
    private PreparedStatement psTodos;
    private PreparedStatement psTodosPorTipo;
    private PreparedStatement psTodosPorUsuario;
    private PreparedStatement psTodosPorTipoEUsuario;


    private List<Contato> obterGenerico(PreparedStatement ps)
            throws SQLException {

        List<Contato> contatos = null;

        try {
            contatos = extrairTodos(persistencia.executarSelecao(ps));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) ps.close();
        }

        return contatos;
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
    public List<Contato> obterTodos(Integer limit, Integer offset) throws SQLException {
        String sql = GenericSQL.obterTodos(ETab.CONTATO, COLUNAS, ID, limit, offset);
        psTodos = conexao.prepareStatement(sql);
        return obterGenerico(psTodos);
    }

    @Override
    public List<Contato> obterTodosPorTipo(ETipoContato tipo, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = new SQLProdutor();

        sqlProd.select(ID, DESCRICAO, FK_USUARIO, FK_TIPO_CONTATO)
                .from(ETab.CONTATO.get()).where(FK_TIPO_CONTATO).eq()
                .limit(limit).offset(offset);

        psTodosPorTipo = conexao.prepareStatement(sqlProd.toString());
        psTodosPorTipo.setInt(1, tipo.getId());
        return obterGenerico(psTodosPorTipo);
    }

    @Override
    public List<Contato> obterTodosPorUsuario(int usuarioId, Integer limit, Integer offset) throws SQLException {

        SQLProdutor sqlProd = new SQLProdutor();

        sqlProd.select(ID, DESCRICAO, FK_USUARIO, FK_TIPO_CONTATO)
                .from(ETab.CONTATO.get()).where(FK_USUARIO).eq()
                .limit(limit).offset(offset);

        psTodosPorUsuario = conexao.prepareStatement(sqlProd.toString());
        psTodosPorUsuario.setInt(1, usuarioId);
        return obterGenerico(psTodosPorUsuario);
    }

    @Override
    public List<Contato> obterTodosPorTipoEUsuario(ETipoContato tipo, int usuarioId,
                                                   Integer limit, Integer offset) throws SQLException {

        SQLProdutor sqlProd = new SQLProdutor();

        sqlProd.select(ID, DESCRICAO, FK_USUARIO, FK_TIPO_CONTATO)
                .from(ETab.CONTATO.get()).where(FK_USUARIO).eq()
                .and(FK_TIPO_CONTATO).eq().limit(limit).offset(offset);

        psTodosPorTipoEUsuario = conexao.prepareStatement(sqlProd.toString());
        psTodosPorTipoEUsuario.setInt(1, usuarioId);
        psTodosPorTipoEUsuario.setInt(2, tipo.getId());
        return obterGenerico(psTodosPorTipoEUsuario);
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
    protected PreparedStatement preencherPS(PreparedStatement ps, Contato objeto) throws SQLException {

        ps.setString(COLUNAS.indexOf(DESCRICAO) + 1, objeto.getDescricao());
        ps.setInt(COLUNAS.indexOf(FK_USUARIO) + 1, objeto.getFkUsuario());
        ps.setInt(COLUNAS.indexOf(FK_TIPO_CONTATO) + 1, objeto.getFkTipoContato());

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
    protected Contato construir(ResultSet rs)
            throws SQLException {

        return new Contato(
                rs.getInt(ID),
                rs.getString(DESCRICAO),
                rs.getInt(FK_USUARIO),
                rs.getInt(FK_TIPO_CONTATO)
        );
    }

    /**
     * Retorna uma string com query de INSERT, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para adicionar um novo objeto.
     */
    @Override
    protected String obterSqlAdicionar() {
        return GenericSQL.adicionar(ETab.CONTATO, COLUNAS);
    }

    /**
     * Retorna uma string com query de UPDATE, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para atualizar um objeto.
     */
    @Override
    protected String obterSqlAtualizar() {
        return GenericSQL.atualizar(ETab.CONTATO, COLUNAS, ID);
    }

    /**
     * Retorna uma string com query de DELETE, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para deletar um objeto.
     */
    @Override
    protected String obterSqlExcluir() {
        return GenericSQL.excluirPorId(ETab.CONTATO, ID);
    }

    /**
     * Retorna uma string com query de SELECT, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para buscar um objeto.
     */
    @Override
    protected String obterSqlSelecionar() {
        return GenericSQL.obterPorId(ETab.CONTATO, COLUNAS, ID);
    }

    /**
     * Define o Id de um objeto.
     *
     * @param objeto Objeto a ter seu ID atualizado.
     * @param id     Id a ser inserido no objeto.
     */
    @Override
    protected void definirId(Contato objeto, int id) {
        objeto.setId(id);
    }
}

