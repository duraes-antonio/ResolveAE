package Dominio.Interfaces;

import Dominio.Entidades.Usuario;
import Dominio.Enum.EEstado;
import Dominio.Enum.ETipoContato;
import Dominio.Enum.ETipoInfoProfissional;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;
import java.util.List;

public interface IUsuarioRepositorio extends IBaseRepositorio<Usuario> {

    Usuario login(String email, String senha) throws SQLException, FailedLoginException;
    List<Usuario> obterTodosPorNome(String nome, Integer limit, Integer offset) throws SQLException;
    List<Usuario> obterTodosPorEmail(String email, Integer limit, Integer offset) throws SQLException;
    List<Usuario> obterTodosPorContato(String contato, Integer limit, Integer offset) throws SQLException;
    List<Usuario> obterTodosPorContato(ETipoContato tipoContato, String contato, Integer limit, Integer offset) throws SQLException;
    List<Usuario> obterTodosPorCep(int cep, Integer limit, Integer offset) throws SQLException;
    List<Usuario> obterTodosPorBairro(String bairro, Integer limit, Integer offset) throws SQLException;
    List<Usuario> obterTodosPorCidade(String cidade, Integer limit, Integer offset) throws SQLException;
    List<Usuario> obterTodosPorEstado(EEstado estado, Integer limit, Integer offset) throws SQLException;
    List<Usuario> obterTodosPorInfoProfissional(ETipoInfoProfissional tipoInfo, Integer limit, Integer offset) throws SQLException;
}
