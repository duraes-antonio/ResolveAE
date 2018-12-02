package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.Avaliacao;
import Dominio.Entidades.Comentario;
import Dominio.Interfaces.IAvaliacaoRepositorio;
import Infraestrutura.Enum.ETab;
import Infraestrutura.Postgre.Util.Persistencia;
import Infraestrutura.Postgre.Util.SQLProdutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class AvaliacaoDAO extends AGenericDAO<Avaliacao> implements IAvaliacaoRepositorio {

    //Nome das colunas da tabela AVALIACAO (nomes usados para montar as querys);
    public static final String ID = ETab.AVALIACAO.get() + ".id";
    public static final String NOTA = ETab.AVALIACAO.get() + ".nota";
    public static final String FK_USUARIO = ETab.AVALIACAO.get() + ".fk_usuario";
    public static final String FK_SERVICO = ETab.AVALIACAO.get() + ".fk_servico";

    public static final List<String> COLUNAS = Arrays.asList(NOTA, FK_USUARIO, FK_SERVICO);

    private Persistencia persistencia = Persistencia.get();
    private Connection conexao = persistencia.getConexao();
    private PreparedStatement psTodos = null;
    private PreparedStatement psTodosPorUsuario = null;
    private PreparedStatement psTodosPorServico = null;

    private List<Avaliacao> obterVarios(PreparedStatement ps)
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

    /**
     * Busca e retorna todos objetos de um determinado tipo.
     *
     * @param limit  Quantidade de resultados a ser retornada.
     * @param offset Quantidade de resultados a pular.
     * @return Lista com todos objetos encontrados.
     * @throws SQLException
     */
    @Override
    public List<Avaliacao> obterTodos(Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = new SQLProdutor();
        sqlProd.select(ID, NOTA, FK_USUARIO, FK_SERVICO, ComentarioDAO.ID, ComentarioDAO.COMENTARIO);
        sqlProd.from(ETab.AVALIACAO.get()).innerJoin(ETab.COMENTARIO.get());
        sqlProd.on(ID, ComentarioDAO.FK_AVALIACAO).limit(limit).offset(offset);

        psTodos = conexao.prepareStatement(sqlProd.toString());
        return obterVarios(psTodos);
    }

    @Override
    public List<Avaliacao> obterTodasPorUsuario(int usuarioId, Integer limit, Integer offset)
            throws SQLException {
        SQLProdutor sqlProd = new SQLProdutor();
        sqlProd.select(ID, NOTA, FK_USUARIO, FK_SERVICO, ComentarioDAO.ID, ComentarioDAO.COMENTARIO);
        sqlProd.from(ETab.AVALIACAO.get()).innerJoin(ETab.COMENTARIO.get());
        sqlProd.on(ID, ComentarioDAO.FK_AVALIACAO).where(FK_USUARIO).eq();
        sqlProd.limit(limit).offset(offset);

        psTodosPorUsuario = conexao.prepareStatement(sqlProd.toString());
        psTodosPorUsuario.setInt(1, usuarioId);
        return obterVarios(psTodosPorUsuario);
    }

    @Override
    public List<Avaliacao> obterTodasPorServico(int servicoId, Integer limit, Integer offset) throws SQLException {
        SQLProdutor sqlProd = new SQLProdutor();
        sqlProd.select(ID, NOTA, FK_USUARIO, FK_SERVICO, ComentarioDAO.ID, ComentarioDAO.COMENTARIO);
        sqlProd.from(ETab.AVALIACAO.get()).innerJoin(ETab.COMENTARIO.get());
        sqlProd.on(ID, ComentarioDAO.FK_AVALIACAO).where(FK_SERVICO).eq();
        sqlProd.limit(limit).offset(offset);

        psTodosPorServico = conexao.prepareStatement(sqlProd.toString());
        psTodosPorServico.setInt(1, servicoId);
        return obterVarios(psTodosPorServico);
    }


    /**
     * Substitui os '?' do PS pelos valores dos atributos da objeto.
     *
     * @param ps     P. Statement com SQL já pronto com os '?' para serem substituídos.
     * @param objeto Objeto com os atributos para preencher o statement.
     * @return ps com os '?' substituídos, pronto para execução.
     */
    protected PreparedStatement preencherPS(PreparedStatement ps, Avaliacao objeto)
            throws SQLException {

        ps.setInt(COLUNAS.indexOf(NOTA) + 1, objeto.getNota());
        ps.setInt(COLUNAS.indexOf(FK_USUARIO) + 1, objeto.getFkUsuario());
        ps.setInt(COLUNAS.indexOf(FK_SERVICO) + 1, objeto.getFkServico());

        //Se o objeto já tiver ID, adicione-o;
        if (objeto.getId() > 0) ps.setInt(COLUNAS.size() + 1, objeto.getId());

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
    protected Avaliacao construir(ResultSet rs) throws SQLException {
        Comentario comentario = new Comentario(
                rs.getInt(ComentarioDAO.ID),
                rs.getString(ComentarioDAO.COMENTARIO),
                rs.getInt(ID));

        return new Avaliacao(
                rs.getInt(ID),
                rs.getInt(NOTA),
                rs.getInt(FK_USUARIO),
                rs.getInt(FK_SERVICO),
                comentario.getComentario() != null ? comentario : null);
    }


    /**
     * Retorna uma string com query de INSERT, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para adicionar um novo objeto.
     */
    @Override
    protected String obterSqlAdicionar() {
        return GenericSQL.adicionar(ETab.AVALIACAO, COLUNAS);
    }

    /**
     * Retorna uma string com query de UPDATE, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para atualizar um objeto.
     */
    @Override
    protected String obterSqlAtualizar() {
        return GenericSQL.atualizar(ETab.AVALIACAO, COLUNAS, ID);
    }

    /**
     * Retorna uma string com query de DELETE, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para deletar um objeto.
     */
    @Override
    protected String obterSqlExcluir() {
        return GenericSQL.excluirPorId(ETab.AVALIACAO, ID);
    }

    /**
     * Retorna uma string com query de SELECT, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para buscar um objeto.
     */
    @Override
    protected String obterSqlSelecionar() {
        SQLProdutor sqlProd = new SQLProdutor();

        /*SELECT avaliacao.id, avaliacao.nota, avaliacao.fk_usuario,
                 avaliacao.fk_servico, comentario.id, comentario.comentario*/
        sqlProd.select(ID, NOTA, FK_USUARIO, FK_SERVICO, ComentarioDAO.ID, ComentarioDAO.COMENTARIO);

        //FROM avaliacao INNER JOIN comentario;
        sqlProd.from(ETab.AVALIACAO.get()).innerJoin(ETab.COMENTARIO.get());

        //ON avaliacao.id = comentario.fk_avaliacao WHERE avaliacao.id = ?;
        sqlProd.on(ID, ComentarioDAO.FK_AVALIACAO).where(ID).eq();

        return sqlProd.toString();
    }

    /**
     * Define o Id de um objeto.
     *
     * @param objeto Objeto a ter seu ID atualizado.
     * @param id     Id a ser inserido no objeto.
     */
    @Override
    public void definirId(Avaliacao objeto, int id) {
        objeto.setId(id);
    }
}