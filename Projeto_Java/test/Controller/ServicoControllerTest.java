package Controller;

import org.junit.jupiter.api.BeforeEach;

class ServicoControllerTest {

    ServicoController controller;

    @BeforeEach
    void setUp() {
        controller = new ServicoController();
    }

//    @Test
//    void executeMethodPost() {
//        String[] metodo = new String[1];
//        metodo[0] = "adicionar";
//        String[] titulo = new String[1];
//        titulo[0] = "TituloTeste";
//        String[] descricao = new String[1];
//        descricao[0] = "DescricaoTeste";
//        String[] subTipos = new String[2];
//        subTipos[0] = "Manutenção Preventiva e Corretiva";
//        subTipos[1] = "Otimização e Análise";
//        String[] valor = new String[1];
//        valor[0] = "777";
//        String[] tipoServico = new String[1];
//        tipoServico[0] = "Design";
//        String[] fkUsuario = new String[1];
//        fkUsuario[0] = "1";
//        Map<String, String[]> parametros = new HashMap<>();
//        parametros.put("Titulo", titulo);
//        parametros.put("Descricao", descricao);
//        parametros.put("Subtipo", subTipos);
//        parametros.put("Valor", valor);
//        parametros.put("TipoServico", tipoServico);
//        parametros.put("FkUsuario", fkUsuario);
//        parametros.put("method", metodo);
//        try {
//            this.controller.executeMethodPost(parametros);
//        } catch (Exception erro) {
//            erro.printStackTrace();
//        }
//    }
}