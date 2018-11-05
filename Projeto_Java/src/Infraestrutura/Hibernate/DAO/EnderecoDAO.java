package Infraestrutura.Hibernate.DAO;

import Dominio.Entidades.Endereco;
import Dominio.Enum.EEstado;
import Dominio.Interfaces.IEnderecoRepositorio;
import Infraestrutura.Enum.ETab;
import Infraestrutura.Postgre.DAO.AGenericDAO;
import Infraestrutura.Postgre.DAO.EnderecoSQL;
import Infraestrutura.Postgre.Util.Function;
import Infraestrutura.Postgre.Util.Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EnderecoDAO extends AGenericDAO<Endereco> implements IEnderecoRepositorio {

    private Persistencia persistencia = Persistencia.get();
    private Connection conexao = persistencia.getConexao();
    private PreparedStatement psTodosPorBairro = null;
    private PreparedStatement psTodosPorCidade = null;
    private PreparedStatement psTodosPorEstado = null;
    private PreparedStatement psTodosPorCep = null;


    /**
     * Retorna uma string com query de INSERT, com '?' p/ ser substuído.
     * @return String com comando SQL para adicionar um novo objeto.
     */
    @Override
    public String obterSqlAdicionar() {
        return null;
    }

    /**
     * Retorna uma string com query de UPDATE, com '?' p/ ser substuído.
     * @return String com comando SQL para atualizar um objeto.
     */
    @Override
    public String obterSqlAtualizar() {
        return null;
    }

    /**
     * Retorna uma string com query de DELETE, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para deletar um objeto.
     */
    @Override
    public String obterSqlExcluir() {
        return EnderecoSQL.EXCLUIR;
    }

    /**
     * Retorna uma string com query de SELECT, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para buscar um objeto.
     */
    @Override
    public String obterSqlSelecionar() {
        return EnderecoSQL.OBTER_POR_ID;
    }

    /**
     * Retorna uma string com query de SELECT *, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para buscar múltiplos itens.
     */
    @Override
    public String obterSqlSelecionarTodos() {
        return EnderecoSQL.OBTER_TODOS;
    }

    /**
     * Define o Id de um objeto.
     *
     * @param objeto Objeto a ter seu ID atualizado.
     * @param id     Id a ser inserido no objeto.
     */
    @Override
    public void definirId(Endereco objeto, int id) {
        objeto.setId(id);
    }

    @Override
    public Endereco adicionar(Endereco entidade) throws SQLException {
        return Function.insertEndereco(entidade, persistencia);
    }

    @Override
    public void atualizar(Endereco entidade) throws SQLException {
        Function.insertEndereco(entidade, persistencia);
    }

    private List<Endereco> obterGenerico(PreparedStatement ps)
            throws SQLException {

        List<Endereco> enderecos = null;

        try {
            enderecos = extrairTodos(persistencia.executarSelecao(ps));
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            if (ps != null) ps.close();
        }

        return enderecos;
    }

    @Override
    public List<Endereco> obterTodosPorBairro(String bairro)
            throws SQLException {
        psTodosPorBairro = conexao.prepareStatement(EnderecoSQL.OBTER_TODOS_POR_BAIRRO);
        psTodosPorBairro.setString(1, "%" + bairro + "%");
        return obterGenerico(psTodosPorBairro);
    }

    @Override
    public List<Endereco> obterTodosPorCidade(String cidade)
            throws SQLException {
        psTodosPorCidade = conexao.prepareStatement(EnderecoSQL.OBTER_TODOS_POR_CIDADE);
        psTodosPorCidade.setString(1, "%" + cidade + "%");
        return obterGenerico(psTodosPorCidade);
    }

    @Override
    public List<Endereco> obterTodosPorEstado(EEstado estado)
            throws SQLException {
        psTodosPorEstado = conexao.prepareStatement(EnderecoSQL.OBTER_TODOS_POR_ESTADO);
        psTodosPorEstado.setString(1, estado.toString());
        return obterGenerico(psTodosPorEstado);
    }

    @Override
    public List<Endereco> obterTodosPorCep(int cep)
            throws SQLException {
        psTodosPorCep = conexao.prepareStatement(EnderecoSQL.OBTER_TODOS_POR_CEP);
        psTodosPorCep.setInt(1, cep);
        return obterGenerico(psTodosPorCep);
    }


    @Override
    protected ETab obterNomeTabela() {
        return ETab.ENDERECO;
    }

    /**
     * Retorna uma lista com o nome de cada coluna da tabela COM EXCEÇÃO do ID.
     *
     * @return Lista com os nomes das colunas.
     */
    @Override
    protected List<String> obterNomeColunas() {
        return EnderecoSQL.COLUNAS;
    }

    /**
     * Substitui os '?' do PS pelos valores dos atributos da entidade.
     *
     * @param ps     P. Statement com SQL já pronto com os '?' para serem substituídos.
     * @param objeto Objeto com os atributos para preencher o statement.
     * @return ps com os '?' substituídos, inclusíve o da cláusula WHERE;
     */
    @Override
    protected PreparedStatement preencherPS(PreparedStatement ps, Endereco objeto)
            throws SQLException {

        ps.setString(EnderecoSQL.COLUNAS.indexOf(EnderecoSQL.BAIRRO) + 1, objeto.getBairro());
        ps.setString(EnderecoSQL.COLUNAS.indexOf(EnderecoSQL.CIDADE) + 1, objeto.getCidade());
        ps.setString(EnderecoSQL.COLUNAS.indexOf(EnderecoSQL.ESTADO) + 1, objeto.getEstado());
        ps.setInt(EnderecoSQL.COLUNAS.indexOf(EnderecoSQL.CEP) + 1, objeto.getCep().getCep());
        ps.setInt(EnderecoSQL.COLUNAS.indexOf(EnderecoSQL.FK_USUARIO) + 1, objeto.getFkUsuario());

        return ps;
    }

    /**
     * Monta e retorna o objeto a partir de um resultSet.
     *
     * @param rs ResultSet retornado de uma consulta já executada.
     * @return Objeto montado a partir dos resultados da consulta.
     */
    @Override
    protected Endereco construir(ResultSet rs)
            throws SQLException {
        return new Endereco(
                rs.getInt(EnderecoSQL.ID),
                rs.getString(EnderecoSQL.BAIRRO),
                rs.getString(EnderecoSQL.CIDADE),
                rs.getString(EnderecoSQL.ESTADO),
                rs.getInt(EnderecoSQL.CEP),
                rs.getInt(EnderecoSQL.FK_USUARIO)
        );
    }
}
