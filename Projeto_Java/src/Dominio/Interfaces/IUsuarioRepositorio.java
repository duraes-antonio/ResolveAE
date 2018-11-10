package Dominio.Interfaces;

import Dominio.Entidades.Usuario;
import Dominio.Enum.EEstado;
import Dominio.Enum.ETipoContato;
import Dominio.Enum.ETipoInfoProfissional;

import java.util.List;

public interface IUsuarioRepositorio extends IBaseRepositorio<Usuario> {

    List<Usuario> obterTodosPorNome(String nome, Integer limit, Integer offset);
    List<Usuario> obterTodosPorEmail(String email, Integer limit, Integer offset);
    List<Usuario> obterTodosPorContato(ETipoContato tipoContato, String contato, Integer limit, Integer offset);
    List<Usuario> obterTodosPorCep(int cep, Integer limit, Integer offset);
    List<Usuario> obterTodosPorBairro(String bairro, Integer limit, Integer offset);
    List<Usuario> obterTodosPorCidade(String cidade, Integer limit, Integer offset);
    List<Usuario> obterTodosPorEstado(EEstado estado, Integer limit, Integer offset);
    List<Usuario> obterTodosPorInfoProfissional(ETipoInfoProfissional tipoInfo, Integer limit, Integer offset);
}
