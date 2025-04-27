package repos;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface RepoCRUD<T, ID> {
    List<T> listar();
    Optional<T> obtenerPorId(ID id);
    ID guardar(T t) throws SQLException;
    void eliminar(ID id);
}
