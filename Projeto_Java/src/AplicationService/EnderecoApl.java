package AplicationService;

import Dominio.Entidades.Endereco;
import Dominio.Enum.EEstado;
import Infraestrutura.Postgre.DAO.EnderecoDAO;

import java.util.List;

public class EnderecoApl extends GenericApl {
    //CONSTRUTORES
    public EnderecoApl(){
        this.setDataDAO(new EnderecoDAO());
    }

    //METODOS
    public List<Endereco> getByBairro(String bairro, int limit, int offset){
        List<Endereco> resultSearch = null;
        try{
            EnderecoDAO currentDao = (EnderecoDAO)this.getDataDAo();
            resultSearch = currentDao.obterTodosPorBairro(bairro, limit,offset);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }
    public List<Endereco> getByCidade(String cidade, int limit, int offset){
        List<Endereco> resultSearch = null;
        try{
            EnderecoDAO currentDao = (EnderecoDAO)this.getDataDAo();
            resultSearch = currentDao.obterTodosPorCidade(cidade, limit, offset);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }
    public List<Endereco> getByEstado(EEstado estado, int limit, int offset){
        List<Endereco> resultSearch = null;
        try{
            EnderecoDAO currentDao = (EnderecoDAO)this.getDataDAo();
            resultSearch = currentDao.obterTodosPorEstado(estado, limit, offset);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }

    public List<Endereco> getByCep(int cep, int limit, int offset){
        List<Endereco> resultSearch = null;
        try{
            EnderecoDAO currentDao = (EnderecoDAO)this.getDataDAo();
            resultSearch = currentDao.obterTodosPorCep(cep, limit, offset);
        }
        catch (Exception erro){
            resultSearch = null;
        }
        finally {
            return resultSearch;
        }
    }
}
