package AplicationService;

import Dominio.Entidades.Endereco;
import Dominio.Enum.EEstado;
import Dominio.Interfaces.IEnderecoRepositorio;
import Infraestrutura.Postgre.DAO.EnderecoDAO;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EnderecoApl extends GenericApl<Endereco> {

    //CONSTRUTORES
    public EnderecoApl() {

        this.setDataDAO(new EnderecoDAO());
    }

    //METODOS
    public List<Endereco> getByBairro(String bairro, int limit, int offset) {

        List<Endereco> resultSearch = null;

        try {
            IEnderecoRepositorio currentDao = (IEnderecoRepositorio) this.getDataDAo();
            resultSearch = currentDao.obterTodosPorBairro(bairro, limit, offset);
        }
        catch (Exception erro) {
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }

    public List<Endereco> getByCidade(String cidade, int limit, int offset) {

        List<Endereco> resultSearch = null;
        try {
            IEnderecoRepositorio currentDao = (IEnderecoRepositorio) this.getDataDAo();
            resultSearch = currentDao.obterTodosPorCidade(cidade, limit, offset);
        }
        catch (Exception erro) {
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }

    public List<Endereco> getByEstado(EEstado estado, int limit, int offset) {

        List<Endereco> resultSearch = null;
        try {
            IEnderecoRepositorio currentDao = (IEnderecoRepositorio) this.getDataDAo();
            resultSearch = currentDao.obterTodosPorEstado(estado, limit, offset);
        }
        catch (Exception erro) {
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }

    public List<Endereco> getByCep(int cep, int limit, int offset) {

        List<Endereco> resultSearch = null;
        try {
            IEnderecoRepositorio currentDao = (IEnderecoRepositorio) this.getDataDAo();
            resultSearch = currentDao.obterTodosPorCep(cep, limit, offset);
        }
        catch (Exception erro) {
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }

    @Override
    public JSONObject parseDataToJSON(Endereco data) {

        JSONObject jsonOutput = new JSONObject();
        jsonOutput.put("ID", data.getId());
        jsonOutput.put("FkUsuario", data.getFkUsuario());
        jsonOutput.put("Bairro", data.getBairro());
        jsonOutput.put("Cidade", data.getCidade());
        jsonOutput.put("Estado", data.getEstado());
        jsonOutput.put("Cep", data.getCep());
        return jsonOutput;
    }

    @Override
    public List<JSONObject> parseListToJSONList(List<Endereco> dataList) {

        List<JSONObject> listJSONOutput = new ArrayList<JSONObject>();
        for(Endereco endereco : dataList) {
            listJSONOutput.add(this.parseDataToJSON(endereco));
        }
        return listJSONOutput;
    }

}
