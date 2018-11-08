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
import java.util.List;

public class ContatoDAO extends AGenericDAO<Contato>
        implements IContatoRepositorio
{
    private Persistencia persistencia = Persistencia.get();
    private Connection conexao = persistencia.getConexao();
    private PreparedStatement psTodosPorTipo;
    private PreparedStatement psTodosPorUsuario;
    private PreparedStatement psTodosPorTipoEUsuario;

    private List<Contato> obterGenerico(PreparedStatement ps)
            throws SQLException {

        List<Contato> contatos = null;

        try {
            contatos = extrairTodos(persistencia.executarSelecao(ps));
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            if (ps != null) ps.close();
        }

        return contatos;
    }

    public List<Contato> obterTodosPorTipo(ETipoContato tipo, Integer limit, Integer offset)
            throws SQLException {
        SQLProdutor sqlProd = new SQLProdutor();
        sqlProd.select(ContatoSQL.ID, ContatoSQL.DESCRICAO, ContatoSQL.FK_USUARIO, ContatoSQL.FK_TIPO_CONTATO)
                .from(ETab.CONTATO.get()).where(ContatoSQL.FK_TIPO_CONTATO).eq();

        if (limit != null) sqlProd.limit();
        if (offset != null) sqlProd.offset();

        psTodosPorTipo = conexao.prepareStatement(sqlProd.toString());

        psTodosPorTipo.setInt(1, ETipoContato.getIdTipoContato(tipo));
        if (limit != null) psTodosPorTipo.setInt(2, limit);
        if (offset != null) psTodosPorTipo.setInt(3, offset);

        return obterGenerico(psTodosPorTipo);
    }

    @Override
    public List<Contato> obterTodosPorTipo(ETipoContato tipo) throws SQLException {
        return null;
    }

    @Override
    public List<Contato> obterTodosPorUsuario(int usuarioId) throws SQLException {
        return null;
    }

    public List<Contato> obterTodosPorUsuario(int usuarioId, int limit, int offset)
            throws SQLException {
        psTodosPorUsuario = conexao.prepareStatement(ContatoSQL.OBTER_TODOS_POR_USUARIO);
        psTodosPorUsuario.setInt(1, usuarioId);
        psTodosPorUsuario.setInt(2, limit);
        psTodosPorUsuario.setInt(3, offset);
        return obterGenerico(psTodosPorUsuario);
    }

    @Override
    public List<Contato> obterTodosPorTipoEUsuario(ETipoContato tipo, int usuarioId) {
        return null;
    }

    /**
     * Substitui os '?' do PS pelos valores dos atributos da objeto.
     *
     * @param ps     P. Statement com SQL já pronto com os '?' para serem substituídos.
     * @param objeto Objeto com os atributos para preencher o statement.
     * @return ps com os '?' substituídos, pronto para execução.
     */
    @Override
    protected PreparedStatement preencherPS(PreparedStatement ps, Contato objeto) throws SQLException {
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
    protected Contato construir(ResultSet rs)
            throws SQLException {

        return new Contato(
                rs.getInt(ContatoSQL.ID),
                rs.getString(ContatoSQL.DESCRICAO),
                rs.getInt(ContatoSQL.FK_USUARIO),
                rs.getInt(ContatoSQL.FK_TIPO_CONTATO)
        );
    }

    /**
     * Retorna uma string com query de INSERT, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para adicionar um novo objeto.
     */
    @Override
    public String obterSqlAdicionar() {
        return ContatoSQL.ADICIONAR;
    }

    /**
     * Retorna uma string com query de UPDATE, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para atualizar um objeto.
     */
    @Override
    public String obterSqlAtualizar() {
        return ContatoSQL.ATUALIZAR;
    }

    /**
     * Retorna uma string com query de DELETE, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para deletar um objeto.
     */
    @Override
    public String obterSqlExcluir() {
        return ContatoSQL.EXCLUIR;
    }

    /**
     * Retorna uma string com query de SELECT, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para buscar um objeto.
     */
    @Override
    public String obterSqlSelecionar() {
        return ContatoSQL.OBTER_POR_ID;
    }

    /**
     * Retorna uma string com query de SELECT *, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para buscar múltiplos itens.
     */
    @Override
    public String obterSqlSelecionarTodos() {
        return ContatoSQL.OBTER_TODOS;
    }

    /**
     * Define o Id de um objeto.
     *
     * @param objeto Objeto a ter seu ID atualizado.
     * @param id     Id a ser inserido no objeto.
     */
    @Override
    public void definirId(Contato objeto, int id) {
        objeto.setId(id);
    }
}

