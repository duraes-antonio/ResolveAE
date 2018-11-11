package AplicationService;

import Dominio.Entidades.Comentario;
import Infraestrutura.Postgre.DAO.ComentarioDAO;
import Infraestrutura.Postgre.Util.Persistencia;

import java.util.List;

public class ComentarioApl {
    //ATRIBUTOS
    private Persistencia localConnection = Persistencia.get("resolve_ae","postgres","XXXXXX",5433);
    private ComentarioDAO comentarioDb = new ComentarioDAO();

    //METODOS
    public Comentario getById(int id){
        Comentario resultSearch = null;
        try {
            resultSearch = this.comentarioDb.obterPorId(id);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        return resultSearch;
    }

    public List<Comentario> getByServico(int idServico,int skip, int offset){
        List<Comentario> resultSet = null;
        try{
            resultSet = this.comentarioDb.obterTodosPorServico(idServico,skip,offset);
        }
        catch (Exception erro){
            resultSet = null;
        }
        return  resultSet;
    }

    public Comentario getByAvaliacao(int idAvaliacao){
        Comentario resultSearch = null;
        try{
            resultSearch = this.comentarioDb.obterPorAvaliacao(idAvaliacao);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        return resultSearch;
    }

    public List<Comentario> getByUsuario(int idUsuario, int limit, int offset){
        List<Comentario> resultSet = null;
        try{
            resultSet = this.comentarioDb.obterTodosPorUsuario(idUsuario,limit,offset);
        }
        catch (Exception erro){
            resultSet = null;
        }
        return resultSet;
    }

    public List<Comentario> getAll(int limit, int offset){
        List<Comentario> resultSet = null;
        try{
            resultSet = this.comentarioDb.obterTodos(limit,offset);
        }
        catch(Exception erro){
            resultSet = null;
        }
        return  resultSet;
    }
}
