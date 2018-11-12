package Controller;

import AplicationService.EnderecoApl;
import Controller.Interfaces.IController;
import Dominio.Entidades.Endereco;
import Dominio.Enum.EEstado;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.util.List;

public class EnderecoController implements IController {
    //ATRIBUTOS
    private EnderecoApl aplication = null;
    private int limit = 10;
    private int offsetAll = 0;
    private int offsetBairro = 0;
    private int offsetCidade = 0;
    private int offsetEstado = 0;
    private int offsetCep = 0;

    //CONSTRUTORES
    public EnderecoController(){
        this.aplication = new EnderecoApl();
    }

    //METODOS
    @Override
    public Endereco searchById(int id) {
        if(id > 0){
            Endereco resultSearch = (Endereco) this.aplication.getById(id);
            return resultSearch;
        }
        else{
            throw new ValueException("O endereco informado nao eh valido.");
        }
    }

    @Override
    public List<Endereco> searchAll() {
        List<Endereco> resultSearch = this.aplication.getAll(this.limit,this.offsetAll);
        return resultSearch;
    }

    public List<Endereco> searchByBairro (String bairro){
        if(!bairro.isEmpty()){
            List<Endereco> resultSearch = this.aplication.getByBairro(bairro,this.limit, this.offsetBairro);
            return resultSearch;
        }
        else{
            throw new ValueException("Necessario informar um bairro para efetuar a busca.");
        }
    }

    public List<Endereco> searchByCidade (String cidade){
        if(!cidade.isEmpty()){
            List<Endereco> resultSearch = this.aplication.getByCidade(cidade, this.limit, this.offsetCidade);
            return resultSearch;
        }
        else{
            throw new ValueException("Necessario informar uma cidade para efetuar a busca.");
        }
    }

    public List<Endereco> searchByEstado (EEstado estado){
        if(!estado.getNomeExtenso().isEmpty()){
            List<Endereco> resultSearch = this.aplication.getByEstado(estado, this.limit,this.offsetEstado);
            return resultSearch;
        }
        else{
            throw new ValueException("Necessario informar um estado para efetuar a busca.");
        }
    }

    public List<Endereco> searchByCep(int cep){
        if(cep>0){
            List<Endereco> resultSearch = this.aplication.getByCep(cep, this.limit, this.offsetCep);
            return  resultSearch;
        }
        else{
            throw new ValueException("O CEP informado nao eh valido.");
        }
    }
}
