package Controller;

import AplicationService.ContratoApl;
import Controller.Interfaces.IController;
import Dominio.Entidades.Contrato;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.security.InvalidParameterException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

public class ContratoController implements IController {
    //ATRIBUTOS
    private ContratoApl aplication = null;
    private int limit = 10;
    private int offsetAll = 0;
    private int offsetUser = 0;
    private int offsetDescription = 0;
    private int offsetDate = 0;

    //CONSTRUTORES
    public ContratoController(){
        this.aplication = new ContratoApl();
    }

    //METODOS
    @Override
    public Contrato searchById(int id) {
        if(id>0){
            Contrato resultSearch = null;
            resultSearch = (Contrato) this.aplication.getById(id);
            return resultSearch;
        }
        else{
            throw new ValueException("O contrato informado eh invalido.");
        }
    }

    @Override
    public List<Contrato> searchAll() {
        List<Contrato> resultSearch = null;
        resultSearch = this.aplication.getAll(this.limit,this.offsetAll);
        return resultSearch;
    }

    public List<Contrato> searchByUserId(int idUser){
        if(idUser>0){
            List<Contrato> resultSearch = null;
            resultSearch = this.aplication.getByUser(idUser,this.limit,this.offsetUser);
            return resultSearch;
        }
        else{
            throw  new ValueException("O usuario informado nao eh valido.");
        }
    }

    public List<Contrato> searchByDescription(String description){
        if(!description.isEmpty()){
            List<Contrato> resultSearch = null;
            resultSearch = this.aplication.getByDescription(description, this.limit, this.offsetDescription);
            return resultSearch;
        }
        else{
            throw new InvalidParameterException("Informe uma descricao.");
        }
    }

    public List<Contrato> searchByDate(LocalDate infDate, LocalDate supDate){
        if(supDate.isAfter(infDate)){
            List<Contrato> resultSearch = null;
            resultSearch = this.aplication.getByDate(infDate,supDate,this.limit,this.offsetDate);
            return resultSearch;
        }
        else {
            throw  new DateTimeException("O intervalo de data informado nao eh valido.");
        }
    }


}
