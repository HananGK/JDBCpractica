package repos;

import java.util.List;
import java.util.Optional;

public interface RepoCRUD<T, ID> {
    List<T> listar();
    Optional<T> obtenerPorId(ID id);
    void crear(T t);
    void eliminar(ID id);
    void actualizar(T t);
}
