package DataAccessObject;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface IDAO<T> {
    void add(T t) throws SQLException;
    void remove(int id) throws SQLException;
    void update(T t) throws SQLException;
    Object get(int id) throws SQLException;
    HashMap<Integer,T> getAll() throws SQLException;
}
