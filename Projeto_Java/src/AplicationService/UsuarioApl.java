package AplicationService;

import Dominio.Entidades.Usuario;
import org.json.JSONObject;

import java.util.List;

public class UsuarioApl extends GenericApl<Usuario> {


    @Override
    public JSONObject parseDataToJSON(Usuario data) {

        return null;
    }

    @Override
    public List<JSONObject> parseListToJSONList(List<Usuario> dataList) {

        return null;
    }

}
