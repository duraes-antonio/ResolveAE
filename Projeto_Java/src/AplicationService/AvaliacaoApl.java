package AplicationService;

import Dominio.Entidades.Avaliacao;
import Infraestrutura.Postgre.DAO.AvaliacaoDAO;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AvaliacaoApl extends  GenericApl<Avaliacao>{

    //CONSTRUTORES
    public AvaliacaoApl(){
        this.setDataDAO(new AvaliacaoDAO());
    }

    //METODOS
    public List<Avaliacao> getByUsuario(int idUsuario, int limit, int offset){
        List<Avaliacao> resultSearch = null;
        try{
            AvaliacaoDAO currentDao = (AvaliacaoDAO)this.getDataDAo();
            resultSearch = currentDao.obterTodasPorUsuario(idUsuario,limit,offset);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }

    public List<Avaliacao> getByServico(int idServico, int limit, int offset){
        List<Avaliacao> resultSearch = null;
        try{
            AvaliacaoDAO currentDao = (AvaliacaoDAO)this.getDataDAo();
            resultSearch = currentDao.obterTodasPorServico(idServico,limit,offset);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }

    @Override
    public JSONObject parseDataToJSON(Avaliacao data) {
        JSONObject jsonOutput = new JSONObject();
        jsonOutput.put("ID",data.getId());
        jsonOutput.put("FkServico",data.getFkServico());
        jsonOutput.put("FkUsuario",data.getFkUsuario());
        jsonOutput.put("Nota",data.getNota());

        if (data.getComentario() != null){
            jsonOutput.put("IdComentario",data.getComentario().getId());
            jsonOutput.put("Comentario",data.getComentario().getComentario());
        }
        return jsonOutput;
    }

    @Override
    public List<JSONObject> parseListToJSONList(List<Avaliacao> dataList) {
        List<JSONObject> lisJSONOutput = new ArrayList<>();
        for (Avaliacao avaliacao:dataList){
            lisJSONOutput.add(this.parseDataToJSON(avaliacao));
        }
        return lisJSONOutput;
    }
}
