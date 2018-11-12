package AplicationService;

import Dominio.Entidades.Servico;
import Dominio.Enum.ESubtipoServico;
import Dominio.Enum.ETipoServico;
import Infraestrutura.Postgre.DAO.ServicoDAO;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ServicoApl extends GenericApl<Servico> {
    //CONSTRUTOR
    public ServicoApl(){
        this.setDataDAO(new ServicoDAO());
    }

    public List<Servico> getByUserId(int userId,int limit, int offset){
        List<Servico> resultSearch = null;
        try{
            ServicoDAO currentDao = (ServicoDAO)this.getDataDAo();
            resultSearch = currentDao.obterTodosPorUsuario(userId, limit, offset);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }

    public List<Servico> getByTitle(String title,int limit, int offset){
        List<Servico> resultSearch = null;
        try{
            ServicoDAO currentDao = (ServicoDAO)this.getDataDAo();
            resultSearch = currentDao.obterTodosPorTitulo(title,limit,offset);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }

    public List<Servico> getByDescription(String descricao, int limit, int offset){
        List<Servico> resultSearch = null;
        try{
            ServicoDAO currentDao = (ServicoDAO)this.getDataDAo();
            resultSearch = currentDao.obterTodosPorDescricao(descricao,limit,offset);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }

    public List<Servico> getByPrice(double minValor, double maxValor, int limit, int offset){
        List<Servico> resultSearch = null;
        try{
            ServicoDAO currentDao = (ServicoDAO)this.getDataDAo();
            resultSearch = currentDao.obterTodosPorValor(minValor, maxValor, limit, offset);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }

    public List<Servico> getByType(ETipoServico type, int limit, int offset){
        List<Servico> resultSearch = null;
        try{
            ServicoDAO currentDao = (ServicoDAO)this.getDataDAo();
            resultSearch = currentDao.obterTodosPorTipo(type, limit, offset);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }

    public List<Servico> getBySubtype(ESubtipoServico subtype, int limit, int offset){
        List<Servico> resultSearch = null;
        try{
            ServicoDAO currentDao = (ServicoDAO)this.getDataDAo();
            resultSearch = currentDao.obterTodosPorSubtipo(subtype, limit, offset);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }

    @Override
    public JSONObject parseDataToJSON(Servico data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ID",data.getId());
        jsonObject.put("FkContrato",data.getFkContrato());
        jsonObject.put("TipoServico",data.getTipo().getTipo());
        List<ESubtipoServico> subtipos = data.getSubtipos();
        int temp = 0;
        for (ESubtipoServico sbTipo : subtipos){
            jsonObject.put("Subtipo"+temp, sbTipo.getSubtipo());
            temp++;
        }
        jsonObject.put("Titulo",data.getTitulo());
        jsonObject.put("Descricao",data.getDescricao());
        jsonObject.put("Valor",data.getValor());
        return jsonObject;
    }

    @Override
    public List<JSONObject> parseListToJSONList(List<Servico> dataList) {
        List<JSONObject> listJSONOutput = new ArrayList<JSONObject>();
        for (Servico servico : dataList){
            listJSONOutput.add(this.parseDataToJSON(servico));
        }
        return listJSONOutput;
    }
}
