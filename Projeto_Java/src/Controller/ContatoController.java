package Controller;

import AplicationService.ContatoApl;
import Controller.Interfaces.IController;
import Dominio.Entidades.Contato;
import Dominio.Enum.ETipoContato;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

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

    @Override
    public String executeMethodGet(Map<String, String[]> parameters) throws Exception {
        String jsonString = "";
        String methodName = parameters.get("method")[0];

        if(methodName.equalsIgnoreCase("searchById")){
            int idContato = 0;
            try{
                idContato = Integer.parseInt(parameters.get("ID")[0]);
            }
            catch (Exception erro){
                throw new ValueException("O contato informado nao eh valido");
            }
            Contato resultSearch = this.searchById(idContato);
            jsonString = this.toJson(resultSearch).toString();
        }

        else if (methodName.equalsIgnoreCase("searchAll")){
            List<Contato> resultSearch = this.searchAll();
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for(JSONObject json : jsonList){
                jsonString += json.toString() + "<br>";
            }
        }

        else if (methodName.equalsIgnoreCase("searchByType")){
            ETipoContato type = ETipoContato.valueOf(parameters.get("TipoContato")[0]);
            List<Contato> resultSearch = this.searchByType(type);
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for(JSONObject json : jsonList){
                jsonString += json.toString() + "<br>";
            }
        }

        else if(methodName.equalsIgnoreCase("searchByUser")){
            int idUser = 0;
            try{
                idUser = Integer.parseInt(parameters.get("FkUsuario")[0]);
            }
            catch (Exception erro){
                throw new ValueException("O usuario informado nao eh valido");
            }
            List<Contato> resultSearch = this.searchByUser(idUser);
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for(JSONObject json : jsonList){
                jsonString += json.toString() + "<br>";
            }
        }

        else if(methodName.equalsIgnoreCase("searchByUserNType")){
            ETipoContato type = ETipoContato.valueOf(parameters.get("TipoContato")[0]);
            int idUser = 0;
            try{
                idUser = Integer.parseInt(parameters.get("FkUsuario")[0]);
            }
            catch (Exception erro){
                throw new ValueException("O usuario ou o tipo informado nao sao validos.");
            }
            List<Contato> resultSearch = this.searchByUserNType(idUser,type);
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for(JSONObject json : jsonList){
                jsonString += json.toString() + "<br>";
            }
        }

        else{
            throw  new Exception("O metodo informado nao eh valido.");
        }

        return jsonString;
    }

    @Override
    public void executeMethodPost(Map<String, String[]> parameters) throws Exception {
        String methodName = parameters.get("method")[0];
        if(methodName.equalsIgnoreCase("atualizar")){
            int idContato = Integer.getInteger(parameters.get("ID")[0]);
            int fkUsuario = Integer.getInteger(parameters.get("FkUsuario")[0]);
            ETipoContato type = ETipoContato.valueOf(parameters.get("TipoContato")[0]);
            String descricao = parameters.get("Descricao")[0];
            Contato updateData = new Contato(idContato, descricao,fkUsuario,type.getId());
            this.aplication.updateData(updateData);
        }
        else if (methodName.equalsIgnoreCase("adicionar")){

            int fkUsuario = Integer.getInteger(parameters.get("FkUsuario")[0]);
            ETipoContato type = ETipoContato.valueOf(parameters.get("TipoContato")[0]);
            String descricao = parameters.get("Descricao")[0];
            Contato addData = new Contato(descricao,fkUsuario,type.getId());
            Contato resultAdd = this.aplication.addData(addData);
        }
        else{
            throw  new Exception("O metodo informado nao eh valido.");
        }
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
