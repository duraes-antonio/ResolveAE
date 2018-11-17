package Controller;

import AplicationService.InfoProfissionalApl;
import Controller.Interfaces.IController;
import Dominio.Entidades.InfoProfissional;
import Dominio.Enum.ETipoInfoProfissional;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class InfoProfissionalController implements IController<InfoProfissional> {
    //ATRIBUTOS
    private InfoProfissionalApl aplication = null;
    private int limit = 10;
    private int offsetAll = 0;
    private int offsetType = 0;
    private int offsetUser = 0;
    private int offsetUserNType = 0;
    private int offsetData = 0;

    public InfoProfissionalController() {
        this.aplication = new InfoProfissionalApl();
    }

    @Override
    public String executeMethodGet(Map<String, String[]> parameters) throws Exception {
        String jsonString = "";
        String methodName = parameters.get("method")[0];
        if (methodName.equalsIgnoreCase("searchById")) {
            int idInfoPro = 0;
            try {
                idInfoPro = Integer.parseInt(parameters.get("ID")[0]);
            } catch (Exception erro) {
                throw new ValueException("A informacao profissional socilitada nao eh valida");
            }
            InfoProfissional resultSearch = this.searchById(idInfoPro);
            jsonString = this.toJson(resultSearch).toString();

        } else if (methodName.equalsIgnoreCase("searchAll")) {
            List<InfoProfissional> resultSearch = this.searchAll();
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for (JSONObject json : jsonList) {
                jsonString += json.toString() + "<br>";
            }
        } else if (methodName.equalsIgnoreCase("searchByType")) {
            ETipoInfoProfissional type = ETipoInfoProfissional.valueOf(parameters.get("TipoInformacaoProf")[0]);
            List<InfoProfissional> resultSearch = this.searchByType(type);
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for (JSONObject json : jsonList) {
                jsonString += json.toString() + "<br>";
            }
        } else if (methodName.equalsIgnoreCase("searchByUser")) {
            int idUser = 0;
            try {
                idUser = Integer.parseInt(parameters.get("FkUsuario")[0]);
            } catch (Exception erro) {
                throw new ValueException("Informe um usuario valido.");
            }
            List<InfoProfissional> resultSearch = this.searchByUser(idUser);
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for (JSONObject json : jsonList) {
                jsonString += json.toString() + "<br>";
            }
        } else if (methodName.equalsIgnoreCase("searchByUserNTYpe")) {
            ETipoInfoProfissional type = ETipoInfoProfissional.valueOf(parameters.get("TipoInformacaoProf")[0]);
            int idUser = 0;
            try {
                idUser = Integer.parseInt(parameters.get("FkUsuario")[0]);
            } catch (Exception erro) {
                throw new ValueException("Usuario ou tipo invalidos.");
            }
            List<InfoProfissional> resultSearch = this.searchByUserNTYpe(idUser, type);
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for (JSONObject json : jsonList) {
                jsonString += json.toString() + "<br>";
            }
        } else if (methodName.equalsIgnoreCase("searchByData")) {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate infDate = LocalDate.parse(parameters.get("DataInicio")[0], dateFormat);
            LocalDate supDate = LocalDate.parse(parameters.get("DataFim")[0], dateFormat);
            List<InfoProfissional> resultSearch = this.searchByData(infDate, supDate);
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for (JSONObject json : jsonList) {
                jsonString += json.toString() + "<br>";
            }
        } else {
            throw new Exception("O metodo informado nao eh valido.");
        }


        return jsonString;
    }

    @Override
    public void executeMethodPost(Map<String, String[]> parameters) throws Exception {
        String methodName = parameters.get("method")[0];

        if (methodName.equalsIgnoreCase("atualizar")) {
            int idInfoPro = Integer.parseInt(parameters.get("ID")[0]);
            String descricao = parameters.get("Descricao")[0];
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate infDate = LocalDate.parse(parameters.get("DataInicio")[0], dateFormat);
            LocalDate supDate = LocalDate.parse(parameters.get("DataFim")[0], dateFormat);
            int fkTipoInfo = ETipoInfoProfissional.getByString(parameters.get("TipoInfo")[0]).getId();
            int fkUsuario = Integer.parseInt(parameters.get("FkUsuario")[0]);
            InfoProfissional updateData = new InfoProfissional(idInfoPro, descricao, infDate, supDate, fkTipoInfo, fkUsuario);
            this.aplication.updateData(updateData);
        } else if (methodName.equalsIgnoreCase("adicionar")) {
            String descricao = parameters.get("Descricao")[0];
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate infDate = LocalDate.parse(parameters.get("DataInicio")[0], dateFormat);
            LocalDate supDate = LocalDate.parse(parameters.get("DataFim")[0], dateFormat);
            int fkTipoInfo = ETipoInfoProfissional.getByString(parameters.get("TipoInfo")[0]).getId();
            int fkUsuario = Integer.parseInt(parameters.get("FkUsuario")[0]);
            InfoProfissional addData = new InfoProfissional(descricao, infDate, supDate, fkTipoInfo, fkUsuario);
            //addData.setTipoInfo(ETipoInfoProfissional.getByString(parameters.get("TipoInfo")[0]));
            InfoProfissional resultAdd = this.aplication.addData(addData);
        } else {
            throw new Exception("O metodo informado nao eh valido.");
        }

    }

    @Override
    public InfoProfissional searchById(int id) {
        if (id > 0) {
            InfoProfissional resultSearch = this.aplication.getById(id);
            return resultSearch;
        } else {
            throw new ValueException("A informacao profissional socilitada nao eh valida");
        }
    }

    @Override
    public List<InfoProfissional> searchAll() {
        List<InfoProfissional> resultSearch = this.aplication.getAll(this.limit, this.offsetAll);
        return resultSearch;
    }

    @Override
    public JSONObject toJson(InfoProfissional data) {
        return this.aplication.parseDataToJSON(data);
    }

    @Override
    public List<JSONObject> toJsonList(List<InfoProfissional> listData) {
        return this.aplication.parseListToJSONList(listData);
    }

    public List<InfoProfissional> searchByType(ETipoInfoProfissional type) {
        if (type.getId() > 0) {
            List<InfoProfissional> resultSearch = this.aplication.getByType(type, this.limit, this.offsetType);
            return resultSearch;
        } else {
            throw new ValueException("O tipo solicitado eh invalido");
        }
    }

    public List<InfoProfissional> searchByUser(int idUser) {
        if (idUser > 0) {
            List<InfoProfissional> resultSearch = this.aplication.getByUser(idUser, this.limit, this.offsetUser);
            return resultSearch;
        } else {
            throw new ValueException("Informe um usuario valido.");
        }
    }

    public List<InfoProfissional> searchByUserNTYpe(int idUser, ETipoInfoProfissional type) {
        if (idUser > 0 && type.getId() > 0) {
            List<InfoProfissional> resultSearch = this.aplication.getByUserNType(idUser, type, this.limit, this.offsetUserNType);
            return resultSearch;
        } else {
            throw new ValueException("Usuario ou tipo invalidos.");
        }
    }

    public List<InfoProfissional> searchByData(LocalDate infDate, LocalDate supDate) {
        if (supDate.isAfter(infDate)) {
            List<InfoProfissional> resultSearch = this.aplication.getByDate(infDate, supDate, this.limit, this.offsetData);
            return resultSearch;
        } else {
            throw new ValueException("Intervalo de data invalido.");
        }
    }
}
