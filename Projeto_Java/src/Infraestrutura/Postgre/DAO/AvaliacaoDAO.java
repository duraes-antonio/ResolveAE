package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.Avaliacao;
import Dominio.Entidades.Comentario;
import Dominio.Interfaces.IAvaliacaoRepositorio;
import Infraestrutura.Postgre.Util.Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AvaliacaoDAO extends AGenericDAO<Avaliacao>
        implements IAvaliacaoRepositorio {


    private Persistencia persistencia = Persistencia.get();
    private Connection conexao = persistencia.getConexao();
    private PreparedStatement psTodasPorUsuario = null;
    private PreparedStatement psTodasPorServico = null;

    private ComentarioDAO comentarioDAO = new ComentarioDAO();

    private List<Avaliacao> obterGenerico(PreparedStatement ps)
            throws SQLException {

        List<Avaliacao> avaliacoes = null;

        try {
            avaliacoes = extrairTodos(persistencia.executarSelecao(ps));
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            if (ps != null) ps.close();
        }

        return avaliacoes;
    }

    @Override
    public List<Avaliacao> obterTodasPorUsuario(int usuarioId)
            throws SQLException {
        psTodasPorUsuario = conexao.prepareStatement(AvaliacaoSQL.OBTER_TODOS_POR_USUARIO);
        psTodasPorUsuario.setInt(1, usuarioId);
        return obterGenerico(psTodasPorUsuario);
    }

    @Override
    public List<Avaliacao> obterTodasPorServico(int servicoId) throws SQLException {
        psTodasPorServico = conexao.prepareStatement(AvaliacaoSQL.OBTER_TODOS_POR_SERVICO);
        psTodasPorServico.setInt(1, servicoId);
        return obterGenerico(psTodasPorServico);
    }

    /**Substitui os '?' do PS pelos valores dos atributos da objeto.
     * @param ps P. Statement com SQL já pronto com os '?' para serem substituídos.
     * @param objeto Objeto com os atributos para preencher o statement.
     * @return ps com os '?' substituídos, pronto para execução.
     */
    @Override
    protected PreparedStatement preencherPS(PreparedStatement ps, Avaliacao objeto)
            throws SQLException {
        ps.setInt(AvaliacaoSQL.COLUNAS.indexOf(AvaliacaoSQL.NOTA) + 1, objeto.getNota());
        ps.setInt(AvaliacaoSQL.COLUNAS.indexOf(AvaliacaoSQL.FK_USUARIO) + 1, objeto.getFkUsuario());
        ps.setInt(AvaliacaoSQL.COLUNAS.indexOf(AvaliacaoSQL.FK_SERVICO) + 1, objeto.getFkServico());
        return null;
    }

    /**Monta e retorna o objeto a partir de um resultSet.
     * Não faz validações sobre o resultSet.
     * @param rs ResultSet retornado de uma consulta já executada.
     * @return Objeto montado a partir dos resultados da consulta.
     */
    @Override
    protected Avaliacao construir(ResultSet rs) throws SQLException {
        Comentario comentario = new Comentario(
                rs.getInt(ComentarioSQL.ID),
                rs.getString(ComentarioSQL.COMENTARIO),
                rs.getInt(AvaliacaoSQL.ID));

        return new Avaliacao(
                rs.getInt(AvaliacaoSQL.ID),
                rs.getInt(AvaliacaoSQL.NOTA),
                rs.getInt(AvaliacaoSQL.FK_USUARIO),
                rs.getInt(AvaliacaoSQL.FK_SERVICO),
                comentario.getComentario() != null ? comentario : null);
    }


    /**
     * Retorna uma string com query de INSERT, com '?' p/ ser substuído.
     * @return String com comando SQL para adicionar um novo objeto.
     */
    @Override
    public String obterSqlAdicionar() {
        return AvaliacaoSQL.ADICIONAR;
    }

    /**
     * Retorna uma string com query de UPDATE, com '?' p/ ser substuído.
     * @return String com comando SQL para atualizar um objeto.
     */
    @Override
    public String obterSqlAtualizar() {
        return AvaliacaoSQL.ATUALIZAR;
    }

    /**
     * Retorna uma string com query de DELETE, com '?' p/ ser substuído.
     * @return String com comando SQL para deletar um objeto.
     */
    @Override
    public String obterSqlExcluir() {
        return AvaliacaoSQL.EXCLUIR;
    }

    /**
     * Retorna uma string com query de SELECT, com '?' p/ ser substuído.
     * @return String com comando SQL para buscar um objeto.
     */
    @Override
    public String obterSqlSelecionar() {
        return AvaliacaoSQL.OBTER_POR_ID;
    }

    /**
     * Retorna uma string com query de SELECT *, com '?' p/ ser substuído.
     * @return String com comando SQL para buscar múltiplos itens.
     */
    @Override
    public String obterSqlSelecionarTodos() {
        return AvaliacaoSQL.OBTER_TODOS;
    }

    /**Define o Id de um objeto.
     * @param objeto Objeto a ter seu ID atualizado.
     * @param id     Id a ser inserido no objeto.
     */
    @Override
    public void definirId(Avaliacao objeto, int id) {
        objeto.setId(id);
    }
}