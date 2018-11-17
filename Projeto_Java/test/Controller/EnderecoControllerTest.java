package Controller;

import org.junit.jupiter.api.BeforeEach;

class EnderecoControllerTest {
    EnderecoController controller;

    @BeforeEach
    void setUp() {
        controller = new EnderecoController();
    }
//    @Test
//    void executeMethodPost() {
//        String[] metodo = new String[1];
//        metodo[0] = "adicionar";
//        String[] bairro = new String[1];
//        bairro[0] = "Resistencia";
//        String[] cidade = new String[1];
//        cidade[0] = "Vitoria";
//        String[] estado = new String[1];
//        estado[0] = "Espírito Santo";
//        String[] cep = new String[1];
//        cep[0] = "29032600";
//        String[] fkUsuario = new String[1];
//        fkUsuario[0]= "1";
//        Map<String,String[]> parametro = new HashMap();
//        parametro.put("method",metodo);
//        parametro.put("Bairro",bairro);
//        parametro.put("Cidade",cidade);
//        parametro.put("Estado",estado);
//        parametro.put("Cep",cep);
//        parametro.put("FkUsuario",fkUsuario);
//        try {
//            this.controller.executeMethodPost(parametro);
//        }
//        catch (Exception erro){
//            erro.printStackTrace();
//        }
//
//    }
}