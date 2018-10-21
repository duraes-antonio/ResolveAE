package Dominio.Interfaces;

import java.sql.SQLException;
import java.util.List;

public interface IRepositorioBase<T> {

    void adicionar(T entidade) throws SQLException;
    void atualizar(T entidade);
    void excluirPorId(int id);
    T obterPorId(int id);
    List<T> obterTodos();
}
