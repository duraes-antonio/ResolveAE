package Controller;

import AplicationService.AvaliacaoApl;
import Controller.Interfaces.IController;
import Dominio.Entidades.Avaliacao;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;


import java.util.List;

public class AvaliacaoController implements IController {
    //ATRIBUTOS
    private AvaliacaoApl aplication = null;
    private int skip = 10;
    private int offsetAll = 0;
    private int offsetUser = 0;
    private int offsetService = 0;

    //CONSTRUTORES
    public AvaliacaoController(){
        this.aplication = new AvaliacaoApl();
    }


    @Override
    public Avaliacao searchById(int id) {
        if(id>0) {
            Avaliacao resultSearch = null;
            resultSearch = (Avaliacao)this.aplication.getById(id);
            return resultSearch;
        }
        else{
            throw new ValueException("A avaliacao informada nao eh valida.");
        }
    }

    @Override
    public List<Avaliacao> searchAll() {
        List<Avaliacao> resultSearch = null;
        resultSearch = this.aplication.getAll(this.skip,this.offsetAll);
        return resultSearch;
    }

    public List<Avaliacao> searchByServico(int idServico){
        if(idServico>0){
            List<Avaliacao> resultSearch = null;
            resultSearch = this.aplication.getByServico(idServico,this.skip,this.offsetService);
            return resultSearch;
        }
        else{
            throw new ValueException("O servico informado nao eh valido.");
        }
    }

    public List<Avaliacao> searchByUser(int idUser){
        if(idUser>0){
            List<Avaliacao> resultSearch = null;
            resultSearch = this.aplication.getByUsuario(idUser,this.skip,this.offsetUser);
            return resultSearch;
        }
        else{
            throw new ValueException("O usuario informado nao eh valido.");
        }
    }
}
