package repos;

import modelos.Cliente;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteRepoImpl implements RepoCRUD<Cliente, Integer> {
    private Connection obtenerConexion() throws SQLException {
        return ConexionBD.getConexion();
    }

    @Override
    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM cliente ORDER BY codigo_cliente";

        try (Statement stmt = obtenerConexion().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Cliente cliente = cargarCliente(rs);

                int codigoEmpleadoRepVentas = rs.getInt("codigo_empleado_rep_ventas");
                if (rs.wasNull()) {
                    cliente.setCodigoEmpleadoRepVentas(null);
                } else {
                    cliente.setCodigoEmpleadoRepVentas(codigoEmpleadoRepVentas);
                }

                float limiteCredito = rs.getFloat("limite_credito");
                if (rs.wasNull()) {
                    cliente.setLimiteCredito(null);
                } else {
                    cliente.setLimiteCredito(limiteCredito);
                }

                clientes.add(cliente);
            }
        }
        catch (Exception  e){
            throw new RuntimeException("Error al listar los clientes", e);
        }
        return clientes;
    }

    @Override
    public Cliente obtenerPorId(Integer id) {
        Cliente cliente;
        String query = "SELECT * FROM cliente WHERE codigo_cliente = ?";
        try(PreparedStatement stmt = obtenerConexion().prepareStatement(query)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            cliente = cargarCliente(rs);

        }
        catch (Exception  e){
            throw new RuntimeException("Error al obtener cliente por ID", e);
        }
        return cliente;
    }

    @Override
    public void crear(Cliente cliente) {
        cliente.setCodigoCliente(listar().getLast().getCodigoCliente()+1);
        String query = """
        INSERT INTO cliente (codigo_cliente, nombre_cliente, nombre_contacto, apellido_contacto, telefono, fax, linea_direccion1, linea_direccion2, ciudad, region, pais, codigo_postal, codigo_empleado_rep_ventas, limite_credito)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

        try (PreparedStatement stmt = obtenerConexion().prepareStatement(query)) {
            stmt.setInt(1, cliente.getCodigoCliente());
            stmt.setString(2, cliente.getNombreCliente());
            stmt.setString(3, cliente.getNombreContacto());
            stmt.setString(4, cliente.getApellidoContacto());
            stmt.setString(5, cliente.getTelefono());
            stmt.setString(6, cliente.getFax());
            stmt.setString(7, cliente.getLineaDireccion1());
            stmt.setString(8, cliente.getLineaDireccion2());
            stmt.setString(9, cliente.getCiudad());
            stmt.setString(10, cliente.getRegion());
            stmt.setString(11, cliente.getPais());
            stmt.setString(12, cliente.getCodigoPostal());
            stmt.setInt(13, cliente.getCodigoEmpleadoRepVentas());
            stmt.setDouble(14, cliente.getLimiteCredito());


            stmt.executeUpdate();
            System.out.println("Cliente creado correctamente.");
        } catch (SQLException e) {
            throw new RuntimeException("Error al crear el cliente", e);
        }
    }

    @Override
    public void eliminar(Integer id) {
        String query = "DELETE FROM cliente WHERE codigo_cliente = ?";

        try (PreparedStatement stmt = obtenerConexion().prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Cliente eliminado correctamente.");
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el cliente", e);
        }
    }

    @Override
    public void actualizar(Cliente cliente) {
        String query = "UPDATE cliente SET " +
                "nombre_cliente = ?, " +
                "nombre_contacto = ?, " +
                "apellido_contacto = ?, " +
                "telefono = ?, " +
                "fax = ?, " +
                "linea_direccion1 = ?, " +
                "linea_direccion2 = ?, " +
                "ciudad = ?, " +
                "region = ?, " +
                "pais = ?, " +
                "codigo_postal = ?, " +
                "codigo_empleado_rep_ventas = ?, " +
                "limite_credito = ? " +
                "WHERE codigo_cliente = ?";

        try (PreparedStatement stmt = obtenerConexion().prepareStatement(query)) {
            stmt.setString(1, cliente.getNombreCliente());
            stmt.setString(2, cliente.getNombreContacto());
            stmt.setString(3, cliente.getApellidoContacto());
            stmt.setString(4, cliente.getTelefono());
            stmt.setString(5, cliente.getFax());
            stmt.setString(6, cliente.getLineaDireccion1());
            stmt.setString(7, cliente.getLineaDireccion2());
            stmt.setString(8, cliente.getCiudad());
            stmt.setString(9, cliente.getRegion());
            stmt.setString(10, cliente.getPais());
            stmt.setString(11, cliente.getCodigoPostal());
            stmt.setInt(12, cliente.getCodigoEmpleadoRepVentas());
            stmt.setDouble(13, cliente.getLimiteCredito());
            stmt.setInt(14, cliente.getCodigoCliente());  // <- el WHERE

            stmt.executeUpdate();
            System.out.println("Cliente actualizado correctamente en la base de datos.");

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el cliente", e);
        }
    }


    private Cliente cargarCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setCodigoCliente(rs.getInt("codigo_cliente"));
        cliente.setNombreCliente(rs.getString("nombre_cliente"));
        cliente.setNombreContacto(rs.getString("nombre_contacto"));
        cliente.setApellidoContacto(rs.getString("apellido_contacto"));
        cliente.setTelefono(rs.getString("telefono"));
        cliente.setFax(rs.getString("fax"));
        cliente.setLineaDireccion1(rs.getString("linea_direccion1"));
        cliente.setLineaDireccion2(rs.getString("linea_direccion2"));
        cliente.setCiudad(rs.getString("ciudad"));
        cliente.setRegion(rs.getString("region"));
        cliente.setPais(rs.getString("pais"));
        cliente.setCodigoPostal(rs.getString("codigo_postal"));
        cliente.setCodigoEmpleadoRepVentas(rs.getInt("codigo_empleado_rep_ventas"));
        cliente.setLimiteCredito(rs.getFloat("limite_credito"));

        return cliente;
    }

    public List<String> listaDeClientesConOficina() throws SQLException {
        List<String> listaClientes = new ArrayList<>();
        String query = """
               SELECT cl.codigo_cliente, cl.nombre_cliente, cl.telefono, CONCAT(e.nombre, " ", e.apellido1) AS "Representante Ventas", o.codigo_oficina, o.codigo_postal
               FROM cliente cl
                   JOIN empleado e ON  cl.codigo_empleado_rep_ventas = e.codigo_empleado 
                   JOIN oficina o ON  e.codigo_oficina = o.codigo_oficina
               """;
        try (Statement stmt = obtenerConexion().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                listaClientes.add(
                        rs.getInt("codigo_cliente") + " - " +
                                rs.getString("nombre_cliente") + " - " +
                                rs.getString("telefono") + " " +
                                rs.getString("Representante Ventas") + " " +
                                rs.getString("codigo_oficina") + " " +
                                rs.getString("codigo_postal")
                );
            }
        }
        return listaClientes;
    }

    public Map<Integer, Map<String, String>> fichaClienteConOficina() throws SQLException {
        Map<Integer, Map<String, String>> listaFichasClientes = new HashMap<>();
        String query = """
                   SELECT cl.codigo_cliente, cl.nombre_cliente, cl.telefono, CONCAT(e.nombre, " ", e.apellido1) AS "Representante Ventas", o.codigo_oficina, o.codigo_postal
                   FROM cliente cl
                       JOIN empleado e ON  cl.codigo_empleado_rep_ventas = e.codigo_empleado 
                       JOIN oficina o ON  e.codigo_oficina = o.codigo_oficina
                   """;

        try (Statement stmt = obtenerConexion().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Map<String, String> ficha = new HashMap<>();

                listaFichasClientes.put(rs.getInt("codigo_cliente"), ficha);

                ficha.put("nombre_cliente", rs.getString("nombre_cliente"));
                ficha.put("telefono", rs.getString("telefono"));
                ficha.put("Representante Ventas", rs.getString("Representante Ventas"));
                ficha.put("codigo_oficina", rs.getString("codigo_oficina"));
                ficha.put("codigo_postal", rs.getString("codigo_postal"));

            }
        }
        return listaFichasClientes;
    }
}
