package Domain;

public interface IRepository {
    void add(Object o);
    void remove(Object o);
    void update(Object o);
    Object get(int id);
}
