package Controller;

import Controller.Interfaces.IController;
import Dominio.Entidades.Horario;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class HorarioController implements IController<Horario> {
    @Override
    public String executeMethodGet(Map<String, String[]> parameters) throws Exception {
        return null;
    }

    @Override
    public void executeMethodPost(Map<String, String[]> parameters) {

    }

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
