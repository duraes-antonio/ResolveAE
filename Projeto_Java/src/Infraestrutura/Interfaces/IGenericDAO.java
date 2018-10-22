package Infraestrutura.Interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IGenericDAO<T> {

    T construir(ResultSet rs) throws SQLException;
    List<T> extrairTodos(ResultSet r) throws SQLException;
}
