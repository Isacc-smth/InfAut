package ctn.infaut.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import ctn.infaut.controllers.Alumno;
import ctn.infaut.DTO.AlumnoCursoDTO;
import ctn.infaut.connection.Conexion;

/**
 * Clase para controlar consultas SQL solicitadas por el  menu de Alumnos
 *
 * @implNote El método #consulta retorna un {@link ctn.infaut.DTO.AlumnoCursoDTO}
 *
 * @see ctn.infaut.DTO.AlumnoCursoDTO
 * @see ctn.infaut.MenuAlumnoController
 *
 * */
public class AlumnoDAO {
    private Conexion con;

    public AlumnoDAO() throws SQLException {
        con = new Conexion();
    }

    public boolean insertar(Alumno al) {
        String sql = "INSERT INTO infaut.alumno (nombre, apellido, ci, id_curso) values (?,?,?,?)";
        try (PreparedStatement pstmt = con.getCon().prepareStatement(sql)) {
            pstmt.setString(1, al.getNombre());
            pstmt.setString(2, al.getApellido());
            pstmt.setInt(3, al.getCi());
            pstmt.setInt(4, al.getIdCurso());

            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.err.println("Hubo un error al insertar al alumno" + e.getMessage());
            return false;
        }
    }

    public ArrayList<AlumnoCursoDTO> consulta() {
        String sql = "SELECT id_alumno, nombre, apellido, ci, c.id_curso, descripcion " + 
            "FROM infaut.alumno a INNER JOIN infaut.curso c ON a.id_curso = c.id_curso " + 
            "WHERE 1=1";
        try (Statement stmt = con.getCon().createStatement()) {
            ArrayList<AlumnoCursoDTO> students = new ArrayList<>();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                AlumnoCursoDTO al = new AlumnoCursoDTO(
                        rs.getInt("id_alumno"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("ci"),
                        rs.getInt("id_curso"),
                        rs.getString("descripcion")
					);
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
        String sql = "UPDATE infaut.alumno SET" + 
            " nombre = ?, apellido = ?, ci = ?, id_curso = ?" +
            " WHERE id_alumno = ?";

        try (PreparedStatement pstmt = con.getCon().prepareStatement(sql)) {
            pstmt.setString(1, al.getNombre());
            pstmt.setString(2, al.getApellido());
            pstmt.setInt(3, al.getCi());
            pstmt.setInt(4, al.getIdCurso());
            pstmt.setInt(5, al.getIdAlumno());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Hubo un error al eliminar al alumno" + e.getMessage());
            return false;
        }
    }
}
