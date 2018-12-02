package Infraestrutura.Hibernate.DAO;

import Dominio.Interfaces.IBaseRepositorio;
import Infraestrutura.Hibernate.Util.FabricaConexao;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public abstract class AGenericDAOHibernate<T> implements IBaseRepositorio<T> {

    private Class<T> classe;

    private FabricaConexao fabricaCon = FabricaConexao.get();
    private EntityManager conexao;
    private boolean fecharConexao = true;

    public AGenericDAOHibernate(Class<T> classe) {
        this.definirClasse(classe);
    }


    public void definirClasse(final Class<T> classe){
        this.classe = classe;
    }

    /**Persiste o objeto em um meio não volátil de armazenamento.
     * @param objeto Objeto a ser persistido.
     * @return objeto Objeto atualizado após a inserção.
     */
    public T adicionar(T objeto) {

        EntityManager conexao = fabricaCon.obterConexao();

        try {
            conexao.getTransaction().begin();
            conexao.persist(objeto);
            conexao.getTransaction().commit();
        }

        catch (Exception e) {
            conexao.getTransaction().rollback();
            e.printStackTrace();
        }

        finally {
            if (fecharConexao) conexao.close();
        }

        return objeto;
    }

    /**Atualiza os dados de um objeto já existente no meio de persistência.
     * @param objeto Objeto a ser atualizado.
     */
    public void atualizar(T objeto){

        EntityManager conexao = fabricaCon.obterConexao();

        try {
            conexao.getTransaction().begin();
            conexao.merge(objeto);
            conexao.getTransaction().commit();
        }

        catch (Exception e) {
            conexao.getTransaction().rollback();
            e.printStackTrace();
        }

        finally {
            if (fecharConexao) conexao.close();
        }
    }

    /**Busca e retorna o objeto que possuir o identificador recebido.
     * @param id Identificador do objeto a ser buscado.
     * @return Objeto já construído e com atributos preenchidos, inclusive ID.
     */
    public T obterPorId(int id){

        EntityManager conexao = fabricaCon.obterConexao();

        T objeto = null;

        try {
            objeto = conexao.find(this.classe, id);
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        finally {
            if (fecharConexao) conexao.close();
        }

        return objeto;
    }

    /**Busca e retorna todos objetos de um determinado tipo.
     * @return Lista com todos objetos encontrados.
     */
    public List<T> obterTodos(Integer limit, Integer offset) {

        EntityManager conexao = fabricaCon.obterConexao();

        List<T> objetos = new ArrayList<>();

        try {
            objetos = conexao.createQuery("from " + classe.getName())
                    .setFirstResult(offset).setMaxResults(limit).getResultList();
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        finally {
            if (fecharConexao) conexao.close();
        }

        return objetos;
    }

    /**Remove um objeto do meio de persistência dado seu identificador.
     * @param id Identificador do objeto a ser removido.
     */
    public void excluirPorId(int id){

        EntityManager conexao = fabricaCon.obterConexao();

        try {
            conexao.getTransaction().begin();
            T objeto = conexao.find(classe, id);
            conexao.remove(objeto);
            conexao.getTransaction().commit();
        }

        catch (Exception e) {
            conexao.getTransaction().rollback();
            e.printStackTrace();
        }

        finally {
            if (fecharConexao) conexao.close();
        }
    }
}
