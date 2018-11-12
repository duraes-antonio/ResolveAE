package AplicationService;

import Dominio.Entidades.InfoProfissional;
import Dominio.Enum.ETipoInfoProfissional;
import Infraestrutura.Postgre.DAO.InfoProfissionalDAO;

import java.time.LocalDate;
import java.util.List;

public class InfoProfissionalApl extends GenericApl{
    //CONSTRUTOR
    public InfoProfissionalApl(){
        this.setDataDAO(new InfoProfissionalDAO());
    }
    //METODOS

    public List<InfoProfissional> getByType(ETipoInfoProfissional type, int limit, int offset){
        List<InfoProfissional> resultSearch = null;
        try{
            InfoProfissionalDAO currentDao = (InfoProfissionalDAO)this.getDataDAo();
            resultSearch = currentDao.obterTodosPorTipo(type, limit, offset);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }

    public List<InfoProfissional> getByUser(int idUser, int limit, int offset){
        List<InfoProfissional> resultSearch = null;
        try{
            InfoProfissionalDAO currentDao = (InfoProfissionalDAO)this.getDataDAo();
            resultSearch = currentDao.obterTodosPorUsuario(idUser, limit, offset);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }

    public List<InfoProfissional> getByUserNType(int idUser, ETipoInfoProfissional type, int limit, int offset){
        List<InfoProfissional> resultSearch = null;
        try{
            InfoProfissionalDAO currentDao = (InfoProfissionalDAO)this.getDataDAo();
            resultSearch = currentDao.obterTodosPorTipoEUsuario(type,idUser, limit, offset);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }

    public List<InfoProfissional> getByDate(LocalDate infDate, LocalDate supDate, int limit, int offset){
        List<InfoProfissional> resultSearch = null;
        try{
            InfoProfissionalDAO currentDao = (InfoProfissionalDAO)this.getDataDAo();
            resultSearch = currentDao.obterTodosPorData(infDate, supDate, limit, offset);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }
}
