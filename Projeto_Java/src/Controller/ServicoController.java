
package Controller;

import AplicationService.ServicoApl;
import Controller.Interfaces.IController;
import Dominio.Entidades.Contrato;
import Dominio.Entidades.Servico;
import Dominio.Enum.ESubtipoServico;
import Dominio.Enum.ETipoServico;
import Infraestrutura.Postgre.DAO.ContratoDAO;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    //CONSTRUTORES
    public ServicoController() {
        this.aplication = new ServicoApl();
    }

    //METODOS
    @Override
    public String executeMethodGet(Map<String, String[]> parameters) throws Exception {
        String jsonString = "";
        String methodName = parameters.get("method")[0];

        if (methodName.equalsIgnoreCase("searchById")) {
            int idServico = 0;
            try {
                idServico = Integer.parseInt(parameters.get("ID")[0]);
            } catch (Exception erro) {
                throw new ValueException("O servico informado nao eh valido");
            }
            Servico resultSearch = this.searchById(idServico);
            jsonString = this.toJson(resultSearch).toString();
        } else if (methodName.equalsIgnoreCase("searchAll")) {
            List<Servico> resultSearch = this.searchAll();
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for (JSONObject json : jsonList) {
                jsonString += json.toString() + "<br>";
            }
        } else if (methodName.equalsIgnoreCase("searchByUser")) {
            int idUser = 0;
            try {
                idUser = Integer.parseInt(parameters.get("FkUsuario")[0]);
            } catch (Exception erro) {
                throw new ValueException("O usuario informado nao eh valido");
            }
            List<Servico> resultSearch = this.searchByUser(idUser);
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for (JSONObject json : jsonList) {
                jsonString += json.toString() + "<br>";
            }
        } else if (methodName.equalsIgnoreCase("searchByTitle")) {
            String title = parameters.get("Titulo")[0];
            List<Servico> resultSearch = this.searchByTitle(title);
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for (JSONObject json : jsonList) {
                jsonString += json.toString() + "<br>";
            }
        } else if (methodName.equalsIgnoreCase("searchByDescription")) {
            String description = parameters.get("Descricao")[0];
            List<Servico> resultSearch = this.searchByDescription(description);
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for (JSONObject json : jsonList) {
                jsonString += json.toString() + "<br>";
            }
        } else if (methodName.equalsIgnoreCase("searchByPrice")) {
            double price = 0;
            try {
                price = Double.parseDouble(parameters.get("Valor")[0]);
            } catch (Exception erro) {
                throw new ValueException("Os precos informados nao sao validos.");
            }
            List<Servico> resultSearch = this.searchByPrice(price, price);
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for (JSONObject json : jsonList) {
                jsonString += json.toString() + "<br>";
            }
        } else if (methodName.equalsIgnoreCase("searchByType")) {
            ETipoServico type = ETipoServico.valueOf(parameters.get("TipoServico")[0]);
            List<Servico> resultSearch = this.searchByType(type);
            List<JSONObject> jsonList = this.toJsonList(resultSearch);
            for (JSONObject json : jsonList) {
                jsonString += json.toString() + "<br>";
            }
        } else if (methodName.equalsIgnoreCase("searchBySubtype")) {
            ESubtipoServico subtype = ESubtipoServico.valueOf(parameters.get("Subtipo")[0]);
            List<Servico> resultSearch = this.searchBySubtype(subtype);
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
            int idServico = Integer.parseInt(parameters.get("ID")[0]);
            String titulo = parameters.get("Titulo")[0];
            String descricao = parameters.get("Descricao")[0];
            String[] parametersSubtipo = parameters.get("Subtipo");
            List<ESubtipoServico> subtipos = new ArrayList<ESubtipoServico>();
            for (String subtipo : parametersSubtipo) {
                subtipos.add(ESubtipoServico.getByString(subtipo));
            }
            double valor = Double.parseDouble(parameters.get("Valor")[0]);
            int fkTipoServico = ETipoServico.getByString(parameters.get("TipoServico")[0]).getId();
            int fkUsuario = Integer.parseInt(parameters.get("FkUsuario")[0]);
            int fkContrato = -1;
            try {
                fkContrato = Integer.parseInt(parameters.get("FkContrato")[0]);
            } catch (Exception erro) {
                fkContrato = 0;
            }
            Servico updateData = new Servico(idServico, titulo, descricao, subtipos, valor, fkTipoServico, fkUsuario, fkContrato);
            this.aplication.updateData(updateData);
        } else if (methodName.equalsIgnoreCase("adicionar")) {
            String titulo = parameters.get("Titulo")[0];
            String descricao = parameters.get("Descricao")[0];
            String[] parametersSubtipo = parameters.get("Subtipo");
            List<ESubtipoServico> subtipos = new ArrayList<ESubtipoServico>();
            for (String subtipo : parametersSubtipo) {
                subtipos.add(ESubtipoServico.getByString(subtipo));
            }
            double valor = Double.parseDouble(parameters.get("Valor")[0]);
            int fkTipoServico = ETipoServico.getByString(parameters.get("TipoServico")[0]).getId();
            int fkUsuario = Integer.parseInt(parameters.get("FkUsuario")[0]);
            int fkContrato = -1;
            try {
                fkContrato = Integer.parseInt(parameters.get("FkContrato")[0]);

            } catch (Exception erro) {
                ContratoDAO contratoCreate = new ContratoDAO();
                Contrato contratoResult = contratoCreate.adicionar(new Contrato(fkUsuario));
                fkContrato = contratoResult.getId();
            }
            Servico addData = new Servico(titulo, descricao, subtipos, valor, fkTipoServico, fkUsuario, fkContrato);
            Servico resultAdd = this.aplication.addData(addData);
        } else {
            throw new Exception("O metodo informado nao eh valido.");
        }

    }

    //METODOS
    @Override
    public Servico searchById(int id) {
        if (id > 0) {
            Servico resultSearch = this.aplication.getById(id);
            return resultSearch;
        } else {
            throw new ValueException("O servico informado nao eh valido");
        }
    }

    @Override
    public List<Servico> searchAll() {
        List<Servico> resultSearch = this.aplication.getAll(this.limit, this.offsetAll);
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

    public List<Servico> searchByUser(int userId) {
        if (userId > 0) {
            List<Servico> resultSearch = this.aplication.getByUserId(userId, this.limit, this.offsetUser);
            return resultSearch;
        } else {
            throw new ValueException("O usuario informado nao eh valido");
        }
    }

    public List<Servico> searchByTitle(String title) {
        if (!title.isEmpty()) {
            List<Servico> resultSearch = this.aplication.getByTitle(title, this.limit, this.offsetTitle);
            return resultSearch;
        } else {
            throw new ValueException("Necessario informar um titulo para a pesquisa");
        }
    }

    public List<Servico> searchByDescription(String description) {
        if (!description.isEmpty()) {
            List<Servico> resultSearch = this.aplication.getByDescription(description, this.limit, this.offsetDescription);
            return resultSearch;
        } else {
            throw new ValueException("Necessario informar uma descricao para a pesquisa");
        }
    }

    public List<Servico> searchByPrice(double minPrice, double maxPrice) {
        if (maxPrice >= minPrice && minPrice >= 0) {
            List<Servico> resultSearch = this.aplication.getByPrice(minPrice, maxPrice, this.limit, this.offsetPrice);
            return resultSearch;
        } else {
            throw new ValueException("Os precos informados nao sao validos.");
        }
    }

    public List<Servico> searchByType(ETipoServico type) {
        if (type.getId() > 0) {
            List<Servico> resultSearch = this.aplication.getByType(type, this.limit, this.offsetType);
            return resultSearch;
        } else {
            throw new ValueException("O tipo de servico informado nao eh valido");
        }
    }

    public List<Servico> searchBySubtype(ESubtipoServico subtype) {
        if (subtype.getId() > 0) {
            List<Servico> resultSearch = this.aplication.getBySubtype(subtype, this.limit, this.offsetSubtype);
            return resultSearch;
        } else {
            throw new ValueException("O subtipo de servico informado nao eh valido");
        }
    }


}
