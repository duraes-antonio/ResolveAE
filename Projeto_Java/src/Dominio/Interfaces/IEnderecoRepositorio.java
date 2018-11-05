package Dominio.Interfaces;

import Dominio.Entidades.Endereco;
import Dominio.Enum.EEstado;

import java.sql.SQLException;
import java.util.List;

public interface IEnderecoRepositorio extends IRepositorioBase<Endereco> {

    List<Endereco> obterTodosPorBairro(String bairro) throws SQLException;
    List<Endereco> obterTodosPorCidade(String cidade) throws SQLException;
    List<Endereco> obterTodosPorEstado(EEstado estado) throws SQLException;
    List<Endereco> obterTodosPorCep(int cep) throws SQLException;
}
