package ctn.infaut.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ctn.infaut.DTO.CursoMateriaDTO;
import ctn.infaut.connection.Conexion;

public class CursoMateriaDAO {
    public boolean insertar(CursoMateriaDTO cursoMateria) {
        String sql = "INSERT INTO infaut.curso_materia VALUES (?,?);";
        try (PreparedStatement pstmt = Conexion.getCon().prepareStatement(sql)) {
            pstmt.setInt(1, cursoMateria.getIdCurso());
            pstmt.setInt(2, cursoMateria.getIdMateria());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Ocurrio un error al relacionar el curso con la materia: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<CursoMateriaDTO> consulta() {
        String sql = "SELECT c.id_curso, c.descripcion, m.id_materia, m.nombre" +
                "FROM infaut.curso c" +
                "JOIN infaut.curso_materia cm ON c.id_curso = cm.id_curso" +
                "JOIN infaut.materia m ON cm.id_materia = m.id_materia;";

        ArrayList<CursoMateriaDTO> result = new ArrayList<CursoMateriaDTO>();
        try (Statement stmt = Conexion.getCon().createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
            }
        } catch (SQLException e) {
            System.err.println("Hubo un error al obtener las relaciones curso, aula: " + e.getMessage());
            return null;
        }

        return result;
    }
}
