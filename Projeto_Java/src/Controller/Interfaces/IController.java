package Controller.Interfaces;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface IController<T> {
    public String executeMethodGet(Map<String,String[]> parameters) throws Exception;
    public void executeMethodPost(Map<String,String[]> parameters) throws Exception;
    public T searchById(int id);
    public List<T> searchAll();
    public JSONObject toJson(T data);
    public List<JSONObject> toJsonList(List<T> listData);

}
