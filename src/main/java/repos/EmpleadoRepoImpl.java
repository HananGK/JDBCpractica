package repos;

import modelos.Empleado;
import util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoRepoImpl implements RepoCRUD<Empleado, Integer>{
    private Connection obtenerConexion() throws SQLException {
        return ConexionBD.getConexion();
    }

    @Override
    public List<Empleado> listar() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleado ORDER BY codigo_empleado";
        try (PreparedStatement statement = obtenerConexion().prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Empleado empleado = cargarEmpleado(rs);
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar los empleados", e);
        }
        return empleados;
    }

    @Override
    public Empleado obtenerPorId(Integer id) {
        Empleado empleado;
        String query = "SELECT * FROM empleado WHERE codigo_empleado = ?";
        try(PreparedStatement stmt = obtenerConexion().prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            empleado = cargarEmpleado(rs);
        }
        catch (Exception e) {
            throw new RuntimeException("Error al obtener el empleado por ID", e);
        }
        return empleado;
    }

    @Override
    public void crear(Empleado empleado) {
        empleado.setCodigoEmpleado(listar().getLast().getCodigoEmpleado()+1);
        String query = """
                INSERT INTO empleado(codigo_empleado, nombre, apellido1, apellido2, extension, email, codigo_oficina, codigo_jefe, puesto)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try(PreparedStatement stmt = obtenerConexion().prepareStatement(query)){
            stmt.setInt(1, empleado.getCodigoEmpleado());
            stmt.setString(2, empleado.getNombre());
            stmt.setString(3, empleado.getApellido1());
            stmt.setString(4, empleado.getApellido2());
            stmt.setString(5, empleado.getExtension());
            stmt.setString(6, empleado.getEmail());
            stmt.setString(7, empleado.getCodigoOficina());
            stmt.setInt(8, empleado.getCodigoJefe());
            stmt.setString(9, empleado.getPuesto());

            stmt.executeUpdate();
            System.out.println("Empleado creado correctamente.");
        }
        catch (Exception e){
            throw new RuntimeException("Error al crear el empleado", e);
        }
    }

    @Override
    public void eliminar(Integer id) {
        String query = "DELETE FROM empleado WHERE codigo_empleado = ?";

        try(PreparedStatement stmt = obtenerConexion().prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Empleado eliminado correctamente.");
        } catch (Exception e){
            throw new RuntimeException("Error al eliminar el empleado", e);
        }
    }

    @Override
    public void actualizar(Empleado empleado) {
        String query = "UPDATE empleado SET nombre = ?, apellido1 = ?, apellido2 =?, extension = ?, email = ?, codigo_oficina = ?, codigo_jefe = ?, puesto = ? WHERE codigo_empleado = ? ";

        try (PreparedStatement stmt = obtenerConexion().prepareStatement(query)){
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido1());
            stmt.setString(3, empleado.getApellido2());
            stmt.setString(4, empleado.getExtension());
            stmt.setString(5, empleado.getEmail());
            stmt.setString(6, empleado.getCodigoOficina());
            stmt.setInt(7, empleado.getCodigoJefe());
            stmt.setString(8, empleado.getPuesto());
            stmt.setInt(9, empleado.getCodigoEmpleado());
            stmt.executeUpdate();
            System.out.println("Empleado actualizado correctamente.");
        }
        catch (Exception e){
            throw new RuntimeException("Error al actualizar el empleado", e);
        }
    }

    private Empleado cargarEmpleado(ResultSet rs) throws SQLException {
        Empleado empleado = new Empleado();
        empleado.setCodigoEmpleado(rs.getInt("codigo_empleado"));
        empleado.setNombre(rs.getString("nombre"));
        empleado.setApellido1(rs.getString("apellido1"));
        empleado.setApellido2(rs.getString("apellido2") != null ? rs.getString("apellido2") : null);
        empleado.setExtension(rs.getString("extension"));
        empleado.setEmail(rs.getString("email"));
        empleado.setCodigoOficina(rs.getString("codigo_oficina"));
        empleado.setCodigoJefe(rs.getObject("codigo_jefe") != null ? rs.getInt("codigo_jefe") : null);
        empleado.setPuesto(rs.getString("puesto") != null ? rs.getString("puesto") : null);
        return empleado;
    }
}