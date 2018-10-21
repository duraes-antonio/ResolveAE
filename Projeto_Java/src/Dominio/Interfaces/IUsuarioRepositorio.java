package Dominio.Interfaces;

import Dominio.Entidades.Usuario;
import Dominio.Enum.EEstado;
import Dominio.Enum.ETipoContato;
import Dominio.Enum.ETipoInfoProfissional;

import java.util.List;

public interface IUsuarioRepositorio extends IRepositorioBase<Usuario> {

    List<Usuario> obterTodosPorNome(String nome);
    List<Usuario> obterTodosPorEmail(String email);
    List<Usuario> obterTodosPorContato(ETipoContato tipoContato, String contato);
    List<Usuario> obterTodosPorCep(int cep);
    List<Usuario> obterTodosPorBairro(String bairro);
    List<Usuario> obterTodosPorCidade(String cidade);
    List<Usuario> obterTodosPorEstado(EEstado estado);
    List<Usuario> obterTodosPorInfoProfissional(ETipoInfoProfissional tipoInfo);
}
