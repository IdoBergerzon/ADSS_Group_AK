package DAL.HR;


public interface IDao<T,Y> {
    T search(Y unique);
    void insert(T unique);
    void remove(Y unique);

    //Role search(Integer unique);
}
