package ctn.infaut.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import ctn.infaut.connection.Conexion;
import ctn.infaut.controllers.Asistencia;

public class AsistenciaDAO {
    public boolean insertar(Asistencia a) {
        String sql = "Insert into asistencia values (?);";
        try (PreparedStatement pstmt  = Conexion.getCon().prepareStatement(sql)) {
            pstmt.setInt(1, a.getIdAlumno());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Hubo un error al insertar la asistencia: " + e.getMessage());
            return false;
        }
    }
}
