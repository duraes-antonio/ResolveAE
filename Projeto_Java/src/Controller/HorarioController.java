package Controller;

import Controller.Interfaces.IController;
import Dominio.Entidades.Horario;
import org.json.JSONObject;

import java.util.List;

public class HorarioController implements IController<Horario> {
    @Override
    public Horario searchById(int id) {
        return null;
    }

    @Override
    public List<Horario> searchAll() {
        return null;
    }

    @Override
    public JSONObject toJson(Horario data) {
        return null;
    }

    @Override
    public List<JSONObject> toJsonList(List<Horario> listData) {
        return null;
    }
}
