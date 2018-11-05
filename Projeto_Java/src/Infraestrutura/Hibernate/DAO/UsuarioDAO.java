package Infraestrutura.Hibernate.DAO;//package Infraestrutura.Postgre.DAO;
//
//import Dominio.Entidades.Usuario;
//import Dominio.Enum.EEstado;
//import Dominio.Enum.ETipoContato;
//import Dominio.Enum.ETipoInfoProfissional;
//import Dominio.Interfaces.IUsuarioRepositorio;
//import Infraestrutura.Enum.ETab;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Arrays;
//import java.util.List;
//
//public class UsuarioDAO extends AGenericDAO<Usuario>
//        implements IUsuarioRepositorio {
//
//    public static final String ID = ETab.USUARIO.get() + ".id";
//    public static final String EMAIL = ETab.USUARIO.get() + ".email";
//    public static final String NOME = ETab.USUARIO.get() + ".nome";
//    public static final String SENHA = ETab.USUARIO.get() + ".senha";
//    public static final String SOBRE = ETab.USUARIO.get() + ".sobre";
//
//    private final List<String> COLUNAS = Arrays.asList(EMAIL, NOME, SENHA, SOBRE);
//
//
//    @Override
//    public List<Usuario> obterTodosPorNome(String nome) {
//        return null;
//    }
//
//    @Override
//    public List<Usuario> obterTodosPorEmail(String email) {
//        return null;
//    }
//
//    @Override
//    public List<Usuario> obterTodosPorContato(ETipoContato tipoContato, String contato) {
//        return null;
//    }
//
//    @Override
//    public List<Usuario> obterTodosPorCep(int cep) {
//        return null;
//    }
//
//    @Override
//    public List<Usuario> obterTodosPorBairro(String bairro) {
//        return null;
//    }
//
//    @Override
//    public List<Usuario> obterTodosPorCidade(String cidade) {
//        return null;
//    }
//
//    @Override
//    public List<Usuario> obterTodosPorEstado(EEstado estado) {
//        return null;
//    }
//
//    @Override
//    public List<Usuario> obterTodosPorInfoProfissional(ETipoInfoProfissional tipoInfo) {
//        return null;
//    }
//
//    /**
//     * Retorna o nome da Tabela responsável pela classe.
//     *
//     * @return Nome da tabela.
//     */
//    @Override
//    protected ETab obterNomeTabela() {
//        return null;
//    }
//
//    /**
//     * Retorna uma lista com o nome de cada coluna da tabela COM EXCEÇÃO do ID.
//     *
//     * @return Lista com os nomes das colunas.
//     */
//    @Override
//    protected List<String> obterNomeColunas() {
//        return null;
//    }
//
//    /**
//     * Substitui os '?' do PS pelos valores dos atributos da entidade.
//     *
//     * @param ps     P. Statement com SQL já pronto com os '?' para serem substituídos.
//     * @param objeto Objeto com os atributos para preencher o statement.
//     * @return ps com os '?' substituídos, inclusíve o da cláusula WHERE;
//     */
//    @Override
//    protected PreparedStatement preencherPS(PreparedStatement ps, Usuario objeto) throws SQLException {
//        return null;
//    }
//
//    /**
//     * Monta e retorna o objeto a partir de um resultSet.
//     *
//     * @param rs ResultSet retornado de uma consulta já executada.
//     * @return Objeto montado a partir dos resultados da consulta.
//     */
//    @Override
//    protected Usuario construir(ResultSet rs) throws SQLException {
//        return new Usuario()
//    }
//}
