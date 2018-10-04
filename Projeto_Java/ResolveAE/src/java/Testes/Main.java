package Testes;

import Util.Persistencia;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String sql = "CREATE TABLE teste (idade INT, nome VARCHAR(10));";

        Persistencia persistencia = Persistencia.getPersistencia();

        persistencia.executeQuery(sql);
    }
}
