package Infraestrutura.Interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IGenericDAO<T> {

    public T construir(ResultSet rs) throws SQLException;
    public List<T> extrairTodos(ResultSet r) throws SQLException;
}
