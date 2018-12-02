package Controller;

import AplicationService.ContratoApl;
import Controller.Interfaces.IController;
import Dominio.Entidades.Contrato;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.json.JSONObject;

import java.security.InvalidParameterException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class ContratoController implements IController<Contrato> {

    //ATRIBUTOS
    private ContratoApl aplication = null;
    private int limit = 10;
    private int offsetAll = 0;
    private int offsetUser = 0;
    private int offsetDescription = 0;
    private int offsetDate = 0;

    //CONSTRUTORES
    public ContratoController() {

        this.aplication = new ContratoApl();
    }

    @Override
    public String executeMethodGet(Map<String, String[]> parameters) throws Exception {

        String jsonString = "";
        String methodName = parameters.get("method")[0];
        if(methodName.equalsIgnoreCase("searchById")) {
            int idContrato = 0;
            try {
                idContrato = Integer.parseInt(parameters.get("ID")[0]);
            }
            catch (Exception erro) {
                throw new ValueException("O contrato informado nao eh valido");
            }
            Contrato resultSearch = this.searchById(idContrato);
            jsonString = this.toJson(resultSearch).toString();
        }
        else if(methodName.equalsIgnoreCase("searchAll")) {
            List<Contrato> resultSearch = this.searchAll();
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for(JSONObject json : jsonList) {
                jsonString += json.toString() + "<br>";
            }
        }
        else if(methodName.equalsIgnoreCase("searchByUserId")) {
            int idUser = 0;
            try {
                idUser = Integer.parseInt(parameters.get("FkUsuario")[0]);
            }
            catch (Exception erro) {
                throw new ValueException("O usuario informado nao eh valido.");
            }
            List<Contrato> resultSearch = this.searchByUserId(idUser);
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for(JSONObject json : jsonList) {
                jsonString += json.toString() + "<br>";
            }
        }
        else if(methodName.equalsIgnoreCase("searchByDescription")) {
            String description = parameters.get("Descricao")[0];
            List<Contrato> resultSearch = this.searchByDescription(description);
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for(JSONObject json : jsonList) {
                jsonString += json.toString() + "<br>";
            }
        }
        else if(methodName.equalsIgnoreCase("searchByDate")) {

            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate infDate = LocalDate.parse(parameters.get("DataInicio")[0], dateFormat);
            LocalDate supDate = LocalDate.parse(parameters.get("DataFim")[0], dateFormat);
            List<Contrato> resultSearch = this.searchByDate(infDate, supDate);
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for(JSONObject json : jsonList) {
                jsonString += json.toString() + "<br>";
            }
        }
        else {
            throw new Exception("O metodo informado nao eh valido.");
        }

        return jsonString;
    }

    @Override
    public void executeMethodPost(Map<String, String[]> parameters) throws Exception {

        String methodName = parameters.get("method")[0];
        if(methodName.equalsIgnoreCase("atualizar")) {
            int idContrato = Integer.parseInt(parameters.get("ID")[0]);
            String descricao = parameters.get("Descricao")[0];
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate infDate = LocalDate.parse(parameters.get("DataInicio")[0], dateFormat);
            LocalDate supDate = LocalDate.parse(parameters.get("DataFim")[0], dateFormat);
            int hrsContratadas = Integer.parseInt(parameters.get("HorasContratadas")[0]);
            int fkUsuario = Integer.parseInt(parameters.get("FkUsuario")[0]);
            Contrato updateData = new Contrato(idContrato, descricao, infDate, supDate, hrsContratadas, fkUsuario);
            this.aplication.updateData(updateData);
        }
        else if(methodName.equalsIgnoreCase("adicionar")) {
            String descricao = parameters.get("Descricao")[0];
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate infDate = LocalDate.parse(parameters.get("DataInicio")[0], dateFormat);
            LocalDate supDate = LocalDate.parse(parameters.get("DataFim")[0], dateFormat);
            int hrsContratadas = Integer.parseInt(parameters.get("HorasContratadas")[0]);
            int fkUsuario = Integer.parseInt(parameters.get("FkUsuario")[0]);
            Contrato addData = new Contrato(descricao, infDate, supDate, hrsContratadas, fkUsuario);
            Contrato resultAdd = this.aplication.addData(addData);
        }
        else {
            throw new Exception("O metodo informado nao eh valido.");
        }
    }

    //METODOS
    @Override
    public Contrato searchById(int id) {

        if(id > 0) {
            Contrato resultSearch = null;
            resultSearch = this.aplication.getById(id);
            return resultSearch;
        }
        else {
            throw new ValueException("O contrato informado eh invalido.");
        }
    }

    @Override
    public List<Contrato> searchAll() {

        List<Contrato> resultSearch = null;
        resultSearch = this.aplication.getAll(this.limit, this.offsetAll);
        return resultSearch;
    }

    @Override
    public JSONObject toJson(Contrato data) {

        return this.aplication.parseDataToJSON(data);
    }

    @Override
    public List<JSONObject> toJsonList(List<Contrato> listData) {

        return this.aplication.parseListToJSONList(listData);
    }

    public List<Contrato> searchByUserId(int idUser) {

        if(idUser > 0) {
            List<Contrato> resultSearch = null;
            resultSearch = this.aplication.getByUser(idUser, this.limit, this.offsetUser);
            return resultSearch;
        }
        else {
            throw new ValueException("O usuario informado nao eh valido.");
        }
    }

    public List<Contrato> searchByDescription(String description) {

        if(!description.isEmpty()) {
            List<Contrato> resultSearch = null;
            resultSearch = this.aplication.getByDescription(description, this.limit, this.offsetDescription);
            return resultSearch;
        }
        else {
            throw new InvalidParameterException("Informe uma descricao.");
        }
    }

    public List<Contrato> searchByDate(LocalDate infDate, LocalDate supDate) {

        if(supDate.isAfter(infDate)) {
            List<Contrato> resultSearch = null;
            resultSearch = this.aplication.getByDate(infDate, supDate, this.limit, this.offsetDate);
            return resultSearch;
        }
        else {
            throw new DateTimeException("O intervalo de data informado nao eh valido.");
        }
    }

}
