package Controller;

import AplicationService.ContatoApl;
import Controller.Interfaces.IController;
import Dominio.Entidades.Contato;
import Dominio.Enum.ETipoContato;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.json.JSONObject;

import java.util.List;

public class ContatoController implements IController<Contato> {
    //ATRIBUTOS
    private ContatoApl aplication = null;
    private int limit = 10;
    private int offsetAll = 0;
    private int offsetType = 0;
    private int offsetUser = 0;
    private int offsetUserNType = 0;

    //CONSTRUTORES
    public ContatoController(){
        this.aplication = new ContatoApl();
    }

    //METODOS
    @Override
    public Contato searchById(int id){
        if (id>0){
            Contato resultSearch = null;
            resultSearch = this.aplication.getById(id);
            return resultSearch;
        }
        else{
            throw new ValueException("O contato informado nao eh valido");
        }
    }

    @Override
    public List<Contato> searchAll(){
        List<Contato> resultSearch = null;
        resultSearch = this.aplication.getAll(this.limit,this.offsetAll);
        return  resultSearch;
    }

    @Override
    public JSONObject toJson(Contato data) {
        return this.aplication.parseDataToJSON(data);
    }

    @Override
    public List<JSONObject> toJsonList(List<Contato> listData) {
        return this.aplication.parseListToJSONList(listData);
    }

    public List<Contato> searchByType(ETipoContato type){
        if (type.getId()>0){
            List<Contato> resultSearch = null;
            resultSearch = this.aplication.getByType(type,this.limit,this.offsetType);
            return resultSearch;
        }
        else{
            throw new ValueException("O tipo informado nao eh valido");
        }
    }

    public List<Contato> searchByUser(int idUser){
        if (idUser > 0){
            List<Contato> resultSearch = null;
            resultSearch = this.aplication.getByUser(idUser,this.limit, this.offsetUser);
            return resultSearch;
        }
        else{
            throw new ValueException("O usuario informado nao eh valido");
        }
    }

    public List<Contato> searchByUserNType(int idUser, ETipoContato type){
        if (idUser >0 && type.getId()>0){
            List<Contato> resultSearch = null;
            resultSearch = this.aplication.getByUserNType(idUser,type,this.limit,this.offsetUserNType);
            return  resultSearch;
        }
        else{
            throw new ValueException("O usuario ou o tipo informado nao sao validos.");
        }
    }

}
