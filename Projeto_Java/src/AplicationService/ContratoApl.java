package AplicationService;

import Dominio.Entidades.Contrato;
import Infraestrutura.Postgre.DAO.ContratoDAO;

import java.time.LocalDate;
import java.util.List;

public class ContratoApl extends  GenericApl{
    //CONSTRUTORES
    public ContratoApl(){
        this.setDataDAO(new ContratoDAO());
    }

    //METODOS
    public List<Contrato> getByUser(int idUser,int limit,int offset){
        List<Contrato> resultSearch = null;
        try{
            ContratoDAO currentDao = (ContratoDAO) this.getDataDAo();
            resultSearch = currentDao.obterTodosPorUsuario(idUser,limit,offset);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }

    public List<Contrato> getByDescription(String description, int limit, int offset){
        List<Contrato> resultSearch = null;
        try{
            ContratoDAO currentDao = (ContratoDAO)this.getDataDAo();
            resultSearch = currentDao.obterTodosPorDescricao(description,limit,offset);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }

    public List<Contrato> getByDate(LocalDate infDate, LocalDate supDate, int limit, int offset){
        List<Contrato> resultSearch = null;
        try{
            ContratoDAO currentDao = (ContratoDAO)this.getDataDAo();
            resultSearch = currentDao.obterTodosPorData(infDate,supDate,limit,offset);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        finally {
            return  resultSearch;
        }
    }
}
