package Infraestrutura.Dao;

import Dominio.Entidades.Avaliacao;
import Dominio.Interfaces.IAvaliacaoRepositorio;
import Infraestrutura.Interfaces.IGenericDAO;
import Infraestrutura.Util.Persistencia;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 20161BSI0314
 */
public class AvaliacaoDAO implements IAvaliacaoRepositorio, IGenericDAO<Avaliacao> {

    //Nome das cols. da tabela 'Avaliacao', facilita correções e centraliza os nomes;
    private final String ID = "id";
    private final String NOTA = "nota";
    private final String COMENTARIO = "comentario";
    private final String FK_USUARIO = "fk_usuario";
    private final String FK_SERVICO = "fk_servico";

    private Persistencia persistencia = Persistencia.getPersistencia();
    private Connection conexao = Persistencia.getPersistencia().getConexao();

    public AvaliacaoDAO() throws SQLException, ClassNotFoundException {}

    @Override
    public Avaliacao construir(ResultSet rs) throws SQLException {
        return new Avaliacao(
                rs.getInt(this.ID),
                rs.getInt(this.NOTA),
                rs.getString(this.COMENTARIO)
        );
    }

    @Override
    public List<Avaliacao> extrairTodos(ResultSet rs) throws SQLException {
        List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();

        while (rs.next()) avaliacoes.add(this.construir(rs));

        return avaliacoes;
    }

    @Override
    public List<Avaliacao> obterTodasPorUsuario(int idUsuario)
            throws SQLException {

        PreparedStatement ps = this.conexao.prepareStatement(
                "SELECT * FROM avaliacao WHERE fk_usuario = ?;");
        ps.setInt(1, idUsuario);

        return this.extrairTodos(this.persistencia.executarSelecao(ps));
    }

    @Override
    public List<Avaliacao> obterTodasPorServico(int idServico)
            throws SQLException {

        PreparedStatement ps = this.conexao.prepareStatement(
                "SELECT * FROM avaliacao WHERE "+this.FK_SERVICO+" = ?;");
        ps.setInt(1, idServico);

        return this.extrairTodos(this.persistencia.executarSelecao(ps));
    }


    public void adicionar(Avaliacao avaliacao) {
        PreparedStatement ps = this.conexao.prepareStatement(
                "INSERT INTO avaliacao (Nota,Comentario,IdUsuario,IdServicoPrestado)"
                + "VALUES"
                + "("+avaliacao.getNota()+","+avaliacao.getComentario()+","+avaliacao.getIdUsuario()+","+avaliacao.getIdServicoPrestado()+")";

        PreparedStatement ps = this.conexao.prepareStatement(
                "SELECT * FROM avaliacao WHERE "+this.FK_SERVICO+" = ?;");
        ps.setInt(1, idServico);

//        return this.extrairTodos(this.persistencia.executarSelecao(ps));

    }

    @Override
    public void atualizar(Avaliacao entidade) {

    }

    @Override
    public void excluirPorId(int id) {

    }

    @Override
    public Avaliacao obterPorId(int id) {
        return null;
    }

    @Override
    public List<Avaliacao> obterTodos() {
        return null;
    }

}
