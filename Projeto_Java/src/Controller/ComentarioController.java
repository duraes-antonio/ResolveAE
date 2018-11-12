package Controller;

import AplicationService.ComentarioApl;
import Controller.Interfaces.IController;
import Dominio.Entidades.Comentario;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.util.List;

public class ComentarioController implements IController {
    private ComentarioApl aplication = null;
    private int skip = 10;
    private int offsetServicos = 0;
    private int offsetUsuario = 0;
    private int offsetAll = 0;

    public ComentarioController(){
        this.aplication = new ComentarioApl();
    }

    @Override
    public Comentario searchById(int id){
        if (id>0){
            Comentario comentarioSearch = null;
            comentarioSearch = (Comentario) this.aplication.getById(id);
            return comentarioSearch;
        }
        else{
            throw new ValueException("Nao eh possivel efetuar busca para o comentario selecionado");
        }
    }

    @Override
    public List<Comentario> searchAll(){
        List<Comentario> comentariosSearch = null;
        comentariosSearch = this.aplication.getAll(this.skip,offsetAll);
        return comentariosSearch;
    }

    public List<Comentario> searchByIdServico(int idServico){
        if(idServico>0){
            List<Comentario> comentariosSearch = null;
            comentariosSearch = this.aplication.getByServico(idServico,this.skip,this.offsetServicos);
            return comentariosSearch;
        }
        else{
            throw  new ValueException("Nao eh possivel efetuar busca para o comentario selecionado");
        }
    }

    public Comentario searchByIdAvaliacao(int idAvaliacao){
        if(idAvaliacao>0){
            Comentario comentarioSearch = null;
            comentarioSearch = this.aplication.getByAvaliacao(idAvaliacao);
            return comentarioSearch;
        }
        else{
            throw  new ValueException("Nao eh possivel efetuar busca para o comentario selecionado");
        }
    }

    public List<Comentario> searchByIdUser(int idUser){
        if(idUser>0){
            List<Comentario> comentariosSearch = null;
            comentariosSearch = this.aplication.getByUsuario(idUser,this.skip,this.offsetUsuario);
            return comentariosSearch;
        }
        else{
            throw  new ValueException("Nao eh possivel efetuar busca para o comentario selecionado");
        }
    }





}
