package Dominio.Interfaces;

import java.util.List;

public interface IRepositorio<T> {

    void Adicionar(T entidade);
    void Atualizar(T entidade);
    void ExcluirPorId(int id);
    T ObterPorId(int id);
    List<T> ObterTodos();
}
