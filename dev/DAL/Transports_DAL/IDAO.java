package DAL.Transports_DAL;

import java.sql.SQLException;
import java.util.HashMap;

public interface IDAO<T> {
    void add(T t) throws SQLException;
    void remove(int id) throws SQLException;
    void update(T t) throws SQLException;
    Object get(int id) throws SQLException;
    HashMap<Integer,T> getAll() throws SQLException;
}
