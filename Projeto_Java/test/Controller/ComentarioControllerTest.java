package Controller;

import Dominio.Entidades.Comentario;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ComentarioControllerTest {
    ComentarioController controler;
    Comentario comentario;
    List<Comentario> comentarios;
    int idComentario;
    int idServico;
    int idAvaliacao;
    int idUser;

    @BeforeEach
    void setUp() {
        controler = new ComentarioController();
        idComentario = 1;
        idServico = 11;
        idAvaliacao = 1;
        idUser = 5;
    }

    @Test
    void searchComentarioById() {
        comentario = controler.searchById(idComentario);
        System.out.println(comentario);
    }

    @Test
    void searchComentarioByIdServico() {
        comentarios = controler.searchByIdServico(idServico);
        for (Comentario output : comentarios) {
            System.out.println(output);
        }
    }

    @Test
    void searchComentarioByIdAvaliacao() {
        comentario = controler.searchByIdAvaliacao(idAvaliacao);
        System.out.println(comentario);
    }

    @Test
    void searchComentariosByIdUser() {
        comentarios = controler.searchByIdUser(idUser);
        for (Comentario output : comentarios) {
            System.out.println(output);
        }
    }

    @Test
    void searchAllComentarios() {
        comentarios = controler.searchAll();
        for (Comentario output : comentarios) {
            System.out.println(output);
            System.out.println("--------------");
        }
    }

    @Test
    void toJson() {
        comentario = controler.searchById(idComentario);
        JSONObject json = controler.toJson(comentario);
        System.out.println(json.toString());
    }

    @Test
    void toJsonList() {
        comentarios = controler.searchAll();
        List<JSONObject> jsonList = controler.toJsonList(this.comentarios);
        for (JSONObject output : jsonList) {
            System.out.println(output.toString());
        }
    }

//    @Test
//    void executeMethodPost(){
//        String[] metodo = new String[1];
//        metodo[0] = "adicionar";
//        String[] comentario = new String[1];
//        comentario[0] = "teste";
//        String[] fkAvaliacao = new String[1];
//        fkAvaliacao[0] = "13";
//        Map<String, String[]> parametros = new HashMap();
//        parametros.put("method",metodo);
//        parametros.put("Comentario",comentario);
//        parametros.put("FkAvaliacao",fkAvaliacao);
//        try {
//            controler.executeMethodPost(parametros);
//        }
//        catch (Exception erro){
//            erro.printStackTrace();
//        }
//    }
}