import Dominio.Entidades.Avaliacao;
import Infraestrutura.Hibernate.Util.FabricaConexao;
import Infraestrutura.Postgre.DAO.AvaliacaoDAO;
import Infraestrutura.Postgre.Util.Persistencia;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        long startTime;
        boolean hard = false;
        List<Avaliacao> lista = new ArrayList<>();

        if (false) {
            AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
            Persistencia persistencia = Persistencia.get();
            startTime = System.currentTimeMillis();

            for (int i = 0; i < 1; i++) {
                lista = avaliacaoDAO.obterTodos();
            }
            System.out.println(lista.size());
            System.out.println(lista.get(0));
            persistencia.getConexao().close();
        }

        if(true) {
            EntityManager em = FabricaConexao.obterConexao();

            startTime = System.currentTimeMillis();
            for (int i = 0; i < 1; i++) {
                lista = em.createQuery("from Avaliacao").getResultList();
            }
            System.out.println(lista.size());
            System.out.println(lista.get(0));
            em.close();
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("TEMPO de EXECUÇÃO:\t" + elapsedTime/1000.00 + " sec.");
        System.exit(0);
    }
}
