package Dominio.Interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IRepositorioBase<T> {

    int adicionar(T entidade) throws SQLException;
    void atualizar(T entidade) throws SQLException;
    void excluirPorId(int id) throws SQLException;
    T obterPorId(int id) throws SQLException;
    List<T> obterTodos() throws SQLException;
}
