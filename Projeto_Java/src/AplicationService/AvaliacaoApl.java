package AplicationService;

import Dominio.Entidades.Avaliacao;
import Infraestrutura.Postgre.DAO.AvaliacaoDAO;

import java.util.List;

public class AvaliacaoApl extends  GenericApl{

    //CONSTRUTORES
    public AvaliacaoApl(){
        this.setDataDAO(new AvaliacaoDAO());
    }

    //METODOS
    public List<Avaliacao> getByUsuario(int idUsuario, int limit, int offset){
        List<Avaliacao> resultSearch = null;
        try{
            AvaliacaoDAO currentDao = (AvaliacaoDAO)this.getDataDAo();
            resultSearch = currentDao.obterTodasPorUsuario(idUsuario,limit,offset);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }

    public List<Avaliacao> getByServico(int idServico, int limit, int offset){
        List<Avaliacao> resultSearch = null;
        try{
            AvaliacaoDAO currentDao = (AvaliacaoDAO)this.getDataDAo();
            resultSearch = currentDao.obterTodasPorServico(idServico,limit,offset);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }

}
