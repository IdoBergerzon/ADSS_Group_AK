package DataAccessObject;

import java.sql.SQLException;

public interface IDAO<T> {
    void add(T t) throws SQLException;
    void remove(T t) throws SQLException;
    void update(T t) throws SQLException;
    Object get(int id) throws SQLException;
}
