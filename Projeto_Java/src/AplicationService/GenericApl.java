package AplicationService;

import Infraestrutura.Postgre.DAO.AGenericDAO;
import Infraestrutura.Postgre.Util.Persistencia;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.json.JSONObject;

import java.util.List;

public abstract class GenericApl<T> {

    private Persistencia currentConnection = Persistencia.get("resolve_ae", "postgres", "postgres", 5433);
    private AGenericDAO dataDAO;

    public void setDataDAO(AGenericDAO dataDAO) {

        this.dataDAO = dataDAO;
    }

    public AGenericDAO getDataDAo() {

        return this.dataDAO;
    }

    public T addData(T data) {

        T resultAdd = null;
        try {
            resultAdd = (T) this.dataDAO.adicionar(data);
        }
        catch (Exception erro) {
            resultAdd = null;
        }
        finally {
            return resultAdd;
        }
    }

    public void updateData(T data) {

        try {
            this.dataDAO.atualizar(data);
        }
        catch (Exception erro) {
            throw new ValueException("Nao foi possivel atualizar as informacoes.");
        }
    }

    public T getById(int id) {

        T resultSearch = null;
        try {
            resultSearch = (T) dataDAO.obterPorId(id);
        }
        catch (Exception erro) {
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }

    public List<T> getAll(int limt, int offset) {

        List<T> resultSerch = null;
        try {
            resultSerch = dataDAO.obterTodos(limt, offset);
        }
        catch (Exception erro) {
            resultSerch = null;
        }
        finally {
            return resultSerch;
        }
    }

    public abstract JSONObject parseDataToJSON(T data);

    public abstract List<JSONObject> parseListToJSONList(List<T> dataList);

}
