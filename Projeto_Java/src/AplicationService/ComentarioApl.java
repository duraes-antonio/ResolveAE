package AplicationService;

import Dominio.Entidades.Comentario;
import Infraestrutura.Postgre.DAO.ComentarioDAO;

import java.util.List;

public class ComentarioApl extends GenericApl{

    //CONSTRUTORES
    public ComentarioApl(){
        this.setDataDAO(new ComentarioDAO());
    }

    //METODOS

    public List<Comentario> getByServico(int idServico,int skip, int offset){
        List<Comentario> resultSet = null;
        try{
            ComentarioDAO currentDAO =(ComentarioDAO)this.getDataDAo();
            resultSet = currentDAO.obterTodosPorServico(idServico,skip,offset);
        }
        catch (Exception erro){
            resultSet = null;
        }
        return  resultSet;
    }

    public Comentario getByAvaliacao(int idAvaliacao){
        Comentario resultSearch = null;
        try{
            ComentarioDAO currentDAO =(ComentarioDAO)this.getDataDAo();
            resultSearch = currentDAO.obterPorAvaliacao(idAvaliacao);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        return resultSearch;
    }

    public List<Comentario> getByUsuario(int idUsuario, int limit, int offset){
        List<Comentario> resultSet = null;
        try{
            ComentarioDAO currentDAO =(ComentarioDAO)this.getDataDAo();
            resultSet = currentDAO.obterTodosPorUsuario(idUsuario,limit,offset);
        }
        catch (Exception erro){
            resultSet = null;
        }
        return resultSet;
    }
}
