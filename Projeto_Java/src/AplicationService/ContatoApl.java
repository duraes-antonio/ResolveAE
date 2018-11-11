package AplicationService;

import Dominio.Entidades.Contato;
import Dominio.Enum.ETipoContato;
import Infraestrutura.Postgre.DAO.ContatoDAO;

import java.util.List;

public class ContatoApl extends GenericApl {
    //CONSTRUTORES
    public ContatoApl(){
        this.setDataDAO(new ContatoDAO());
    }

    //METODOS
    public List<Contato> getByType(ETipoContato type, int limit, int offset){
        List<Contato> resultSearch = null;
        try{
            ContatoDAO currentDao = (ContatoDAO) this.getDataDAo();
            resultSearch = currentDao.obterTodosPorTipo(type, limit, offset);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }

    public List<Contato> getByUser(int idUser, int limit, int offset){
        List<Contato> resultSearch = null;
        try{
            ContatoDAO currentDao = (ContatoDAO)this.getDataDAo();
            resultSearch = currentDao.obterTodosPorUsuario(idUser,limit,offset);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        finally {
            return  resultSearch;
        }
    }

    public List<Contato> getByUserNType(int idUser, ETipoContato type, int limit, int offset){
        List<Contato> resultSearch = null;
        try{
            ContatoDAO currentDao = (ContatoDAO) this.getDataDAo();
            resultSearch = currentDao.obterTodosPorTipoEUsuario(type,idUser,limit,offset);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }
}
