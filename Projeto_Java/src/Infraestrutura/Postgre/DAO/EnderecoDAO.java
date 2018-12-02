package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.Endereco;
import Dominio.Enum.EEstado;
import Dominio.Interfaces.IEnderecoRepositorio;
import Infraestrutura.Enum.ETab;
import Infraestrutura.Postgre.Util.Function;
import Infraestrutura.Postgre.Util.Persistencia;
import Infraestrutura.Postgre.Util.SQLProdutor;
import Infraestrutura.Postgre.Util.View;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class EnderecoDAO extends AGenericDAO<Endereco> implements IEnderecoRepositorio {

    //Nome das colunas da VIEW_ENDERECO (nomes usados para montar as querys);
    public static final String ID = ETab.ENDERECO.get() + ".id";
    public static final String BAIRRO = ETab.ENDERECO.get() + ".bairro";
    public static final String CIDADE = ETab.ENDERECO.get() + ".cidade";
    public static final String ESTADO = ETab.ENDERECO.get() + ".estado";
    public static final String CEP = ETab.ENDERECO.get() + ".cep";
    public static final String FK_USUARIO = ETab.ENDERECO.get() + ".fk_usuario";

    public static final List<String> COLUNAS = Arrays.asList(BAIRRO, CIDADE, ESTADO, CEP, FK_USUARIO);


    private Persistencia persistencia = Persistencia.get();
    private Connection conexao = persistencia.getConexao();
    private PreparedStatement psTodos = null;
    private PreparedStatement psTodosPorBairro = null;
    private PreparedStatement psTodosPorCidade = null;
    private PreparedStatement psTodosPorEstado = null;
    private PreparedStatement psTodosPorCep = null;
    private PreparedStatement psTodosPorUsuario = null;

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
        return Function.salvarEndereco(entidade, persistencia);
    }

    @Override
    public void atualizar(Endereco entidade) throws SQLException {
        Function.salvarEndereco(entidade, persistencia);
    }


    private List<Endereco> obterGenerico(PreparedStatement ps) throws SQLException {

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

    /**
     * Busca e retorna todos objetos de um determinado tipo.
     *
     * @param limit  Quantidade de resultados a ser retornada.
     * @param offset Quantidade de resultados a pular.
     * @return Lista com todos objetos encontrados.
     * @throws SQLException
     */
    @Override
    public List<Endereco> obterTodos(Integer limit, Integer offset) throws SQLException {

        SQLProdutor sqlProd = new SQLProdutor();
        sqlProd.selectAll().from(View.VIEW_ENDERECO.name());
        sqlProd.limit(limit).offset(offset);

        psTodos = conexao.prepareStatement(sqlProd.toString());
        return obterGenerico(psTodos);
    }

    @Override
    public List<Endereco> obterTodosPorBairro(String bairro, Integer limit, Integer offset) throws SQLException {

        SQLProdutor sqlProd = new SQLProdutor();

        //SELECT * FROM VIEW_ENDERECO WHERE bairro ILIKE ?;
        sqlProd.selectAll().from(View.VIEW_ENDERECO.name()).where(BAIRRO).ilike().limit(limit).offset(offset);

        psTodosPorBairro = conexao.prepareStatement(sqlProd.toString());
        psTodosPorBairro.setString(1, "%" + bairro + "%");
        return obterGenerico(psTodosPorBairro);
    }

    @Override
    public List<Endereco> obterTodosPorCidade(String cidade, Integer limit, Integer offset) throws SQLException {

        SQLProdutor sqlProd = new SQLProdutor();

        //SELECT * FROM VIEW_ENDERECO WHERE cidade ILIKE ?;
        sqlProd.selectAll().from(View.VIEW_ENDERECO.name()).where(CIDADE).ilike().limit(limit).offset(offset);

        psTodosPorCidade = conexao.prepareStatement(sqlProd.toString());
        psTodosPorCidade.setString(1, "%" + cidade + "%");
        return obterGenerico(psTodosPorCidade);
    }

    @Override
    public List<Endereco> obterTodosPorEstado(EEstado estado, Integer limit, Integer offset) throws SQLException {

        SQLProdutor sqlProd = new SQLProdutor();

        //SELECT * FROM VIEW_ENDERECO WHERE estado ILIKE ?;
        sqlProd.selectAll().from(View.VIEW_ENDERECO.name()).where(ESTADO).ilike().limit(limit).offset(offset);

        psTodosPorEstado = conexao.prepareStatement(sqlProd.toString());
        psTodosPorEstado.setString(1, estado.toString());
        return obterGenerico(psTodosPorEstado);
    }

    @Override
    public List<Endereco> obterTodosPorCep(int cep, Integer limit, Integer offset) throws SQLException {

        SQLProdutor sqlProd = new SQLProdutor();

        //SELECT * FROM VIEW_ENDERECO WHERE estado ILIKE ?;
        sqlProd.selectAll().from(View.VIEW_ENDERECO.name()).where(CEP).eq().limit(limit).offset(offset);

        psTodosPorCep = conexao.prepareStatement(sqlProd.toString());
        psTodosPorCep.setInt(1, cep);
        return obterGenerico(psTodosPorCep);
    }

    @Override
    public Endereco obterTodosPorUsuario(int fkUsuario) throws SQLException {

        SQLProdutor sqlProd = new SQLProdutor();

        //SELECT * FROM VIEW_ENDERECO WHERE fkUsuario = ?;
        sqlProd.selectAll().from(View.VIEW_ENDERECO.name()).where(FK_USUARIO).eq();

        psTodosPorUsuario = conexao.prepareStatement(sqlProd.toString());
        psTodosPorUsuario.setInt(1, fkUsuario);
        List<Endereco> enderecos = obterGenerico(psTodosPorUsuario);

        return  (enderecos != null && enderecos.size() > 0) ? enderecos.get(0) : null;
    }


    /**
     * Substitui os '?' do PS pelos valores dos atributos da entidade.
     *
     * @param ps     P. Statement com SQL já pronto com os '?' para serem substituídos.
     * @param objeto Objeto com os atributos para preencher o statement.
     * @return ps com os '?' substituídos, inclusíve o da cláusula WHERE;
     */
    @Override
    protected PreparedStatement preencherPS(PreparedStatement ps, Endereco objeto) throws SQLException {

        ps.setString(COLUNAS.indexOf(BAIRRO) + 1, objeto.getBairro());
        ps.setString(COLUNAS.indexOf(CIDADE) + 1, objeto.getCidade());
        ps.setString(COLUNAS.indexOf(ESTADO) + 1, objeto.getEstado());
        ps.setInt(COLUNAS.indexOf(CEP) + 1, objeto.getCep().getCep());
        ps.setInt(COLUNAS.indexOf(FK_USUARIO) + 1, objeto.getFkUsuario());

        return ps;
    }

    /**
     * Monta e retorna o objeto a partir de um resultSet.
     *
     * @param rs ResultSet retornado de uma consulta já executada.
     * @return Objeto montado a partir dos resultados da consulta.
     */
    @Override
    protected Endereco construir(ResultSet rs) throws SQLException {
        return new Endereco(
                rs.getInt(ID),
                rs.getString(BAIRRO),
                rs.getString(CIDADE),
                rs.getString(ESTADO),
                rs.getInt(CEP),
                rs.getInt(FK_USUARIO));
    }

    /**
     * Retorna uma string com query de INSERT, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para adicionar um novo objeto.
     */
    @Override
    public String obterSqlAdicionar() {
        return null;
    }

    /**
     * Retorna uma string com query de UPDATE, com '?' p/ ser substuído.
     *
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
        return GenericSQL.excluirPorId(ETab.ENDERECO, ID);
    }

    /**
     * Retorna uma string com query de SELECT, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para buscar um objeto.
     */
    @Override
    public String obterSqlSelecionar() {
        SQLProdutor sqlProdutor = new SQLProdutor();
        sqlProdutor.selectAll().from(View.VIEW_ENDERECO.name()).where(ID).eq();
        return sqlProdutor.toString();
    }


}
