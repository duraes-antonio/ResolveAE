package Dominio.Interfaces;

import Dominio.Entidades.Usuario;
import Dominio.Enum.EEstado;
import Dominio.Enum.ETipoContato;
import Dominio.Enum.ETipoInfoProfissional;

import java.util.List;

public interface IUsuarioRepositorio extends IRepositorio<Usuario> {

    List<Usuario> ObterTodosPorNome(String nome);
    List<Usuario> ObterTodosPorEmail(String email);
    List<Usuario> ObterTodosPorContato(ETipoContato tipoContato, String contato);
    List<Usuario> ObterTodosPorCep(int cep);
    List<Usuario> ObterTodosPorBairro(String bairro);
    List<Usuario> ObterTodosPorCidade(String cidade);
    List<Usuario> ObterTodosPorEstado(EEstado estado);
    List<Usuario> ObterTodosPorInfoProfissional(ETipoInfoProfissional tipoInfo);
}
