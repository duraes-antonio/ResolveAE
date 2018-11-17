package AplicationService;

import Dominio.Entidades.Contrato;
import Infraestrutura.Postgre.DAO.ContratoDAO;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContratoApl extends GenericApl<Contrato> {
    //CONSTRUTORES
    public ContratoApl() {
        this.setDataDAO(new ContratoDAO());
    }

    //METODOS
    public List<Contrato> getByUser(int idUser, int limit, int offset) {
        List<Contrato> resultSearch = null;
        try {
            ContratoDAO currentDao = (ContratoDAO) this.getDataDAo();
            resultSearch = currentDao.obterTodosPorUsuario(idUser, limit, offset);
        } catch (Exception erro) {
            resultSearch = null;
        } finally {
            return resultSearch;
        }
    }

    public List<Contrato> getByDescription(String description, int limit, int offset) {
        List<Contrato> resultSearch = null;
        try {
            ContratoDAO currentDao = (ContratoDAO) this.getDataDAo();
            resultSearch = currentDao.obterTodosPorDescricao(description, limit, offset);
        } catch (Exception erro) {
            resultSearch = null;
        } finally {
            return resultSearch;
        }
    }

    public List<Contrato> getByDate(LocalDate infDate, LocalDate supDate, int limit, int offset) {
        List<Contrato> resultSearch = null;
        try {
            ContratoDAO currentDao = (ContratoDAO) this.getDataDAo();
            resultSearch = currentDao.obterTodosPorData(infDate, supDate, limit, offset);
        } catch (Exception erro) {
            resultSearch = null;
        } finally {
            return resultSearch;
        }
    }

    @Override
    public JSONObject parseDataToJSON(Contrato data) {
        JSONObject jsonOutput = new JSONObject();
        jsonOutput.put("ID", data.getId());
        jsonOutput.put("FkUsuario", data.getFkUsuario());
        jsonOutput.put("HorasContratadas", data.getHorasContratadas());
        jsonOutput.put("Descricao", data.getDescricao());
        jsonOutput.put("DataUltimaModif", data.getDataUltimaModif());
        jsonOutput.put("DataInicio", data.getDataInicio());
        jsonOutput.put("DataFim", data.getDataFim());
        return jsonOutput;
    }

    @Override
    public List<JSONObject> parseListToJSONList(List<Contrato> dataList) {
        List<JSONObject> listJSONOutput = new ArrayList<JSONObject>();
        for (Contrato contrato : dataList) {
            listJSONOutput.add(this.parseDataToJSON(contrato));
        }
        return listJSONOutput;
    }
}
