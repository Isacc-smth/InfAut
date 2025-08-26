package ctn.infaut.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import ctn.infaut.controllers.Alumno;
import ctn.infaut.connection.Conexion;

public class AlumnoDAO {
    private Conexion con;

    public AlumnoDAO() throws SQLException {
        con = new Conexion();
    }

    public boolean insertar(Alumno al) {
        String sql = "INSERT INTO infaut.alumno (nombre, apellido, ci) values (?,?,?)";
        try (PreparedStatement pstmt = con.getCon().prepareStatement(sql)) {
            pstmt.setString(1, al.getNombre());
            pstmt.setString(2, al.getApellido());
            pstmt.setInt(3, al.getCi());

            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.err.println("Hubo un error al insertar al alumno" + e.getMessage());
            return false;
        }
    }

    public ArrayList<Alumno> consulta() {
        String sql = "SELECT * FROM infaut.alumno WHERE 1=1";
        try (Statement stmt = con.getCon().createStatement()) {
            ArrayList<Alumno> students = new ArrayList<>();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Alumno al = new Alumno(
                        rs.getInt("id_alumno"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("ci"));

                students.add(al);
            }
            return students;
        } catch (SQLException e) {
            System.err.println("Hubo un error al obtener los alumnos" + e.getMessage());
            return null;
        }
    }

    public boolean eliminar(Alumno al) {
        String sql = "DELETE FROM infaut.alumno WHERE id_alumno = ?";
        try (PreparedStatement pstmt = con.getCon().prepareStatement(sql)) {
            pstmt.setInt(1, al.getIdAlumno());
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Hubo un error al eliminar al alumno" + e.getMessage());
            return false;
        }
    }

    public boolean modificar(Alumno al) {
        String sql = "UPDATE infaut.alumno SET nombre = ?, apellido = ?, ci = ? WHERE id_alumno = ?";
        try (PreparedStatement pstmt = con.getCon().prepareStatement(sql)) {
            pstmt.setString(1, al.getNombre());
            pstmt.setString(2, al.getApellido());
            pstmt.setInt(3, al.getCi());
            pstmt.setInt(4, al.getIdAlumno());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Hubo un error al eliminar al alumno" + e.getMessage());
            return false;
        }
    }
    }

}
