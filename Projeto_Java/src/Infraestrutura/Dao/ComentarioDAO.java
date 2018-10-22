package Infraestrutura.Dao;

import Dominio.Entidades.Comentario;
import Dominio.Interfaces.IComentarioRepositorio;
import Infraestrutura.Interfaces.IGenericDAO;
import Infraestrutura.Util.Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComentarioDAO implements IComentarioRepositorio, IGenericDAO<Comentario> {

    //Nome das cols. da tabela 'Avaliacao', facilita correções e centraliza os nomes;
    private final String TABELA = "comentario";
    private final String ID = "id";
    private final String COMENTARIO = "comentario";
    private final String FK_AVALIACAO = "fk_avaliacao";

    private Persistencia persistencia = Persistencia.getPersistencia();
    private Connection conexao = Persistencia.getPersistencia().getConexao();

    public ComentarioDAO()
            throws SQLException, ClassNotFoundException {}

    @Override
    public Comentario construir(ResultSet rs)
            throws SQLException {

        if (rs == null) return null;

        return new Comentario(
                rs.getInt(this.ID),
                rs.getString(this.COMENTARIO),
                rs.getInt(this.FK_AVALIACAO));
    }

    @Override
    public List<Comentario> extrairTodos(ResultSet rs)
            throws SQLException {

        List<Comentario> comentarios = new ArrayList<Comentario>();

        while (rs.next()) comentarios.add(this.construir(rs));

        return comentarios;
    }

    @Override
    public Comentario obterPorAvaliacao(int avaliacaoId)
            throws SQLException {

        PreparedStatement ps = this.conexao.prepareStatement(
                "SELECT * FROM "+TABELA+" c "
                + "INNER JOIN avaliacao a ON c."+FK_AVALIACAO+" = a.id "
                + "WHERE a.id = ?;");

        ps.setInt(1, avaliacaoId);
        ResultSet rs = persistencia.executarSelecao(ps);

        return rs.next() ? construir(rs) : null;
    }

    @Override
    public List<Comentario> obterTodosPorUsuario(int idUsuario)
            throws SQLException {

        PreparedStatement ps = this.conexao.prepareStatement(
                "SELECT * FROM "+TABELA+" WHERE fk_usuario = ?;");
        ps.setInt(1, idUsuario);

        return this.extrairTodos(this.persistencia.executarSelecao(ps));
    }

    @Override
    //TODO criar VIEW para o SQL;
    public List<Comentario> obterTodosPorServico(int idServico)
            throws SQLException {

        PreparedStatement ps = this.conexao.prepareStatement(
                "SELECT * FROM comentario c "
                + "INNER JOIN avaliacao a ON a.id = c.fk_avaliacao "
                + "INNER JOIN servico s ON s.id = a.fk_servico "
                + "WHERE s.id = ? ORDER BY 1;");

        ps.setInt(1, idServico);

        return this.extrairTodos(this.persistencia.executarSelecao(ps));
    }

    @Override
    public int adicionar(Comentario comentario) throws SQLException {

        PreparedStatement ps = this.conexao.prepareStatement(
                "INSERT INTO "+TABELA+"("+COMENTARIO+", "+FK_AVALIACAO+
                        ") VALUE(?, ?);");
        ps.setString(1, comentario.getComentario());
        ps.setInt(2, comentario.getFkAvalicao());
        persistencia.executarAtualizacao(ps);
        comentario.setId(Persistencia.getIdAtCreate(ps));

        return comentario.getId();
    }

    @Override
    public void atualizar(Comentario comentario) throws SQLException {

        PreparedStatement ps = this.conexao.prepareStatement(
                "UPDATE "+TABELA+" SET "+COMENTARIO+" = ? WHERE id = ?;"
        );

        ps.setString(1, comentario.getComentario());
        ps.setInt(2, comentario.getId());

        persistencia.executarAtualizacao(ps);
    }

    @Override
    public void excluirPorId(int id) throws SQLException {
        PreparedStatement ps = this.conexao.prepareStatement(
                "DELETE FROM "+TABELA+" WHERE id = ?;");
        ps.setInt(1, id);
        persistencia.executarAtualizacao(ps);
    }

    @Override
    public Comentario obterPorId(int id) throws SQLException {
        PreparedStatement ps = this.conexao.prepareStatement(
                "SELECT * FROM "+TABELA+" WHERE id = ?;");
        ps.setInt(1, id);
        ResultSet rs = persistencia.executarSelecao(ps);

        return rs.next() ? construir(rs) : null;
    }

    @Override
    public List<Comentario> obterTodos() throws SQLException {
        PreparedStatement ps = this.conexao.prepareStatement(
                "SELECT * FROM "+TABELA+";");
        return this.extrairTodos(persistencia.executarSelecao(ps));
    }
}
