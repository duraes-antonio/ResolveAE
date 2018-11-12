package Controller;

import AplicationService.InfoProfissionalApl;
import Controller.Interfaces.IController;
import Dominio.Entidades.InfoProfissional;
import Dominio.Enum.ETipoInfoProfissional;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.time.LocalDate;
import java.util.List;

public class InfoProfissionalController implements IController {
    //ATRIBUTOS
    private InfoProfissionalApl aplication = null;
    private int limit = 10;
    private int offsetAll = 0;
    private int offsetType = 0;
    private int offsetUser = 0;
    private int offsetUserNType = 0;
    private int offsetData = 0;

    @Override
    public InfoProfissional searchById(int id) {
        if (id>0){
            InfoProfissional resultSearch = (InfoProfissional) this.aplication.getById(id);
            return resultSearch;
        }
        else{
            throw new ValueException("A informacao profissional socilitada nao eh valida");
        }
    }

    @Override
    public List<InfoProfissional> searchAll() {
        List<InfoProfissional> resultSearch = this.aplication.getAll(this.limit,this.offsetAll);
        return resultSearch;
    }

    public List<InfoProfissional>  searchByType(ETipoInfoProfissional type){
        if(type.getId()>0){
            List<InfoProfissional> resultSearch = this.aplication.getByType(type,this.limit,this.offsetType);
            return resultSearch;
        }
        else{
            throw  new ValueException("O tipo solicitado eh invalido");
        }
    }

    public List<InfoProfissional> searchByUser (int idUser){
        if(idUser>0){
            List<InfoProfissional> resultSearch = this.aplication.getByUser(idUser,this.limit,this.offsetUser);
            return  resultSearch;
        }
        else{
            throw new ValueException("Informe um usuario valido.");
        }
    }

    public List<InfoProfissional> searchByUserNTYpe(int idUser, ETipoInfoProfissional type){
        if(idUser>0 && type.getId()>0){
            List<InfoProfissional> resultSearch = this.aplication.getByUserNType(idUser,type,this.limit,this.offsetUserNType);
            return resultSearch;
        }
        else{
            throw new ValueException("Usuario ou tipo invalidos.");
        }
    }

    public List<InfoProfissional> searchByData(LocalDate infDate, LocalDate supDate){
        if(supDate.isAfter(infDate)){
            List<InfoProfissional> resultSearch = this.aplication.getByDate(infDate,supDate,this.limit,this.offsetData);
            return resultSearch;
        }
        else{
            throw new ValueException("Intervalo de data invalido.");
        }
    }
}
