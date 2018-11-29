import Infraestrutura.Hibernate.DAO.EnderecoDAOHibernate;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        long startTime = System.currentTimeMillis();


        EnderecoDAOHibernate daoh = new EnderecoDAOHibernate();
        System.out.println(daoh.obterTodosPorUsuario(1));
//        daoh.obterTodosPorUsuario(1, null, null)
//                .forEach(System.out::println);


        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("TEMPO de EXECUÇÃO:\t" + elapsedTime / 1000.00 + " sec.");
    }

}
