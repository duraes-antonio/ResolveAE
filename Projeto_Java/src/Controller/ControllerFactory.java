package Controller;

import Controller.Interfaces.IController;

public class ControllerFactory {
    public static IController createController(String typeController) {
        IController creation = null;
        if (typeController.equalsIgnoreCase("avaliacao") || typeController.equalsIgnoreCase("avaliação")) {
            creation = new AvaliacaoController();
        } else if (typeController.equalsIgnoreCase("comentario")) {
            creation = new ComentarioController();
        } else if (typeController.equalsIgnoreCase("contato")) {
            creation = new ContatoController();
        } else if (typeController.equalsIgnoreCase("contrato")) {
            creation = new ContratoController();
        } else if (typeController.equalsIgnoreCase("endereco") || typeController.equalsIgnoreCase("endereço")) {
            creation = new EnderecoController();
        } else if (typeController.equalsIgnoreCase("horario")) {
            creation = new HorarioController();
        } else if (typeController.equalsIgnoreCase("infoprofissional")) {
            creation = new InfoProfissionalController();
        } else if (typeController.equalsIgnoreCase("servico") || typeController.equalsIgnoreCase("serviço")) {
            creation = new ServicoController();
        } else if (typeController.equalsIgnoreCase("usuario")) {
            creation = new UsuarioController();
        }
        return creation;

    }
}
