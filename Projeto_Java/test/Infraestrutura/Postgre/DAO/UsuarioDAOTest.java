package Infraestrutura.Postgre.DAO;

import Dominio.Entidades.*;
import Dominio.Enum.EEstado;
import Dominio.Enum.ETipoContato;
import Dominio.Enum.ETipoInfoProfissional;
import Infraestrutura.Postgre.Util.Persistencia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.security.auth.login.FailedLoginException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

class UsuarioDAOTest {

    private Connection conexao;
    private Persistencia persistencia = Persistencia.get();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private ContatoDAO contatoDAO = new ContatoDAO();
    private InfoProfissionalDAO infoDAO = new InfoProfissionalDAO();
    private EnderecoDAO enderecoDAO = new EnderecoDAO();
    private AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
    private ContratoDAO contratoDAO = new ContratoDAO();
    private ServicoDAO servicoDAO = new ServicoDAO();
    private ServicoSubtipoServicoDAO sssDAO = new ServicoSubtipoServicoDAO();
    private HorarioDAO horarioDAO = new HorarioDAO();


    private int id = 1;

    @BeforeEach
    void setUp() {
        conexao = persistencia.getConexao();
    }

    @AfterEach
    void tearDown() throws SQLException {
        conexao.close();
    }

    @Test
    void obterTodos() throws SQLException {
        List<Usuario> usuarios = usuarioDAO.obterTodos(10000, 0);
        System.out.println(usuarios.size());
        System.out.println(usuarios.get(usuarios.size() - 1));
        assert usuarios.size() > 0;
    }

    @Test
    void login() throws FailedLoginException, SQLException {

        Usuario usuario = null;

        try {
            usuario = usuarioDAO.login("ligio-afdps-2015@r7.com", "senhainvalida");
        }

        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            assert true;
        }

        try {
            usuario = usuarioDAO.login("usuario_inexistente", "senha");
        }

        catch (FailedLoginException e) {
            System.out.println(e.getMessage());
            assert true;
        }

        usuario = usuarioDAO.login("ligio-afdps-2015@r7.com", "senha");
        System.out.println(usuario);
    }

    @Test
    void obterTodosPorNome() throws SQLException {
        List<Usuario> usuarios = usuarioDAO.obterTodosPorNome("Max", 1000, 0);
        System.out.println(usuarios.size());
        System.out.println(usuarios.get(usuarios.size() - 1));
        assert usuarios.size() > 0;
    }

    @Test
    void obterTodosPorEmail() throws SQLException {
        List<Usuario> usuarios = usuarioDAO.obterTodosPorEmail("@gmail.com", 0, 0);
        System.out.println(usuarios.size());
        System.out.println(usuarios.get(usuarios.size() - 1));
        assert usuarios.size() > 0;
    }

    @Test
    void obterTodosPorContato() throws SQLException {
        List<Usuario> usuarios = usuarioDAO.obterTodosPorContato(ETipoContato.FACEBOOK, "Joao", 0, 0);
        System.out.println(usuarios.size());
        System.out.println(usuarios.get(usuarios.size() - 1));
        assert usuarios.size() > 0;
    }

    @Test
    void obterTodosPorCep() throws SQLException {
        List<Usuario> usuarios = usuarioDAO.obterTodosPorCep(29161699, 0, 0);
        System.out.println(usuarios.size());
        System.out.println(usuarios.get(usuarios.size() - 1));
        assert usuarios.size() > 0;
    }

    @Test
    void obterTodosPorBairro() throws SQLException {
        List<Usuario> usuarios = usuarioDAO.obterTodosPorBairro("Carapina", 0, 0);
        System.out.println(usuarios.size());
        System.out.println(usuarios.get(usuarios.size() - 1));
        assert usuarios.size() > 0;
    }

    @Test
    void obterTodosPorCidade() throws SQLException {
        List<Usuario> usuarios = usuarioDAO.obterTodosPorCidade("Viana", 0, 0);
        System.out.println(usuarios.size());
        System.out.println(usuarios.get(usuarios.size() - 1));
        assert usuarios.size() > 0;
    }

    @Test
    void obterTodosPorEstado() throws SQLException {
        List<Usuario> usuarios = usuarioDAO.obterTodosPorEstado(EEstado.ES, 0, 0);
        System.out.println(usuarios.size());
        System.out.println(usuarios.get(usuarios.size() - 1));
        assert usuarios.size() > 0;
    }

    @Test
    void obterTodosPorInfoProfissional() throws SQLException {
        List<Usuario> usuarios = usuarioDAO.obterTodosPorInfoProfissional(
                ETipoInfoProfissional.GRADUACAO, 0, 0);
        System.out.println(usuarios.size());
        System.out.println(usuarios.get(usuarios.size() - 1));
        assert usuarios.size() > 0;
    }

    @Test
    void adicionar() throws SQLException {

        try {
            conexao.setAutoCommit(false);

            Usuario usuario = new Usuario("João", "joao@truemail.com", "123456", "...");
            usuarioDAO.adicionar(usuario);

            System.out.println(usuario);

            Usuario usuario2 = usuarioDAO.obterPorId(usuario.getId());

            System.out.println(usuario2);

            assert usuario.toString().trim().equals(usuario2.toString().trim());
        }

        finally {
            conexao.rollback();
        }
    }

    @Test
    void atualizar() {
    }

    @Test
    void excluirPorId() throws SQLException {

        try {
            conexao.setAutoCommit(false);

            Usuario usuario = usuarioDAO.obterPorId(id);

            System.out.println(usuario);

            for (Contato c : usuario.getContatos()) {
                contatoDAO.excluirPorId(c.getId());
            }

            if (usuario.getEndereco() != null) {
                enderecoDAO.excluirPorId(usuario.getEndereco().getId());
            }

            for (InfoProfissional info : usuario.getInfoProfissionais()) {
                infoDAO.excluirPorId(info.getId());
            }

            for (Avaliacao aval : usuario.getAvaliacoes()) {
                avaliacaoDAO.excluirPorId(aval.getId());
            }

            for (Servico servico : servicoDAO.obterTodosPorUsuario(usuario.getId(), 0, 0)) {

                for (ServicoSubtipoServico sss : sssDAO.obterPorServico(servico.getId(), 0, 0)) {
                    sssDAO.excluirPorId(sss.getId());
                }

                servicoDAO.excluirPorId(servico.getId());
            }

            for (Contrato contrato : contratoDAO.obterTodosPorUsuario(usuario.getId(), 0, 0)) {
                contratoDAO.excluirPorId(contrato.getId());
            }

            for (Contrato contrato : contratoDAO.obterTodosPorUsuario(usuario.getId(), 0, 0)) {
                contratoDAO.excluirPorId(contrato.getId());
            }

            for (Horario horario : horarioDAO.obterTodosPorUsuario(usuario.getId(), 0, 0)) {
                horarioDAO.excluirPorId(horario.getId());
            }

            usuarioDAO.excluirPorId(usuario.getId());

            Usuario usuario2 = usuarioDAO.obterPorId(usuario.getId());
            System.out.println(usuario2);

            assert usuario2 == null;
        }

        finally {
            conexao.rollback();
        }
    }

    @Test
    void obterPorId() throws SQLException {
        Usuario usuario = usuarioDAO.obterPorId(id);
        System.out.println(usuario);
        assert usuario.getId() == id;
    }
}