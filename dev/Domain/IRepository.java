package Domain;

import java.util.Collection;

public interface IRepository<T> {
    void add(T t);
    void remove(T t);
    void update(T t);
    T get(int id);
    Collection<T> getAll();
}
