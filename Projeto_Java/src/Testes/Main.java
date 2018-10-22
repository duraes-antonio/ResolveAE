package Testes;

import Dominio.Interfaces.IComentarioRepositorio;
import Infraestrutura.Dao.ComentarioDAO;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        long startTime = System.currentTimeMillis();

        IComentarioRepositorio comentarioDAO = new ComentarioDAO();
        System.out.println(comentarioDAO.obterPorId(999999).toString());

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("TEMPO de EXECUÇÃO:\t" + elapsedTime/1000.00 + " sec.");
    }
}
