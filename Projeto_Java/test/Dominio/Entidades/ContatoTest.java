package Dominio.Entidades;

import Infraestrutura.Hibernate.Util.FabricaConexao;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

class ContatoTest {

    EntityManager em;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        System.out.println();
        em = FabricaConexao.obterConexao();
        get();
        em.close();
        get();
    }

    @Test
    void get() {
        Contato contato = (Contato) em.createQuery("from Contato where id = 1").getResultList().get(0);
        System.out.println(contato.getTipo());
    }
}