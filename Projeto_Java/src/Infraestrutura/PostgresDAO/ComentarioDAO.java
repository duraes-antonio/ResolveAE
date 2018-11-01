package Infraestrutura.PostgresDAO;

import Dominio.Entidades.Comentario;
import Dominio.Interfaces.IComentarioRepositorio;
import Infraestrutura.Enum.ETab;
import Infraestrutura.UtilPostgres.Persistencia;
import Infraestrutura.UtilPostgres.SQLProdutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ComentarioDAO extends AGenericDAO<Comentario>
        implements IComentarioRepositorio{

    //Nome das cols. da tabela 'Avaliacao', facilita correções e centraliza os nomes;
    public static final String ID = ETab.COMENTARIO.get() + ".id";
    public static final String COMENTARIO = ETab.COMENTARIO.get() + ".comentario";
    public static final String FK_AVALIACAO = ETab.COMENTARIO.get() + ".fk_avaliacao";
    private final List<String> COLUNAS = Arrays.asList(COMENTARIO, FK_AVALIACAO);
    PreparedStatement ps;

    private Persistencia persistencia = Persistencia.get();
    private Connection conexao = persistencia.getConexao();

    @Override
    public Comentario obterPorAvaliacao(int avaliacaoId)
            throws SQLException {
        SQLProdutor sqlProdutor = new SQLProdutor();

        //Monta: SELECT * FROM comentario WHERE fk_avaliacao = ?;
        sqlProdutor.selectAll().from(ETab.COMENTARIO.get()).where(FK_AVALIACAO).eq();

        //Prepara o Statement e substitui o ? pelo id da avaliacao;
        PreparedStatement ps = conexao.prepareStatement(sqlProdutor.toString());
        ps.setInt(1, avaliacaoId);

        return construir(persistencia.executarSelecao(ps));
    }

    @Override
    public List<Comentario> obterTodosPorUsuario(int usuarioId)
            throws SQLException {

        SQLProdutor sqlProdutor = new SQLProdutor();

        /*Monta:
        SELECT * FROM comentario INNER JOIN avaliacao a
        ON c.fk_avaliacao = a.id WHERE a.id = ?;*/
        sqlProdutor.selectAll().from(ETab.COMENTARIO.get())
                .innerJoin(ETab.AVALIACAO.get())
                .on(FK_AVALIACAO, AvaliacaoDAO.ID)
                .where(AvaliacaoDAO.ID).eq();

        System.out.println(sqlProdutor.toString());

        //Prepara o Statement e substitui o ? pelo id da avaliacao;
        PreparedStatement ps = conexao.prepareStatement(sqlProdutor.toString());
        ps.setInt(1, usuarioId);

        return extrairTodos(persistencia.executarSelecao(ps));
    }

    @Override
    public List<Comentario> obterTodosPorServico(int servicoId)
            throws SQLException {
        SQLProdutor sqlProdutor = new SQLProdutor();
        sqlProdutor.selectAll().from(ETab.COMENTARIO.get()).where(FK_AVALIACAO).eq();

        ps = conexao.prepareStatement(sqlProdutor.toString());
        ps.setInt(1, servicoId);

        return null;
    }

    /**
     * Retorna o nome da Tabela responsável pela classe.
     *
     * @return Nome da tabela.
     */
    @Override
    protected ETab obterNomeTabela() {
        return ETab.COMENTARIO;
    }

    /**Retorna uma lista com o nome de cada coluna da tabela COM EXCEÇÃO do ID.
     * @return Lista com os nomes das colunas.
     */
    @Override
    protected List<String> obterNomeColunas() {
        return COLUNAS;
    }

    /**
     * Substitui os '?' do PS pelos valores dos atributos da entidade.
     *
     * @param ps     P. Statement com SQL já pronto com os '?' para serem substituídos.
     * @param objeto Objeto com os atributos para preencher o statement.
     * @return ps com os '?' substituídos, inclusíve o da cláusula WHERE;
     */
    @Override
    protected PreparedStatement preencherPS(PreparedStatement ps, Comentario objeto)
            throws SQLException {
        ps.setString(COLUNAS.indexOf(COMENTARIO) + 1, objeto.getComentario());
        ps.setInt(COLUNAS.indexOf(FK_AVALIACAO) + 1, objeto.getFkAvalicao());
        return ps;
    }

    /**
     * Monta e retorna o objeto a partir de um resultSet.
     *
     * @param rs ResultSet retornado de uma consulta já executada.
     * @return Objeto montado a partir dos resultados da consulta.
     */
    @Override
    protected Comentario construir(ResultSet rs) throws SQLException {

        if (!rs.isBeforeFirst()) return null;

        rs.next();
        return new Comentario(
                rs.getInt(ID),
                rs.getString(COMENTARIO),
                rs.getInt(FK_AVALIACAO)
        );
    }
}
