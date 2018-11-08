import Dominio.Entidades.Contato;
import Dominio.Enum.ETipoContato;
import Infraestrutura.Postgre.DAO.ContatoDAO;
import Infraestrutura.Postgre.Util.Persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        long startTime;
        boolean hard = false;
        List<Contato> lista = new ArrayList<>();


        if (true) {
            ContatoDAO contatoDAO = new ContatoDAO();
            Persistencia persistencia = Persistencia.get();
            startTime = System.currentTimeMillis();

            lista = contatoDAO.obterTodosPorTipo(ETipoContato.SKYPE, 5, null);
            System.out.println(lista.size());
            lista.forEach(System.out::println);
            persistencia.getConexao().close();
        }
//
//        if(true) {
//            EntityManager em = FabricaConexao.obterConexao();
//
//            startTime = System.currentTimeMillis();
//            for (int i = 0; i < 1; i++) {
//                lista = em.createQuery("from Contato where id = 5").getResultList();
//            }
//            System.out.println(lista.size());
//            System.out.println(lista.get(0));
//            em.close();
//        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("TEMPO de EXECUÇÃO:\t" + elapsedTime/1000.00 + " sec.");
        System.exit(0);
    }
}
