package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.Comentario;
import Dominio.Interfaces.IComentarioRepositorio;
import Infraestrutura.Postgre.Util.Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ComentarioDAO extends AGenericDAO<Comentario>
        implements IComentarioRepositorio{

    private Persistencia persistencia = Persistencia.get();
    private Connection conexao = persistencia.getConexao();
    private PreparedStatement psPorAvaliacao;
    private PreparedStatement psTodosPorUsuario;
    private PreparedStatement psTodosPorServico;

    private List<Comentario> obterGenerico(PreparedStatement ps)
            throws SQLException {

        List<Comentario> comentarios = null;

        try {
            comentarios = extrairTodos(persistencia.executarSelecao(ps));
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            if (ps != null) ps.close();
        }

        return comentarios;
    }

    @Override
    public Comentario obterPorAvaliacao(int avaliacaoId)
            throws SQLException {
        psPorAvaliacao = conexao.prepareStatement(ComentarioSQL.OBTER_POR_AVALIACAO);
        psPorAvaliacao.setInt(1, avaliacaoId);
        return obterObjeto(persistencia.executarSelecao(psPorAvaliacao));
    }

    @Override
    public List<Comentario> obterTodosPorUsuario(int usuarioId)
            throws SQLException {
        psTodosPorUsuario = conexao.prepareStatement(ComentarioSQL.OBTER_TODOS_POR_USUARIO);
        psTodosPorUsuario.setInt(1, usuarioId);
        return obterGenerico(psTodosPorUsuario);
    }

    //TODO terminar
    @Override
    public List<Comentario> obterTodosPorServico(int servicoId)
            throws SQLException {
        psTodosPorServico = conexao.prepareStatement(ComentarioSQL.OBTER_TODOS_POR_SERVICO);
        psTodosPorServico.setInt(1, servicoId);
        return obterGenerico(psTodosPorServico);
    }

    /**
     * Substitui os '?' do PS pelos valores dos atributos da entidade.
     *
     * @param ps     P. Statement com SQL já pronto com os '?' para serem substituídos.
     * @param objeto Objeto com os atributos para preencher o statement.
     * @return ps com os '?' substituídos, pronto para execução.
     */
    @Override
    protected PreparedStatement preencherPS(PreparedStatement ps, Comentario objeto)
            throws SQLException {
        ps.setString(ComentarioSQL.COLUNAS.indexOf(ComentarioSQL.COMENTARIO) + 1, objeto.getComentario());
        ps.setInt(ComentarioSQL.COLUNAS.indexOf(ComentarioSQL.FK_AVALIACAO) + 1, objeto.getFkAvalicao());
        return ps;
    }

    /**
     * Retorna uma string com query de INSERT, com '?' p/ ser substuído.
     * @return String com comando SQL para adicionar um novo objeto.
     */
    @Override
    public String obterSqlAdicionar() {
        return ComentarioSQL.ADICIONAR;
    }

    /**
     * Retorna uma string com query de UPDATE, com '?' p/ ser substuído.
     * @return String com comando SQL para atualizar um objeto.
     */
    @Override
    public String obterSqlAtualizar() {
        return ComentarioSQL.ADICIONAR;
    }

    /**
     * Retorna uma string com query de DELETE, com '?' p/ ser substuído.
     * @return String com comando SQL para deletar um objeto.
     */
    @Override
    public String obterSqlExcluir() {
        return ComentarioSQL.EXCLUIR;
    }

    /**
     * Retorna uma string com query de SELECT, com '?' p/ ser substuído.
     * @return String com comando SQL para buscar um objeto.
     */
    @Override
    public String obterSqlSelecionar() {
        return ComentarioSQL.OBTER_POR_ID;
    }

    /**
     * Retorna uma string com query de SELECT *, com '?' p/ ser substuído.
     * @return String com comando SQL para buscar múltiplos itens.
     */
    @Override
    public String obterSqlSelecionarTodos() {
        return ComentarioSQL.OBTER_TODOS;
    }

    /**Define o Id de um objeto.
     * @param objeto Objeto a ter seu ID atualizado.
     * @param id     Id a ser inserido no objeto.
     */
    @Override
    public void definirId(Comentario objeto, int id) {
        objeto.setId(id);
    }

    /**Monta e retorna o objeto a partir de um resultSet.
     * Não faz validações sobre o resultSet.
     * @param rs ResultSet retornado de uma consulta já executada.
     * @return Objeto montado a partir dos resultados da consulta.
     */
    @Override
    protected Comentario construir(ResultSet rs)
            throws SQLException {

        return new Comentario(
                rs.getInt(ComentarioSQL.ID),
                rs.getString(ComentarioSQL.COMENTARIO),
                rs.getInt(ComentarioSQL.FK_AVALIACAO)
        );
    }
}
