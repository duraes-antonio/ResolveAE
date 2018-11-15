package Controller;

import Dominio.Entidades.Avaliacao;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class AvaliacaoControllerTest {

    AvaliacaoController controller;
    Avaliacao avaliacao;
    List<Avaliacao> avaliacoes;
    int idAvalicao;
    int idServico;
    int idUser;

    @BeforeEach
    void setUp(){
        controller = new AvaliacaoController();
        idAvalicao = 1;
        idServico = 11;
        idUser = 5;
    }

    @Test
    void searchById() {
        this.avaliacao = this.controller.searchById(this.idAvalicao);
        System.out.println(this.avaliacao);
    }

    @Test
    void searchAll() {
        this.avaliacoes = this.controller.searchAll();
        for (Avaliacao output : this.avaliacoes){
            System.out.println(output);
        }
    }

    @Test
    void toJson() {
        this.avaliacao = this.controller.searchById(this.idAvalicao);
        JSONObject json = this.controller.toJson(this.avaliacao);
        System.out.println(json.toString());
    }

    @Test
    void toJsonList() {
        this.avaliacoes = this.controller.searchAll();
        List<JSONObject> jsonList = this.controller.toJsonList(this.avaliacoes);
        for(JSONObject output : jsonList){
            System.out.println(output.toString());
        }
    }

    @Test
    void searchByServico() {
        this.avaliacoes = this.controller.searchByServico(this.idServico);
        for (Avaliacao output : this.avaliacoes){
            System.out.println(output);
        }
    }

    @Test
    void searchByUser() {
        this.avaliacoes = this.controller.searchByUser(this.idUser);
        for (Avaliacao output : this.avaliacoes){
            System.out.println(output);
        }
    }
}