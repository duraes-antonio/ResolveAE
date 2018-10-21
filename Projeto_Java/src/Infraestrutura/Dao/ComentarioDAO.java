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

    public ComentarioDAO() throws SQLException, ClassNotFoundException {}

    @Override
    public Comentario construir(ResultSet rs) throws SQLException {
        return new Comentario(
                rs.getInt(this.ID),
                rs.getString(this.COMENTARIO)
        );
    }

    @Override
    public List<Comentario> extrairTodos(ResultSet rs) throws SQLException {

        List<Comentario> comentarios = new ArrayList<Comentario>();

        while (rs.next()) comentarios.add(this.construir(rs));

        return comentarios;
    }

    @Override
    public List<Comentario> obterTodosPorUsuario(int idUsuario)
            throws SQLException {

        PreparedStatement ps = this.conexao.prepareStatement(
                String.format(
                        "SELECT %s, %s FROM %s WHERE fk_usuario = ?;",
                        this.ID, this.COMENTARIO, this.TABELA));
        ps.setInt(1, idUsuario);

        return this.extrairTodos(this.persistencia.executarSelecao(ps));
    }

    @Override
    //TODO criar VIEW para o SQL;
    public List<Comentario> obterTodosPorServico(int idServico)
            throws SQLException {

        PreparedStatement ps = this.conexao.prepareStatement(
                "SELECT c.id, c.comentario FROM comentario c "
                + "INNER JOIN avaliacao a ON a.id = c.fk_avaliacao "
                + "INNER JOIN servico s ON s.id = a.fk_servico "
                + "WHERE s.id = ? ORDER BY 1;");

        ps.setInt(1, idServico);

        return this.extrairTodos(this.persistencia.executarSelecao(ps));
    }

    @Override
    public void adicionar(Comentario comentario) throws SQLException {

//        PreparedStatement ps = this.conexao.prepareStatement(
//                "INSERT INTO %s();");
//        ps.setInt(1, idUsuario);
//
//        return this.extrairTodos(this.persistencia.executarSelecao(ps));
    }

    @Override
    public void atualizar(Comentario entidade) {

    }

    @Override
    public void excluirPorId(int id) {

    }

    @Override
    public Comentario obterPorId(int id) {
        return null;
    }

    @Override
    public List<Comentario> obterTodos() {
        return null;
    }
}
