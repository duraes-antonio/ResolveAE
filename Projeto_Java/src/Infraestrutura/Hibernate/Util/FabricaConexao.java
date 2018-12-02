package Infraestrutura.Hibernate.Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FabricaConexao {


    private static FabricaConexao fabricaConexao;
    private static EntityManagerFactory emf;

    public enum EConfig {
        ANTONIO("postgresql.antonio"),
        ELIMAR("postgresql.elimar"),
        IFES("postgresql.ifes");

        private String nome;

        EConfig(String nome) {
            this.nome = nome;
        }

        public String getNome() {
            return this.nome;
        }
    }

    private FabricaConexao(EConfig config) {
        emf = Persistence.createEntityManagerFactory(config.getNome());
    }

    public static synchronized FabricaConexao get(EConfig configuracao) {

        try {

            if (emf == null || !emf.isOpen()) {
                fabricaConexao = new FabricaConexao(configuracao);
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return fabricaConexao;
    }

    public static synchronized FabricaConexao get() {

        try {

            if (emf == null || !emf.isOpen()) {
                fabricaConexao = new FabricaConexao(EConfig.ANTONIO);
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return fabricaConexao;
    }

    public EntityManager obterConexao() {
        return emf.createEntityManager();
    }

    public void FecharTodasConexoes() {

        if (emf.isOpen()) {
            emf.close();
        }
    }
}
