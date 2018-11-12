
package Controller;

import AplicationService.ServicoApl;
import Controller.Interfaces.IController;
import Dominio.Entidades.Servico;
import Dominio.Enum.ESubtipoServico;
import Dominio.Enum.ETipoServico;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.json.JSONObject;

import java.util.List;

public class ServicoController implements IController<Servico> {

    //ATRIBUTOS
    private ServicoApl aplication = null;
    private int limit = 10;
    private int offsetAll = 0;
    private int offsetUser = 0;
    private int offsetTitle = 0;
    private int offsetDescription = 0;
    private int offsetPrice = 0;
    private int offsetType = 0;
    private int offsetSubtype = 0;


    //METODOS
    @Override
    public Servico searchById(int id) {
        if(id > 0){
            Servico resultSearch = this.aplication.getById(id);
            return resultSearch;
        }
        else{
            throw new ValueException("O servico informado nao eh valido");
        }
    }

    @Override
    public List<Servico> searchAll() {
        List<Servico> resultSearch = this.aplication.getAll(this.limit,this.offsetAll);
        return resultSearch;
    }

    @Override
    public JSONObject toJson(Servico data) {
        return this.aplication.parseDataToJSON(data);
    }

    @Override
    public List<JSONObject> toJsonList(List<Servico> listData) {
        return this.aplication.parseListToJSONList(listData);
    }

    public List<Servico> searchByUser(int userId){
        if(userId > 0) {
            List<Servico> resultSearch = this.aplication.getByUserId(userId, this.limit, this.offsetUser);
            return resultSearch;
        }
        else{
            throw new ValueException("O usuario informado nao eh valido");
        }
    }

    public List<Servico> searchByTitle(String title){
        if(!title.isEmpty()){
            List<Servico> resultSearch = this.aplication.getByTitle(title, this.limit, this.offsetTitle);
            return resultSearch;
        }
        else {
            throw new ValueException("Necessario informar um titulo para a pesquisa");
        }
    }

    public List<Servico> searchByDescription(String description){
        if(!description.isEmpty()){
            List<Servico> resultSearch = this.aplication.getByDescription(description,this.limit,this.offsetDescription);
            return resultSearch;
        }
        else{
            throw new ValueException("Necessario informar uma descricao para a pesquisa");
        }
    }

    public List<Servico> searchByPrice (double minPrice, double maxPrice){
        if(maxPrice > minPrice && minPrice >= 0){
            List<Servico> resultSearch = this.aplication.getByPrice(minPrice, maxPrice, this.limit, this.offsetPrice);
            return resultSearch;
        }
        else{
            throw  new ValueException("Os precos informados nao sao validos.");
        }
    }

    public List<Servico> searchByType(ETipoServico type){
        if(type.getId()>0){
            List<Servico> resultSearch = this.aplication.getByType(type, this.limit, this.offsetType);
            return resultSearch;
        }
        else{
            throw new ValueException("O tipo de servico informado nao eh valido");
        }
    }

    public List<Servico> searchBySubtype(ESubtipoServico subtype){
        if(subtype.getId()>0){
            List<Servico> resultSearch = this.aplication.getBySubtype(subtype,this.limit, this.offsetSubtype);
            return resultSearch;
        }
        else{
            throw new ValueException("O subtipo de servico informado nao eh valido");
        }
    }



}
