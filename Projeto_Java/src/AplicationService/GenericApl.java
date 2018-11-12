package AplicationService;

import Infraestrutura.Postgre.DAO.AGenericDAO;
import Infraestrutura.Postgre.Util.Persistencia;

import java.util.List;

public abstract class GenericApl<T>{
    private Persistencia currentConnection = Persistencia.get("resolve_ae","postgres","senha",5433);
    private AGenericDAO dataDAO;

    public void setDataDAO(AGenericDAO dataDAO){
        this.dataDAO = dataDAO;
    }

    public AGenericDAO getDataDAo(){
        return this.dataDAO;
    }

    public T getById(int id){
        T resultSearch = null;
        try{
            resultSearch = (T)dataDAO.obterPorId(id);
        }
        catch(Exception erro){
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }

    public List<T> getAll(int limt, int offset){
        List<T> resultSerch = null;
        try{
            resultSerch =  dataDAO.obterTodos(limt,offset);
        }
        catch (Exception erro){
            resultSerch = null;
        }
        finally {
            return resultSerch;
        }
    }
}
