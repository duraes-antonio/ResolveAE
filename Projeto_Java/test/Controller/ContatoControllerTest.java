package Controller;

import Dominio.Entidades.Contato;
import Dominio.Enum.ETipoContato;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ContatoControllerTest {

    ContatoController controller;
    Contato contato;
    List<Contato> contatos;
    int idContato;
    ETipoContato type;
    int idUser;

    @BeforeEach
    void setUp() {
        this.controller = new ContatoController();
        this.idContato = 10;
        this.type = ETipoContato.TELEFONE;
        this.idUser = 10;
    }

    @Test
    void searchById() {
        this.contato = this.controller.searchById(this.idContato);
        System.out.println(this.contato);
    }

    @Test
    void searchAll() {
        this.contatos = this.controller.searchAll();
        for (Contato output : this.contatos) {
            System.out.println(output);
        }
    }

    @Test
    void toJson() {
        this.contato = this.controller.searchById(this.idContato);
        JSONObject json = this.controller.toJson(this.contato);
        System.out.println(json.toString());
    }

    @Test
    void toJsonList() {
        this.contatos = this.controller.searchByType(this.type);
        List<JSONObject> jsonList = this.controller.toJsonList(this.contatos);
        for (JSONObject output : jsonList) {
            System.out.println(output.toString());
        }
    }

    @Test
    void searchByType() {
        this.contatos = this.controller.searchByType(this.type);
        for (Contato output : this.contatos) {
            System.out.println(output);
        }
    }

    @Test
    void searchByUser() {
        this.contatos = this.controller.searchByUser(this.idUser);
        for (Contato output : this.contatos) {
            System.out.println(output);
        }
    }

    @Test
    void searchByUserNType() {
        this.contatos = this.controller.searchByUserNType(this.idUser, this.type);
        for (Contato output : this.contatos) {
            System.out.println(output);
        }
    }

//    @Test
//    void executeMethodPost(){
//        String[] metodo = new String[1];
//        metodo[0]="adicionar";
//        String[] fkUsuario = new String[1];
//        fkUsuario[0] = "1";
//        String[] tipoContato = new String[1];
//        tipoContato[0] = "Celular";
//        String[] descricao = new String[1];
//        descricao[0] = "99999999";
//        Map<String, String[]> parametros = new HashMap();
//        parametros.put("method",metodo);
//        parametros.put("FkUsuario",fkUsuario);
//        parametros.put("TipoContato",tipoContato);
//        parametros.put("Descricao",descricao);
//        try {
//            this.controller.executeMethodPost(parametros);
//        }
//        catch (Exception erro){
//            erro.printStackTrace();
//        }
// }
}