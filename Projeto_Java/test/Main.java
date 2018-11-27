import Infraestrutura.Hibernate.DAO.ServicoDAOH;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        long startTime = System.currentTimeMillis();


        ServicoDAOH daoh = new ServicoDAOH();
        daoh.obterTodosPorUsuario(1, null, null)
                .forEach(System.out::println);


        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("TEMPO de EXECUÇÃO:\t" + elapsedTime / 1000.00 + " sec.");
    }

}
