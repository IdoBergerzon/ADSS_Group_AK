package Data;


public interface IDao<T,Y> {
    public Y search(T unique);
    public void insert(T unique);
    public void remove(T unique);

}
