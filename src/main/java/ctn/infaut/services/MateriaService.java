package ctn.infaut.services;

import java.sql.PreparedStatement;

import ctn.infaut.connection.Conexion;
import ctn.infaut.controllers.Materia;

public class MateriaService {
  public boolean insertar(Materia m) {
    String sql = "INSERT INTO infaut.materia" +
        "(nombre, hora_inicio, hora_fin, aula_id_aula) VALUES" +
        "(?,?,?,?)";

    try (PreparedStatement pstmt = Conexion.getCon().prepareStatement(sql)) {
      pstmt.setString(1, m.getNombre());
      pstmt.setString(2, m.getHoraInicio());
      pstmt.setString(3, m.getHoraFin());
      pstmt.setInt(4, m.getIdAula());

      pstmt.executeUpdate();
    } catch (Exception e) {
      System.err.println("Hubo un error al insertar la materia: " + e.getMessage());
      return false;
    }

    return true;
  }
}
