package Dominio.Interfaces;

import Dominio.Entidades.Endereco;
import Dominio.Enum.EEstado;

import java.sql.SQLException;
import java.util.List;

public interface IEnderecoRepositorio extends IBaseRepositorio<Endereco> {

    List<Endereco> obterTodosPorBairro(String bairro, Integer limit, Integer offset) throws SQLException;
    List<Endereco> obterTodosPorCidade(String cidade, Integer limit, Integer offset) throws SQLException;
    List<Endereco> obterTodosPorEstado(EEstado estado, Integer limit, Integer offset) throws SQLException;
    List<Endereco> obterTodosPorCep(int cep, Integer limit, Integer offset) throws SQLException;
    Endereco obterTodosPorUsuario(int fkUsuario) throws SQLException;
}
