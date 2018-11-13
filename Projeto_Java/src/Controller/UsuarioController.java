package Controller;

import Controller.Interfaces.IController;
import Dominio.Entidades.Usuario;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class UsuarioController implements IController<Usuario> {

    @Override
    public String executeMethodGet(Map<String, String[]> parameters) throws Exception {
        return null;
    }

    @Override
    public void executeMethodPost(Map<String, String[]> parameters) {

    }

    @Override
    public Usuario searchById(int id) {
        return null;
    }

    @Override
    public List<Usuario> searchAll() {
        return null;
    }

    @Override
    public JSONObject toJson(Usuario data) {
        return null;
    }

    @Override
    public List<JSONObject> toJsonList(List<Usuario> listData) {
        return null;
    }
}
