package ctn.infaut.DAO;

import ctn.infaut.connection.Conexion;
import ctn.infaut.controllers.Aula;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AulaDAO {
    public boolean insertar(Aula a) throws SQLException {
        String sql = "INSERT INTO infaut.aula (descripcion) VALUES (?)";
        try (PreparedStatement pstmt = Conexion.getCon().prepareStatement(sql)) {
            pstmt.setString(1, a.getDescripcion());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Ocurrio un error al insertar el aula: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(Aula a) throws SQLException {
        String sql = "DELETE FROM infaut.aula WHERE id_aula = ?";
        try (PreparedStatement pstmt = Conexion.getCon().prepareStatement(sql)) {
            pstmt.setInt(1, a.getIdAula());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Ocurrio un error al eliminar el aula: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Aula> consulta() throws SQLException {
        ArrayList<Aula> rooms = new ArrayList<>();
        String sql = "SELECT * FROM infaut.aula WHERE 1=1";

        try (Statement stmt = Conexion.getCon().createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                rooms.add(new Aula(
                        rs.getInt("id_aula"),
                        rs.getString("descripcion")));
            }
            return rooms;
        } catch (SQLException e) {
            System.err.println("Ocurrio un error al obtener las filas: " + e.getMessage());
            return null;
        }
    }

    public boolean modificar(Aula a) throws SQLException {
        String sql = "UPDATE infaut.aula SET descripcion = ? WHERE id_aula = ?";
        try (PreparedStatement pstmt = Conexion.getCon().prepareStatement(sql)) {
            pstmt.setString(1, a.getDescripcion());
            pstmt.setInt(2, a.getIdAula());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Ocurrio un error al modificar el aula: " + e.getMessage());
            return false;
        }
    }
}
