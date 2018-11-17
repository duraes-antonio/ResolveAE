package Controller;
import AplicationService.ComentarioApl;
import Controller.Interfaces.IController;
import Dominio.Entidades.Comentario;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class ComentarioController implements IController<Comentario> {
    private ComentarioApl aplication = null;
    private int skip = 10;
    private int offsetServicos = 0;
    private int offsetUsuario = 0;
    private int offsetAll = 0;

    public ComentarioController(){
        this.aplication = new ComentarioApl();
    }

    @Override
    public String executeMethodGet(Map<String, String[]> parameters) throws Exception {
        String jsonString = "";
        String methodName = parameters.get("method")[0];

        if(methodName.equalsIgnoreCase("searchbyid")){
            int idComentario = 0;
            try{
                idComentario = Integer.parseInt(parameters.get("ID")[0]);
            }
            catch (Exception erro){
                throw new ValueException("Nao eh possivel efetuar busca para o comentario selecionado");
            }
            Comentario resultSearch = this.searchById(idComentario);
            jsonString = this.toJson(resultSearch).toString();
        }

        else if(methodName.equalsIgnoreCase("searchall")){
            List<Comentario> resultSearch = this.searchAll();
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for(JSONObject json : jsonList){
                jsonString += json.toString() + "<br>";
            }
        }

        else if(methodName.equalsIgnoreCase("searchByIdServico")){
            int idServico = 0;
            try{
                idServico = Integer.parseInt(parameters.get("IdServico")[0]);
            }
            catch (Exception erro){
                throw  new ValueException("Nao eh possivel efetuar busca para o comentario selecionado");
            }
            List<Comentario> resultSearch = this.searchByIdServico(idServico);
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for(JSONObject json : jsonList){
                jsonString += json.toString() + "<br>";
            }
        }

        else if(methodName.equalsIgnoreCase("searchByIdAvaliacao")){
            int idAvalicao = 0;
            try{
                idAvalicao = Integer.parseInt(parameters.get("FkAvaliacao")[0]);
            }
            catch (Exception erro){
                throw  new ValueException("Nao eh possivel efetuar busca para a avaliacao selecionada");
            }
            Comentario resultSearch = this.searchById(idAvalicao);
            jsonString = this.toJson(resultSearch).toString();
        }

        else if (methodName.equalsIgnoreCase("searchByIdUser")){
            int idUser = 0;
            try{
                idUser = Integer.parseInt(parameters.get("IdUser")[0]);
            }
            catch (Exception erro){
                throw  new ValueException("Nao eh possivel efetuar busca para o comentario selecionado");
            }
            List<Comentario> resultSearch = this.searchByIdUser(idUser);
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
        if (methodName.equalsIgnoreCase("atualizar")){
            int idComentario = Integer.parseInt(parameters.get("ID")[0]);
            String comentario = parameters.get("Comentario")[0];
            int fkAvaliacao = Integer.parseInt(parameters.get("FkAvaliacao")[0]);
            Comentario updateData = new Comentario(idComentario,comentario,fkAvaliacao);
            this.aplication.updateData(updateData);
        }
        else if (methodName.equalsIgnoreCase("adicionar")){
            String comentario = parameters.get("Comentario")[0];
            int fkAvaliacao = Integer.parseInt(parameters.get("FkAvaliacao")[0]);
            Comentario addData = new Comentario(comentario,fkAvaliacao);
            Comentario resultAdd =  this.aplication.addData(addData);
        }
        else{
            throw  new Exception("O metodo informado nao eh valido.");
        }
    }

    @Override
    public Comentario searchById(int id){
        if (id>0){
            Comentario comentarioSearch = null;
            comentarioSearch =  this.aplication.getById(id);
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

    @Override
    public JSONObject toJson(Comentario data) {
        return this.aplication.parseDataToJSON(data);
    }

    @Override
    public List<JSONObject> toJsonList(List<Comentario> listData) {
        return this.aplication.parseListToJSONList(listData);
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
            throw  new ValueException("Nao eh possivel efetuar busca para a avaliacao selecionada");
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
