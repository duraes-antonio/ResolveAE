package Controller;

import AplicationService.AvaliacaoApl;
import Controller.Interfaces.IController;
import Dominio.Entidades.Avaliacao;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.json.JSONObject;


import java.util.List;
import java.util.Map;

public class AvaliacaoController implements IController<Avaliacao> {
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
    public String executeMethodGet(Map<String, String[]> parameters) throws Exception {
        String jsonString = "";
        String methodName = parameters.get("method")[0];
        if(methodName.equalsIgnoreCase("searchbyid")){
            int idAvaliacao = 0;
            try{
                idAvaliacao = Integer.parseInt(parameters.get("ID")[0]);
            }
            catch (Exception erro){
                throw new ValueException("A avaliacao informada nao eh valida.");
            }
            Avaliacao resultMethod = this.searchById(idAvaliacao) ;
            jsonString = this.toJson(resultMethod).toString();
        }

        else if(methodName.equalsIgnoreCase("searchall")){
            List<Avaliacao> resultSearch = this.searchAll();
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for(JSONObject json : jsonList){
                jsonString += json.toString()+"<br>";
            }
        }

        else if(methodName.equalsIgnoreCase("searchbyservico")){
            int idServico = 0;
            try{
                idServico = Integer.parseInt(parameters.get("FkServico")[0]);
            }
            catch (Exception erro){
                throw new ValueException("O servico informado nao eh valido.");
            }
            List<Avaliacao> resultSearch = this.searchByServico(idServico);
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for(JSONObject json : jsonList){
                jsonString += json.toString()+"<br>";
            }
        }

        else if(methodName.equalsIgnoreCase("seachbyuser")){
            int idUser = 0;
            try{
                idUser = Integer.parseInt(parameters.get("FkUsuario")[0]);
            }
            catch (Exception erro){
                throw new ValueException("O usuario informado nao eh valido.");
            }
            List<Avaliacao> resultSearch = this.searchByUser(idUser);
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for(JSONObject json : jsonList){
                jsonString += json.toString()+"<br>";
            }
        }

        else{
            throw  new Exception("O metodo informado nao eh valido.");
        }
        return jsonString;
    }

    @Override
    public void executeMethodPost(Map<String, String[]> parameters) throws Exception{
        String methodName = parameters.get("method")[0];
        if(methodName.equalsIgnoreCase("Atualizar")){
            int idAvaliacao  = Integer.parseInt(parameters.get("ID")[0]);
            int nota = Integer.parseInt(parameters.get("Nota")[0]);
            int fkUsuario = Integer.parseInt(parameters.get("FkUsuario")[0]);
            int fkServico = Integer.parseInt(parameters.get("FkServico")[0]);
            Avaliacao updateData = new Avaliacao(nota,fkUsuario,fkServico);
            updateData.setId(idAvaliacao);
            this.aplication.updateData(updateData);
        }
        else if(methodName.equalsIgnoreCase("Adicionar")){
            int nota = Integer.parseInt(parameters.get("Nota")[0]);
            int fkUsuario = Integer.parseInt(parameters.get("FkUsuario")[0]);
            int fkServico = Integer.parseInt(parameters.get("FkServico")[0]);
            Avaliacao addData = new Avaliacao(nota,fkUsuario,fkServico);
            Avaliacao resultAdd = this.aplication.addData(addData);
        }
        else{
            throw  new Exception("O metodo informado nao eh valido.");
        }

    }

    @Override
    public Avaliacao searchById(int id) {
        if(id>0) {
            Avaliacao resultSearch = null;
            resultSearch = this.aplication.getById(id);
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

    @Override
    public JSONObject toJson(Avaliacao data) {
        return this.aplication.parseDataToJSON(data);
    }

    @Override
    public List<JSONObject> toJsonList(List<Avaliacao> listData) {
        return this.aplication.parseListToJSONList(listData);
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
