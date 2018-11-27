package Infraestrutura.Hibernate.DAO;

import Dominio.Interfaces.IBaseRepositorio;
import Infraestrutura.Hibernate.Util.FabricaConexao;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public abstract class AGenericDAOH<T> implements IBaseRepositorio<T> {

    private Class<T> classe;

    public void definirClasse(final Class<T> classe){
        this.classe = classe;
    }

    public AGenericDAOH(Class<T> classe) {
        this.definirClasse(classe);
    }

    /**Persiste o objeto em um meio não volátil de armazenamento.
     * @param objeto Objeto a ser persistido.
     * @return objeto Objeto atualizado após a inserção.
     */
    public T adicionar(T objeto) {

        EntityManager entityManager = FabricaConexao.obterConexao();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(objeto);
            entityManager.getTransaction().commit();
        }

        catch (Exception e) {
            entityManager.getTransaction().rollback();
        }

        finally {
            entityManager.close();
        }

        return objeto;
    }

    /**Atualiza os dados de um objeto já existente no meio de persistência.
     * @param objeto Objeto a ser atualizado.
     */
    public void atualizar(T objeto){

        EntityManager entityManager = FabricaConexao.obterConexao();

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(objeto);
            entityManager.getTransaction().commit();
        }

        catch (Exception e) {
            entityManager.getTransaction().rollback();
        }

        finally {
            entityManager.close();
        }
    }

    /**Busca e retorna o objeto que possuir o identificador recebido.
     * @param id Identificador do objeto a ser buscado.
     * @return Objeto já construído e com atributos preenchidos, inclusive ID.
     */
    public T obterPorId(int id){

        EntityManager entityManager = FabricaConexao.obterConexao();
        T objeto = null;

        try {
            objeto = entityManager.find(this.classe, id);
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        finally {
            entityManager.close();
        }

        return objeto;
    }

    /**Busca e retorna todos objetos de um determinado tipo.
     * @return Lista com todos objetos encontrados.
     */
    public List<T> obterTodos(){

        EntityManager em = FabricaConexao.obterConexao();
        List<T> objetos = new ArrayList<>();

        try {
            objetos = em.createQuery("from " + classe.getName()).getResultList();
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        finally {
            em.close();
        }

        return objetos;
    }

    /**Remove um objeto do meio de persistência dado seu identificador.
     * @param id Identificador do objeto a ser removido.
     */
    public void excluirPorId(int id){

        EntityManager em = FabricaConexao.obterConexao();

        try {
            em.getTransaction().begin();
            T objeto = em.find(classe, id);
            em.remove(objeto);
            em.getTransaction().commit();
        }

        catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }

        finally {
            em.close();
        }
    }
}
