package Testes;

import Infraestrutura.Util.Persistencia;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        Connection conexao = Persistencia.getPersistencia().getConexao();
        PreparedStatement ps = conexao.prepareStatement(
                "SELECT * FROM estado WHERE sigla ILIKE ?;");
        ps.setString(1, "M%");
        ResultSet rs = Persistencia.getPersistencia().executeQuery(ps);

        while (rs.next()){
            System.out.println(rs.getString("sigla"));
        }

        conexao.close();
    }
}
