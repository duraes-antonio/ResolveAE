package Controller.Interfaces;

import org.json.JSONObject;

import java.util.List;

public interface IController<T> {
    public T searchById(int id);
    public List<T> searchAll();
    public JSONObject toJson(T data);
    public List<JSONObject> toJsonList(List<T> listData);
}
