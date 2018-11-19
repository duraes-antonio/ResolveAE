package Infraestrutura.Hibernate.Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FabricaConexao {

    private FabricaConexao() {}

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence.postgre");

    public static EntityManager obterConexao() {
        return emf.createEntityManager();
    }
}
