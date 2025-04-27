package repos;

import entidades.Oficina;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OficinaRepoImpl implements RepoCRUD<Oficina, String> {
    private Connection obtenerConexion() throws SQLException {
        return ConexionBD.getConexion();
    }

    @Override
    public List<Oficina> listar() {
        List<Oficina> oficinas = new ArrayList<>();
        String query = "SELECT * FROM oficina";
        try(PreparedStatement stmt = obtenerConexion().prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Oficina oficina = cargarOficina(rs);
                oficinas.add(oficina);
            }
        }
        catch (Exception e){
            throw new RuntimeException("Error al listar las oficinas", e);
        }
        return oficinas;
    }

    @Override
    public Optional<Oficina> obtenerPorId(String id) {
        String query = "SELECT * FROM oficina WHERE codigo_oficina = ?";
        try(PreparedStatement stmt = obtenerConexion().prepareStatement(query)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(cargarOficina(rs));
            } else {
                return Optional.empty();
            }
        }
        catch (Exception e){
            throw new RuntimeException("Error al obtener la oficina por id", e);
        }
    }

    @Override
    public String guardar(Oficina oficina) throws SQLException {
        String query;
        String queryIns = """
                INSERT INTO oficina(ciudad, pais, region, codigo_postal, telefono, linea_direccion1, linea_direccion2, codigo_oficina))
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;
        String queryUpd = "UPDATE oficina SET codigo_oficina = ?, ciudad = ?, pais = ?, region = ?, codigo_postal = ?, telefono = ?, linea_direccion1 = ?, linea_direccion2 = ? WHERE codigo_oficina = ?";

        if (oficina.getCodigoOficina() != null) {
            query = queryUpd;
        } else {
            query = queryIns;
        }
        try(PreparedStatement stmt = obtenerConexion().prepareStatement(query)){
            stmt.setString(1, oficina.getCodigoOficina());
            stmt.setString(2, oficina.getCiudad());
            stmt.setString(3, oficina.getPais());
            stmt.setString(4, oficina.getRegion());
            stmt.setString(5, oficina.getCodigoPostal());
            stmt.setString(6, oficina.getTelefono());
            stmt.setString(7, oficina.getLineaDireccion1());
            stmt.setString(8, oficina.getLineaDireccion2());
            stmt.setString(9, oficina.getCodigoOficina());
            stmt.executeUpdate();
            System.out.println("Oficina guardada correctamente.");
        }
        catch (Exception e){
            throw new RuntimeException("Error al guardar la oficina", e);
        }
        return oficina.getCodigoOficina();
    }

    @Override
    public void eliminar(String id) {
        String query = "DELETE FROM oficina WHERE codigo_oficina = ?";
        try(PreparedStatement stmt = obtenerConexion().prepareStatement(query)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("Oficina eliminada correctamente.");
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la oficina", e);
        }
    }


    private Oficina cargarOficina(ResultSet rs) throws SQLException {
        Oficina oficina = new Oficina();
        oficina.setCodigoOficina(rs.getString("codigo_oficina"));
        oficina.setCiudad(rs.getString("ciudad"));
        oficina.setPais(rs.getString("pais"));
        oficina.setRegion(rs.getString("region"));
        oficina.setCodigoPostal(rs.getString("codigo_postal"));
        oficina.setTelefono(rs.getString("telefono"));
        oficina.setLineaDireccion1(rs.getString("linea_direccion1"));
        oficina.setLineaDireccion2(rs.getString("linea_direccion2"));
        return oficina;
    }

}




