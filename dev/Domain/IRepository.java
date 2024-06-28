package Domain;

public interface IRepository<T,Y> {

    T get(Y unique);
    void add(T unique);
    void remove(Y unique);
}
