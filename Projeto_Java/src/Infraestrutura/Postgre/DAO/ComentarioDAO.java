package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.Comentario;
import Dominio.Interfaces.IComentarioRepositorio;
import Infraestrutura.Enum.ETab;
import Infraestrutura.Postgre.Util.Persistencia;
import Infraestrutura.Postgre.Util.SQLProdutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ComentarioDAO extends AGenericDAO<Comentario> implements IComentarioRepositorio{

    public static final String ID = ETab.COMENTARIO.get() + ".id";
    public static final String COMENTARIO = ETab.COMENTARIO.get() + ".comentario";
    public static final String FK_AVALIACAO = ETab.COMENTARIO.get() + ".fk_avaliacao";

    public static final List<String> COLUNAS = Arrays.asList(COMENTARIO, FK_AVALIACAO);

    private Persistencia persistencia = Persistencia.get();
    private Connection conexao = persistencia.getConexao();

    private PreparedStatement psTodos;
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

    /**
     * Substitui os '?' do PS pelos valores dos atributos da entidade.
     * Substitui os valores para operações de INSERT e UPDATE apenas.
     * @param ps     P. Statement com SQL já pronto com os '?' para serem substituídos.
     * @param objeto Objeto com os atributos para preencher o statement.
     * @return ps com os '?' substituídos, pronto para execução.
     */
    @Override
    protected PreparedStatement preencherPS(PreparedStatement ps, Comentario objeto)
            throws SQLException {

        ps.setString(1, objeto.getComentario());
        ps.setInt(2, objeto.getFkAvalicao());

        if (objeto.getId() > 0) {
            ps.setInt(COLUNAS.size() + 1, objeto.getId());
        }

        return ps;
    }

    /**
     * Retorna uma string com query de INSERT, com '?' p/ ser substuído.
     * @return String com comando SQL para adicionar um novo objeto.
     */
    @Override
    protected String obterSqlAdicionar() {
        return GenericSQL.adicionar(ETab.COMENTARIO, COLUNAS);
    }

    /**
     * Retorna uma string com query de UPDATE, com '?' p/ ser substuído.
     * @return String com comando SQL para atualizar um objeto.
     */
    @Override
    protected String obterSqlAtualizar() {
        return GenericSQL.atualizar(ETab.COMENTARIO, COLUNAS, ID);
    }

    /**
     * Retorna uma string com query de DELETE, com '?' p/ ser substuído.
     * @return String com comando SQL para deletar um objeto.
     */
    @Override
    protected String obterSqlExcluir() {
        return GenericSQL.excluirPorId(ETab.COMENTARIO, ID);
    }

    /**
     * Retorna uma string com query de SELECT, com '?' p/ ser substuído.
     * @return String com comando SQL para buscar um objeto.
     */
    @Override
    protected String obterSqlSelecionar() {
        return GenericSQL.obterPorId(ETab.COMENTARIO, COLUNAS, ID);
    }

    /**Define o Id de um objeto.
     * @param objeto Objeto a ter seu ID atualizado.
     * @param id     Id a ser inserido no objeto.
     */
    @Override
    protected void definirId(Comentario objeto, int id) {
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
                rs.getInt(ID),
                rs.getString(COMENTARIO),
                rs.getInt(FK_AVALIACAO)
        );
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
    public List<Comentario> obterTodos(Integer limit, Integer offset) throws SQLException {
        SQLProdutor sqlProd = new SQLProdutor();
        sqlProd.select(ID, COMENTARIO, FK_AVALIACAO).from(ETab.COMENTARIO.get());
        sqlProd.orderBy(1).desc().limit(limit).offset(offset);
        psTodos = conexao.prepareStatement(sqlProd.toString());
        return obterGenerico(psTodos);
    }

    @Override
    public Comentario obterPorAvaliacao(int avaliacaoId) throws SQLException {
        SQLProdutor sqlProd = new SQLProdutor();
        sqlProd.select(ID, COMENTARIO, FK_AVALIACAO).from(ETab.COMENTARIO.get());
        psPorAvaliacao = conexao.prepareStatement(sqlProd.toString());
        List<Comentario> coments = obterGenerico(psPorAvaliacao);
        return coments != null && coments.size() > 0 ? coments.get(0) : null;
    }

    @Override
    public List<Comentario> obterTodosPorUsuario(int usuarioId, Integer limit, Integer offset) throws SQLException {
        SQLProdutor sqlProd = new SQLProdutor();

        //SELECT comentario.id, comentario.comentario, comentario.fk_avaliacao FROM comentario
        sqlProd.select(ID, COMENTARIO, FK_AVALIACAO).from(ETab.COMENTARIO.get());

        //INNER JOIN avaliacao ON comentario.fk_avaliacao = avaliacao.id;
        sqlProd.innerJoin(ETab.AVALIACAO.get()).on(FK_AVALIACAO, AvaliacaoDAO.ID);

        //WHERE avaliacao.fk_usuario = ? LIMIT limit OFFSET offset;
        sqlProd.where(AvaliacaoDAO.FK_USUARIO).eq().orderBy(1).desc().limit(limit).offset(offset);
        psTodosPorUsuario = conexao.prepareStatement(sqlProd.toString());
        psTodosPorUsuario.setInt(1, usuarioId);
        return obterGenerico(psTodosPorUsuario);
    }

    @Override
    public List<Comentario> obterTodosPorServico(int servicoId, Integer limit, Integer offset) throws SQLException {

        SQLProdutor sqlProd = new SQLProdutor();

        //SELECT comentario.id, comentario.comentario, comentario.fk_avaliacao FROM comentario
        sqlProd.select(ID, COMENTARIO, FK_AVALIACAO).from(ETab.COMENTARIO.get());

        //INNER JOIN avaliacao ON comentario.fk_avaliacao = avaliacao.id;
        sqlProd.innerJoin(ETab.AVALIACAO.get()).on(FK_AVALIACAO, AvaliacaoDAO.ID);

        //WHERE avaliacao.fk_usuario = ? LIMIT limit OFFSET offset;
        sqlProd.where(AvaliacaoDAO.FK_SERVICO).eq().orderBy(1).desc().limit(limit).offset(offset);
        psTodosPorServico = conexao.prepareStatement(sqlProd.toString());
        psTodosPorServico.setInt(1, servicoId);
        return obterGenerico(psTodosPorServico);
    }
}
