package ctn.infaut.DAO;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ctn.infaut.connection.Conexion;
import ctn.infaut.controllers.Materia;

public class MateriaDAO {
    public static boolean insertar(Materia m) {
        String sql = "INSERT INTO infaut.materia" +
                "(nombre, hora_inicio, hora_fin, aula_id_aula) VALUES" +
                "(?,?,?,?)";

        try (PreparedStatement pstmt = Conexion.getCon().prepareStatement(sql)) {
            pstmt.setString(1, m.getNombre());
            pstmt.setString(2, m.getHoraInicio());
            pstmt.setString(3, m.getHoraFin());
            pstmt.setInt(4, m.getIdAula());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Hubo un error al insertar la materia: " + e.getMessage());
            return false;
        }

        return true;
    }

    public static boolean eliminar(Materia m) {
        String sql = "DELETE FROM infaut.materia WHERE id_materia = ?";

        try (PreparedStatement pstmt = Conexion.getCon().prepareStatement(sql)) {
            pstmt.setInt(1, m.getIdAula());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Hubo un error al eliminar la materia: " + e.getMessage());
            return false;
        }
    }

    // NOTE: El formato de la hora se debe validar antes de llamar a este método
    public static boolean modificar(Materia m) {
        String sql = "UPDATE infaut.materia SET " +
                "nombre = ?, hora_inicio = ?, hora_fin = ?, aula_id_aula = ?" +
                "WHERE id_materia = ?";

        try (PreparedStatement pstmt = Conexion.getCon().prepareStatement(sql)) {

            pstmt.setString(1, m.getNombre());
            pstmt.setString(2, m.getHoraInicio());
            pstmt.setString(3, m.getHoraFin());
            pstmt.setInt(4, m.getIdAula());
            pstmt.setInt(5, m.getIdMateria());

            pstmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static ArrayList<Materia> consulta() {
        ArrayList<Materia> result = new ArrayList<Materia>();
        String sql = "SELECT * FROM infaut.materia WHERE 1=1";
        try (Statement stmt = Conexion.getCon().createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Materia m = new Materia(
                        rs.getInt("id_materia"),
                        rs.getString("nombre"),
                        rs.getString("hora_inicio"),
                        rs.getString("hora_fin"),
                        rs.getInt("aula_id_aula"));

                result.add(m);
            }
        } catch (SQLException e) {
            System.err.println("Ocurrio un error al obtener las materias: " + e.getMessage());
            return null;
        }

        return result;
    }
}
