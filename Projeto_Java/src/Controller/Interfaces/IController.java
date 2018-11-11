package Controller.Interfaces;

import java.util.List;

public interface IController<T> {
    public T searchById(int id);
    public List<T> searchAll();
}
