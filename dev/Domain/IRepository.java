package Domain;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface IRepository {
    void add(Object o);
    void remove(Object o);
    void update(Object o);
    Object get(int id);
    Set<Object> getAll();
}
