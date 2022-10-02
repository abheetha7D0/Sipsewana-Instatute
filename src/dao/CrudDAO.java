package dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO <T,Id> extends SuperDAO {
    public boolean add(T t) throws IOException;
    public boolean update(T t) throws IOException;
    public boolean delete(Id id) throws IOException;

    public List<T> getAll() throws IOException;
}
