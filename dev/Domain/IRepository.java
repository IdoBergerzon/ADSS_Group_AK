package Domain;

import java.util.*;

public interface IRepository<T> {
    void add(T t);
    void remove(int id);
    void update(T t);
    T get(int id);
    List<T> getAll();
}
