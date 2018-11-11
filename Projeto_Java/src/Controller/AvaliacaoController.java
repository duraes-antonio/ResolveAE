package Controller;

import AplicationService.AvaliacaoApl;
import Dominio.Entidades.Avaliacao;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

public class AvaliacaoController {
    private AvaliacaoApl aplication = null;
    public AvaliacaoController(){
        this.aplication = new AvaliacaoApl();
    }

    public Avaliacao searchIdAvaliacao(int id){
        if(id>0){
            Avaliacao avaliacaoSearch = null;
            //OS COISO DE BUSCA DA APLICACAO E EH ISTO
            return avaliacaoSearch;
        }
        else{
            throw new ValueException("Nao eh possivel efetuar busca para o item informado");
        }
    }
}
