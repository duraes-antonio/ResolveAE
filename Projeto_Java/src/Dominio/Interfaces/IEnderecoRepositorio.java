package Dominio.Interfaces;

import Dominio.Entidades.Endereco;
import Dominio.Enum.EEstado;

import java.util.List;

public interface IEnderecoRepositorio extends IRepositorioBase<Endereco> {

    List<Endereco> obterTodosPorBairro(String bairro);
    List<Endereco> obterTodosPorCidade(String cidade);
    List<Endereco> obterTodosPorEstado(EEstado estado);
    List<Endereco> obterTodosPorCep(int cep);
}
