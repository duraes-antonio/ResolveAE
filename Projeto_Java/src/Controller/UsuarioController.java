package Controller;

import Controller.Interfaces.IController;
import Dominio.Entidades.Usuario;
import org.json.JSONObject;

import java.util.List;

public class UsuarioController implements IController<Usuario> {

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
