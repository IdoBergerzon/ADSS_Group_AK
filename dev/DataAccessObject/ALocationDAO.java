package DataAccessObject;

import Domain.ALocation;

import java.sql.SQLException;
import java.util.List;

public class ALocationDAO implements IDAO<ALocation> {
    @Override
    public void add(ALocation aLocation) throws SQLException {

    }

    @Override
    public void remove(ALocation aLocation) throws SQLException {

    }

    @Override
    public void update(ALocation aLocation) throws SQLException {

    }

    @Override
    public ALocation get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<ALocation> getAll() throws SQLException {
        return List.of();
    }
}
