package Controller;

import AplicationService.EnderecoApl;
import Controller.Interfaces.IController;
import Dominio.Entidades.Endereco;
import Dominio.Enum.EEstado;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class EnderecoController implements IController<Endereco> {
    //ATRIBUTOS
    private EnderecoApl aplication = null;
    private int limit = 10;
    private int offsetAll = 0;
    private int offsetBairro = 0;
    private int offsetCidade = 0;
    private int offsetEstado = 0;
    private int offsetCep = 0;

    //CONSTRUTORES
    public EnderecoController() {
        this.aplication = new EnderecoApl();
    }

    @Override
    public String executeMethodGet(Map<String, String[]> parameters) throws Exception {
        String jsonString = "";
        String methodName = parameters.get("method")[0];

        if (methodName.equalsIgnoreCase("searchById")) {
            int idEndereco = 0;
            try {
                idEndereco = Integer.parseInt(parameters.get("ID")[0]);
            } catch (Exception erro) {
                throw new ValueException("O endereco informado nao eh valido.");
            }
            Endereco resultSearch = this.searchById(idEndereco);
            jsonString = this.toJson(resultSearch).toString();
        } else if (methodName.equalsIgnoreCase("searchAll")) {
            List<Endereco> resultSearch = this.searchAll();
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for (JSONObject json : jsonList) {
                jsonString += json.toString() + "<br>";
            }
        } else if (methodName.equalsIgnoreCase("searchByBairro")) {
            String bairro = parameters.get("Bairro")[0];
            List<Endereco> resultSearch = this.searchByBairro(bairro);
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for (JSONObject json : jsonList) {
                jsonString += json.toString() + "<br>";
            }
        } else if (methodName.equalsIgnoreCase("searchByCidade")) {
            String cidade = parameters.get("Cidade")[0];
            List<Endereco> resultSearch = this.searchByCidade(cidade);
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for (JSONObject json : jsonList) {
                jsonString += json.toString() + "<br>";
            }
        } else if (methodName.equalsIgnoreCase("searchByEstado")) {
            EEstado estado = EEstado.valueOf(parameters.get("Estado")[0]);
            List<Endereco> resultSearch = this.searchByEstado(estado);
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for (JSONObject json : jsonList) {
                jsonString += json.toString() + "<br>";
            }
        } else if (methodName.equalsIgnoreCase("searchByCep")) {
            int cep = 0;
            try {
                cep = Integer.parseInt(parameters.get("Cep")[0]);
            } catch (Exception erro) {
                throw new ValueException("O CEP informado nao eh valido.");
            }
            List<Endereco> resultSearch = this.searchByCep(cep);
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
            int idEndereco = Integer.parseInt(parameters.get("ID")[0]);
            String bairro = parameters.get("Bairro")[0];
            String cidade = parameters.get("Cidade")[0];
            String estado = parameters.get("Estado")[0];
            int cep = Integer.parseInt(parameters.get("Cep")[0]);
            int fkUsuario = Integer.parseInt(parameters.get("FkUsuario")[0]);
            Endereco updateData = new Endereco(idEndereco, bairro, cidade, estado, cep, fkUsuario);
            this.aplication.updateData(updateData);
        } else if (methodName.equalsIgnoreCase("adicionar")) {
            String bairro = parameters.get("Bairro")[0];
            String cidade = parameters.get("Cidade")[0];
            String estado = parameters.get("Estado")[0];
            int cep = Integer.parseInt(parameters.get("Cep")[0]);
            int fkUsuario = Integer.parseInt(parameters.get("FkUsuario")[0]);
            Endereco addData = new Endereco(bairro, cidade, estado, cep, fkUsuario);
            Endereco resultAdd = this.aplication.addData(addData);
        } else {
            throw new Exception("O metodo informado nao eh valido.");
        }

    }

    //METODOS
    @Override
    public Endereco searchById(int id) {
        if (id > 0) {
            Endereco resultSearch = this.aplication.getById(id);
            return resultSearch;
        } else {
            throw new ValueException("O endereco informado nao eh valido.");
        }
    }

    @Override
    public List<Endereco> searchAll() {
        List<Endereco> resultSearch = this.aplication.getAll(this.limit, this.offsetAll);
        return resultSearch;
    }

    @Override
    public JSONObject toJson(Endereco data) {
        return this.aplication.parseDataToJSON(data);
    }

    @Override
    public List<JSONObject> toJsonList(List<Endereco> listData) {
        return this.aplication.parseListToJSONList(listData);
    }

    public List<Endereco> searchByBairro(String bairro) {
        if (!bairro.isEmpty()) {
            List<Endereco> resultSearch = this.aplication.getByBairro(bairro, this.limit, this.offsetBairro);
            return resultSearch;
        } else {
            throw new ValueException("Necessario informar um bairro para efetuar a busca.");
        }
    }

    public List<Endereco> searchByCidade(String cidade) {
        if (!cidade.isEmpty()) {
            List<Endereco> resultSearch = this.aplication.getByCidade(cidade, this.limit, this.offsetCidade);
            return resultSearch;
        } else {
            throw new ValueException("Necessario informar uma cidade para efetuar a busca.");
        }
    }

    public List<Endereco> searchByEstado(EEstado estado) {
        if (!estado.getNomeExtenso().isEmpty()) {
            List<Endereco> resultSearch = this.aplication.getByEstado(estado, this.limit, this.offsetEstado);
            return resultSearch;
        } else {
            throw new ValueException("Necessario informar um estado para efetuar a busca.");
        }
    }

    public List<Endereco> searchByCep(int cep) {
        if (cep > 0) {
            List<Endereco> resultSearch = this.aplication.getByCep(cep, this.limit, this.offsetCep);
            return resultSearch;
        } else {
            throw new ValueException("O CEP informado nao eh valido.");
        }
    }
}
