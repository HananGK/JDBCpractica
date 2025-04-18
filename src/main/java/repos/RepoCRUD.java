package repos;

import java.util.List;

public interface RepoCRUD<T, ID> {
    List<T> listar();
    T obtenerPorId(ID id);
    void crear(T t);
    void eliminar(ID id);
    void actualizar(T t);
}
