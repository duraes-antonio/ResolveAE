package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.*;
import Dominio.Enum.EEstado;
import Dominio.Enum.ETipoContato;
import Dominio.Enum.ETipoInfoProfissional;
import Dominio.Interfaces.IUsuarioRepositorio;
import Infraestrutura.Enum.ETab;
import Infraestrutura.Postgre.Util.Persistencia;
import Infraestrutura.Postgre.Util.SQLProdutor;
import Infraestrutura.Postgre.Util.View;

import javax.security.auth.login.FailedLoginException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UsuarioDAO extends AGenericDAO<Usuario>
        implements IUsuarioRepositorio {

    public static final String ID = ETab.USUARIO.get() + ".id";
    public static final String EMAIL = ETab.USUARIO.get() + ".email";
    public static final String NOME = ETab.USUARIO.get() + ".nome";
    public static final String SENHA = ETab.USUARIO.get() + ".senha";
    public static final String SOBRE = ETab.USUARIO.get() + ".sobre";

    private final List<String> COLUNAS = Arrays.asList(NOME, EMAIL, SENHA, SOBRE);

    private Persistencia persistencia = Persistencia.get();
    private Connection conexao = persistencia.getConexao();

    private AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
    private ContatoDAO contatoDAO = new ContatoDAO();
    private EnderecoDAO enderecoDAO = new EnderecoDAO();
    private HorarioDAO horarioDAO = new HorarioDAO();
    private InfoProfissionalDAO infoProDAO = new InfoProfissionalDAO();

    private PreparedStatement psLogin;
    private PreparedStatement psTodos;
    private PreparedStatement psTodosPorNome;
    private PreparedStatement psTodosPorEmail;
    private PreparedStatement psTodosPorContato;
    private PreparedStatement psTodosPorTipoContato;
    private PreparedStatement psTodosPorTipoInfoPro;
    private PreparedStatement psTodosPorCep;
    private PreparedStatement psTodosPorBairro;
    private PreparedStatement psTodosPorCidade;
    private PreparedStatement psTodosPorEstado;


    private SQLProdutor obterSQlInicial() {

        SQLProdutor sqlProd = new SQLProdutor();

        //SELECT * FROM view_usuario;
        sqlProd.selectAll().from(View.VIEW_USUARIO.name());

        return sqlProd;
    }

    public Usuario login(String email, String senha)
            throws SQLException, FailedLoginException, IllegalArgumentException {

        SQLProdutor sqlProd = new SQLProdutor();
        sqlProd.selectAll().from(View.VIEW_USUARIO.toString());
        sqlProd.where(EMAIL).eq();

        psLogin = conexao.prepareStatement(sqlProd.toString());
        psLogin.setString(1, email);

        List<Usuario> usuarios = obterVarios(psLogin);

        if (usuarios == null || usuarios.size() == 0) {
            throw new FailedLoginException(
                    "Não foram encontradas pessoas com esse login." +
                    " Verifique o email digitado e tente novamente.");
        }

        else if (!usuarios.get(0).getSenha().equals(senha)) {
            throw new IllegalArgumentException(
                    "Senha incorreta! Verifique a senha digitada e tente novamente."
            );
        }

        return usuarios.size() > 0 ? obterPorId(usuarios.get(0).getId()) : null;
    }

    /**Recebe um Produtor de SQL já com as condições do WHERE montadas e
     * adiciona o restante da query (inserindo ou não, as cláusulas limit e offset)*/
    private SQLProdutor obterSQLFinal(SQLProdutor sqlProd, Integer limit) {

        //WHERE "usuario.id" >= ?
        if (sqlProd.containsWhere()) {
            sqlProd.grteq();
        }

        else {
            sqlProd.where(ID).grteq();
        }

        //WHERE "usuario.id" >= ? AND "usuario.id" <= ? ORDER BY "usuario.id";
        if (limit == null || limit > 0) {
            sqlProd.and(ID).leq();
        }


        return sqlProd;
    }

    /**Preenche o P. Statement com os valores adequados de limit e offset
     * @return Quantidade parametros inseridos;
     * */
    private int preencherPSInicial(PreparedStatement ps, Integer limit, Integer offset)
            throws SQLException {

        int qtdParam = 1;
        int offset2 = offset != null ? offset : 0;
        int limit2 = limit != null ? limit : 0;

        ps.setInt(1, offset2 + 1);

        if (limit2 > 0) {
            ps.setInt(2, limit2 + offset2);
            qtdParam++;
        }

        return qtdParam;
    }

    private List<Usuario> obterVarios(PreparedStatement ps)
            throws SQLException {

        List<Usuario> usuarios = null;

        try {
            ResultSet rs = persistencia.executarSelecao(ps);
            usuarios = extrairUsuarios(rs);
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            if (ps != null) ps.close();
        }

        return usuarios;
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
    public List<Usuario> obterTodos(Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProdutor = obterSQLFinal(obterSQlInicial(), limit);
        psTodos = conexao.prepareStatement(sqlProdutor.toString());
        preencherPSInicial(psTodos, limit, offset);
        return obterVarios(psTodos);
    }

    @Override
    public List<Usuario> obterTodosPorNome(String nome, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = obterSQLFinal(obterSQlInicial(), limit);
        sqlProd.and(NOME).ilike();

        psTodosPorNome = conexao.prepareStatement(sqlProd.toString());
        int pos = preencherPSInicial(psTodosPorNome, limit, offset);
        psTodosPorNome.setString(pos + 1, "%" + nome + "%");

        System.out.println(psTodosPorNome);
        return obterVarios(psTodosPorNome);
    }

    @Override
    public List<Usuario> obterTodosPorEmail(String email, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = obterSQLFinal(obterSQlInicial(), limit);
        sqlProd.and(EMAIL).ilike();

        psTodosPorEmail = conexao.prepareStatement(sqlProd.toString());
        int pos = preencherPSInicial(psTodosPorEmail, limit, offset);
        psTodosPorEmail.setString(pos + 1, "%" + email + "%");

        System.out.println(psTodosPorEmail);
        return obterVarios(psTodosPorEmail);
    }

    @Override
    public List<Usuario> obterTodosPorContato(String contatoDesc, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = obterSQLFinal(obterSQlInicial(), limit);
        sqlProd.and(ContatoDAO.DESCRICAO).ilike();

        psTodosPorContato = conexao.prepareStatement(sqlProd.toString());
        int pos = preencherPSInicial(psTodosPorContato, limit, offset);
        psTodosPorContato.setString(pos + 1, "%" + contatoDesc + "%");

        System.out.println(psTodosPorContato);

        return obterVarios(psTodosPorContato);
    }

    @Override
    public List<Usuario> obterTodosPorContato(ETipoContato tipoContato, String contato, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = obterSQLFinal(obterSQlInicial(), limit);
        sqlProd.and(ContatoDAO.FK_TIPO_CONTATO).eq();
        sqlProd.and(ContatoDAO.DESCRICAO).ilike();

        psTodosPorTipoContato = conexao.prepareStatement(sqlProd.toString());
        int pos = preencherPSInicial(psTodosPorTipoContato, limit, offset);
        psTodosPorTipoContato.setInt(pos + 1, tipoContato.getId());
        psTodosPorTipoContato.setString(pos + 2, "%" + contato + "%");


        System.out.println(psTodosPorTipoContato);
        return obterVarios(psTodosPorTipoContato);
    }

    @Override
    public List<Usuario> obterTodosPorCep(int cep, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = obterSQLFinal(obterSQlInicial(), limit);
        sqlProd.and(EnderecoDAO.CEP).eq();

        psTodosPorCep = conexao.prepareStatement(sqlProd.toString());
        int pos = preencherPSInicial(psTodosPorCep, limit, offset);
        psTodosPorCep.setInt(pos + 1, cep);

        System.out.println(psTodosPorCep);
        return obterVarios(psTodosPorCep);
    }

    @Override
    public List<Usuario> obterTodosPorBairro(String bairro, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = obterSQLFinal(obterSQlInicial(), limit);
        sqlProd.and(EnderecoDAO.BAIRRO).ilike();

        psTodosPorBairro = conexao.prepareStatement(sqlProd.toString());
        int pos = preencherPSInicial(psTodosPorBairro, limit, offset);
        psTodosPorBairro.setString(pos + 1, "%" + bairro + "%");

        System.out.println(psTodosPorBairro);
        return obterVarios(psTodosPorBairro);
    }

    @Override
    public List<Usuario> obterTodosPorCidade(String cidade, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = obterSQLFinal(obterSQlInicial(), limit);
        sqlProd.and(EnderecoDAO.CIDADE).ilike();

        psTodosPorCidade = conexao.prepareStatement(sqlProd.toString());
        int pos = preencherPSInicial(psTodosPorCidade, limit, offset);
        psTodosPorCidade.setString(pos + 1, "%" + cidade + "%");

        System.out.println(psTodosPorCidade);
        return obterVarios(psTodosPorCidade);
    }

    @Override
    public List<Usuario> obterTodosPorEstado(EEstado estado, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = obterSQLFinal(obterSQlInicial(), limit);
        sqlProd.and(EnderecoDAO.ESTADO).ilike();

        psTodosPorEstado = conexao.prepareStatement(sqlProd.toString());
        int pos = preencherPSInicial(psTodosPorEstado, limit, offset);
        psTodosPorEstado.setString(pos + 1, estado.toString());

        System.out.println(psTodosPorEstado);
        return obterVarios(psTodosPorEstado);
    }

    @Override
    public List<Usuario> obterTodosPorInfoProfissional(ETipoInfoProfissional tipoInfo, Integer limit, Integer offset)
            throws SQLException {

        SQLProdutor sqlProd = obterSQLFinal(obterSQlInicial(), limit);
        sqlProd.and(InfoProfissionalDAO.FK_TIPO_INFO_PROF).eq();

        psTodosPorTipoInfoPro = conexao.prepareStatement(sqlProd.toString());
        int pos = preencherPSInicial(psTodosPorTipoInfoPro, limit, offset);
        psTodosPorTipoInfoPro.setInt(pos + 1, tipoInfo.getId());

        System.out.println(psTodosPorTipoInfoPro);
        return obterVarios(psTodosPorTipoInfoPro);
    }

    private List<Usuario> extrairUsuarios(ResultSet rs) throws SQLException {

        Usuario usuario = null;
        HashMap<Integer, Usuario> usuarios = new HashMap<>();

        HashMap<Integer, Avaliacao> avals = null;
        HashMap<Integer, Contato> contatos = null;
        Endereco endereco = null;
        HashMap<Integer, Horario> horarios = null;
        HashMap<Integer, InfoProfissional> infoPros = null;

        int idAvaliacao;
        int idContato;
        int idEndereco;
        int idHorario;
        int idInfoPro;
        int idUsuario;

        // Enquanto houver registros;
        while (rs.next()) {

            idAvaliacao = rs.getInt(AvaliacaoDAO.ID);
            idContato = rs.getInt(ContatoDAO.ID);
            idEndereco = rs.getInt(EnderecoDAO.ID);
            idHorario = rs.getInt(HorarioDAO.ID);
            idInfoPro = rs.getInt(InfoProfissionalDAO.ID);
            idUsuario = rs.getInt(ID);

            if (idUsuario > 0 && !usuarios.containsKey(idUsuario)) {

                if (usuario != null) {
                    usuario.setEndereco(endereco);
                    usuario.setAvaliacoes(new ArrayList<>(avals.values()));
                    usuario.setContatos(new ArrayList<>(contatos.values()));
                    usuario.setInfoProfissionais(new ArrayList<>(infoPros.values()));
                    usuario.setHorarios(new ArrayList<>(horarios.values()));
                }

                usuario = construirUsuario(rs);
                usuarios.put(idUsuario,usuario);

                avals = new HashMap<>();
                contatos = new HashMap<>();

                if (idEndereco > 0) {
                    endereco = enderecoDAO.construir(rs);
                }

                horarios = new HashMap<>();
                infoPros = new HashMap<>();
            }

            if (idAvaliacao > 0 && avals != null && !avals.containsKey(idAvaliacao)) {
                avals.put(idAvaliacao, avaliacaoDAO.construir(rs));
            }

            if (idContato > 0 && contatos != null && !contatos.containsKey(idContato)) {
                contatos.put(idContato, contatoDAO.construir(rs));
            }

            if (idHorario > 0 && horarios != null && !horarios.containsKey(idHorario)) {
                horarios.put(idHorario, horarioDAO.construir(rs));
            }

            if (idInfoPro > 0 && infoPros != null && !infoPros.containsKey(idInfoPro)) {
                infoPros.put(idInfoPro, infoProDAO.construir(rs));
            }
        }

        if (usuario != null) {
            usuario.setEndereco(endereco);
            usuario.setAvaliacoes(new ArrayList<>(avals.values()));
            usuario.setContatos(new ArrayList<>(contatos.values()));
            usuario.setInfoProfissionais(new ArrayList<>(infoPros.values()));
            usuario.setHorarios(new ArrayList<>(horarios.values()));
        }

        return new ArrayList<>(usuarios.values());
    }


    /**
     * Substitui os '?' do PS pelos valores dos atributos da objeto.
     * Substitui os valores para operações de INSERT e UPDATE apenas.
     *
     * @param ps     P. Statement com SQL já pronto com os '?' para serem substituídos.
     * @param objeto Objeto com os atributos para preencher o statement.
     * @return ps com os '?' substituídos, pronto para execução.
     */
    @Override
    protected PreparedStatement preencherPS(PreparedStatement ps, Usuario objeto)
            throws SQLException {

        ps.setString(1, objeto.getNome());
        ps.setString(2, objeto.getEmail());
        ps.setString(3, objeto.getSenha());
        ps.setString(4, objeto.getSobre());

        if (objeto.getId() > 0) {
            ps.setInt(COLUNAS.size() + 1, objeto.getId());
        }

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
    protected Usuario construir(ResultSet rs) throws SQLException {

        List<Avaliacao> avaliacoes;
        List<Contato> contatos;
        List<InfoProfissional> infoPros;
        Endereco endereco;

        Usuario usuario = new Usuario(
                rs.getInt(ID),
                rs.getString(NOME),
                rs.getString(EMAIL),
                null,
                null,
                rs.getString(SENHA),
                rs.getString(SOBRE));

        endereco = enderecoDAO.obterTodosPorUsuario(usuario.getId());
        avaliacoes = avaliacaoDAO.obterTodasPorUsuario(usuario.getId(), 0, 0);
        contatos = contatoDAO.obterTodosPorUsuario(usuario.getId(), 0, 0);
        infoPros = infoProDAO.obterTodosPorUsuario(usuario.getId(), 0, 0);

        usuario.setAvaliacoes(avaliacoes);
        usuario.setContatos(contatos);
        usuario.setEndereco(endereco);
        usuario.setInfoProfissionais(infoPros);

        return usuario;
    }

    /**
     * Monta e retorna o objeto a partir de um resultSet.
     * Não faz validações sobre o resultSet.
     *
     * @param rs ResultSet retornado de uma consulta já executada.
     * @return Objeto montado a partir dos resultados da consulta.
     */
    private Usuario construirUsuario(ResultSet rs) throws SQLException {

        return new Usuario(
                rs.getInt(ID),
                rs.getString(NOME),
                rs.getString(EMAIL),
                null,
                null,
                rs.getString(SENHA),
                rs.getString(SOBRE));
    }

    /**
     * Retorna uma string com query de INSERT, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para adicionar um novo objeto.
     */
    @Override
    protected String obterSqlAdicionar() {
        return GenericSQL.adicionar(ETab.USUARIO, COLUNAS);
    }

    /**
     * Retorna uma string com query de UPDATE, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para atualizar um objeto.
     */
    @Override
    protected String obterSqlAtualizar() {
        return GenericSQL.atualizar(ETab.USUARIO, COLUNAS, ID);
    }

    /**
     * Retorna uma string com query de DELETE, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para deletar um objeto.
     */
    @Override
    protected String obterSqlExcluir() {
        return GenericSQL.excluirPorId(ETab.USUARIO, ID);
    }

    /**
     * Retorna uma string com query de SELECT, com '?' p/ ser substuído.
     *
     * @return String com comando SQL para buscar um objeto.
     */
    @Override
    protected String obterSqlSelecionar() {
        return GenericSQL.obterPorId(ETab.USUARIO, COLUNAS, ID);
    }

    /**
     * Define o Id de um objeto.
     *
     * @param objeto Objeto a ter seu ID atualizado.
     * @param id     Id a ser inserido no objeto.
     */
    @Override
    protected void definirId(Usuario objeto, int id) {
        objeto.setId(id);
    }
}
