package DataAccessObject;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {
    void add(T t) throws SQLException;
    void remove(T t) throws SQLException;
    void update(T t) throws SQLException;
    Object get(int id) throws SQLException;
    List<T> getAll() throws SQLException;
}
