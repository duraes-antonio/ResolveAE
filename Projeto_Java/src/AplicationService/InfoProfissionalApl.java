package AplicationService;

import Dominio.Entidades.InfoProfissional;
import Dominio.Enum.ETipoInfoProfissional;
import Infraestrutura.Postgre.DAO.InfoProfissionalDAO;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InfoProfissionalApl extends GenericApl<InfoProfissional> {
    //CONSTRUTOR
    public InfoProfissionalApl() {
        this.setDataDAO(new InfoProfissionalDAO());
    }
    //METODOS

    public List<InfoProfissional> getByType(ETipoInfoProfissional type, int limit, int offset) {
        List<InfoProfissional> resultSearch = null;
        try {
            InfoProfissionalDAO currentDao = (InfoProfissionalDAO) this.getDataDAo();
            resultSearch = currentDao.obterTodosPorTipo(type, limit, offset);
        } catch (Exception erro) {
            resultSearch = null;
        } finally {
            return resultSearch;
        }
    }

    public List<InfoProfissional> getByUser(int idUser, int limit, int offset) {
        List<InfoProfissional> resultSearch = null;
        try {
            InfoProfissionalDAO currentDao = (InfoProfissionalDAO) this.getDataDAo();
            resultSearch = currentDao.obterTodosPorUsuario(idUser, limit, offset);
        } catch (Exception erro) {
            resultSearch = null;
        } finally {
            return resultSearch;
        }
    }

    public List<InfoProfissional> getByUserNType(int idUser, ETipoInfoProfissional type, int limit, int offset) {
        List<InfoProfissional> resultSearch = null;
        try {
            InfoProfissionalDAO currentDao = (InfoProfissionalDAO) this.getDataDAo();
            resultSearch = currentDao.obterTodosPorTipoEUsuario(type, idUser, limit, offset);
        } catch (Exception erro) {
            resultSearch = null;
        } finally {
            return resultSearch;
        }
    }

    public List<InfoProfissional> getByDate(LocalDate infDate, LocalDate supDate, int limit, int offset) {
        List<InfoProfissional> resultSearch = null;
        try {
            InfoProfissionalDAO currentDao = (InfoProfissionalDAO) this.getDataDAo();
            resultSearch = currentDao.obterTodosPorData(infDate, supDate, limit, offset);
        } catch (Exception erro) {
            resultSearch = null;
        } finally {
            return resultSearch;
        }
    }

    @Override
    public JSONObject parseDataToJSON(InfoProfissional data) {
        JSONObject jsonOutput = new JSONObject();
        jsonOutput.put("ID", data.getId());
        jsonOutput.put("FkUsuario", data.getFkUsuario());
        jsonOutput.put("FkTipoInfo", data.getFkTipoInfo());
        jsonOutput.put("TipoInformacaoProf", data.getTipoInfoProfissional().getTipo());
        jsonOutput.put("Descricao", data.getDescricao());
        jsonOutput.put("DataInicio", data.getDataInicio());
        jsonOutput.put("DataFim", data.getDataFim());
        return jsonOutput;
    }

    @Override
    public List<JSONObject> parseListToJSONList(List<InfoProfissional> dataList) {
        List<JSONObject> listJSONOutput = new ArrayList<JSONObject>();
        for (InfoProfissional infoPro : dataList) {
            listJSONOutput.add(this.parseDataToJSON(infoPro));
        }
        return listJSONOutput;
    }
}
