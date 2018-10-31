package Infraestrutura.PostgresDAO;

import Dominio.Entidades.Avaliacao;
import Dominio.Interfaces.IAvaliacaoRepositorio;
import Infraestrutura.Enum.ETab;
import Infraestrutura.UtilPostgres.SQLProdutor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class AvaliacaoDAO extends AGenericDAO<Avaliacao>
        implements IAvaliacaoRepositorio {

    public static final String ID = ETab.AVALIACAO.get() + ".id";
    public static final String NOTA = ETab.AVALIACAO.get() + ".nota";
    public static final String FK_USUARIO = ETab.AVALIACAO.get() + ".fk_usuario";
    public static final String FK_SERVICO = ETab.AVALIACAO.get() + ".fk_servico";
    private final List<String> COLUNAS = Arrays.asList(NOTA, FK_USUARIO, FK_SERVICO);

    private ComentarioDAO comentarioDAO = new ComentarioDAO();

    //TODO FINALIZAR - AGUARDANDO CLASSE usuarioDAO
    @Override
    public List<Avaliacao> obterTodasPorUsuario(int usuarioId) throws SQLException {

        SQLProdutor sqlProdutor = new SQLProdutor();

        /*Monta:
        SELECT * FROM avaliacao a INNER JOIN usuario u
        ON a.fk_usuario = u.id WHERE u.id = ?;*/

        sqlProdutor.selectAll().from(ETab.AVALIACAO.get())
                .innerJoin(ETab.USUARIO.get())
                .on(FK_USUARIO, null)
                .where(AvaliacaoDAO.ID).eq();

        System.out.println(sqlProdutor.toString());

        //Prepara o Statement e substitui o ? pelo id da avaliacao;
        PreparedStatement ps = conexao.prepareStatement(sqlProdutor.toString());
        ps.setInt(1, usuarioId);

        return extrairTodos(persistencia.executarSelecao(ps));
    }

    //TODO IMPLEMENTAR
    @Override
    public List<Avaliacao> obterTodasPorServico(int servicoId) throws SQLException {
        return null;
    }


    /**
     * Retorna o nome da Tabela responsável pela classe.
     *
     * @return Nome da tabela.
     */
    @Override
    protected ETab obterNomeTabela() {
        return ETab.AVALIACAO;
    }

    /**
     * Retorna uma lista com o nome de cada coluna da tabela.
     *
     * @return Lista com os nomes das colunas.
     */
    @Override
    protected List<String> obterNomeColunas() {
        return COLUNAS;
    }

    /**
     * Substitui os '?' do PS pelos valores dos atributos da entidade.
     * @param ps     P. Statement com SQL já pronto com os '?' para serem substituídos.
     * @param objeto Objeto com os atributos para preencher o statement.
     * @return ps com os '?' substituídos, inclusíve o da cláusula WHERE;
     */
    @Override
    protected PreparedStatement preencherPS(PreparedStatement ps, Avaliacao objeto)
            throws SQLException {
        ps.setInt(COLUNAS.indexOf(NOTA) + 1, objeto.getNota());
        ps.setInt(COLUNAS.indexOf(FK_USUARIO) + 1, objeto.getFkUsuario());
        ps.setInt(COLUNAS.indexOf(FK_SERVICO) + 1, objeto.getFkServico());
        return null;
    }

    /**
     * Monta e retorna o objeto a partir de um resultSet.
     *
     * @param rs ResultSet retornado de uma consulta já executada.
     * @return Objeto montado a partir dos resultados da consulta.
     */
    @Override
    protected Avaliacao construir(ResultSet rs) throws SQLException {

        if (!rs.isBeforeFirst()) return null;

        rs.next();

        return new Avaliacao(
                rs.getInt(ID),
                rs.getInt(NOTA),
                rs.getInt(FK_USUARIO),
                rs.getInt(FK_SERVICO),
                comentarioDAO.obterPorAvaliacao(rs.getInt(ID))
        );
    }
}