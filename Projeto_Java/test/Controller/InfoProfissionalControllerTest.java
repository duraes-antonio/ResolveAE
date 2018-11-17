package Controller;

import org.junit.jupiter.api.BeforeEach;

class InfoProfissionalControllerTest {
    InfoProfissionalController controller;

    @BeforeEach
    void setUp() {
        controller = new InfoProfissionalController();
    }

//    @Test
//    void executeMethodPost() {
//        String[] metodo = new String[1];
//        metodo[0] = "adicionar";
//        String[] descricao = new String[1];
//        descricao[0] = "TESTEZAO";
//        String[] dataInicio = new String[1];
//        dataInicio[0] = "2018-02-22";
//        String[] dataFim = new String[1];
//        dataFim[0] = "2018-02-21";
//        String[] tipoInfo = new String[1];
//        tipoInfo[0] = "Trabalho";
//        String[] fkUsuario = new String[1];
//        fkUsuario[0] = "1";
//        Map<String, String[]> parametros = new HashMap();
//        parametros.put("method", metodo);
//        parametros.put("Descricao", descricao);
//        parametros.put("DataInicio", dataInicio);
//        parametros.put("DataFim", dataFim);
//        parametros.put("TipoInfo", tipoInfo);
//        parametros.put("FkUsuario", fkUsuario);
//        try {
//            this.controller.executeMethodPost(parametros);
//        } catch (Exception erro) {
//            erro.printStackTrace();
//        }
//    }
}