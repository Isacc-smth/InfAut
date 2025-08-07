package ctn.infaut.services;

import ctn.infaut.connection.Conexion;
import ctn.infaut.controllers.Docente;

import java.util.logging.Logger;
import java.util.logging.Level;

import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class DocenteService {
  public static boolean insertar(Docente d) {
    String sql = "INSERT INTO infaut.docente (nombre, apellido, ci) VALUES (?, ?, ?)";
    try (PreparedStatement pstmt = Conexion.getCon().prepareStatement(sql)) {
      pstmt.setString(1, d.getNombre());
      pstmt.setString(2, d.getApellido());
      pstmt.setInt(3, d.getCI());

      pstmt.executeUpdate();

      return true;

    } catch (SQLException ex) {
      Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }
  }

  public static boolean modificar(Docente d) {
    String sql = "UPDATE infaut.docente SET nombre = ?, apellido = ?, ci = ? WHERE id_docente = ?";

    try (PreparedStatement pstmt = Conexion.getCon().prepareStatement(sql)) {

      pstmt.setString(1, d.getNombre());
      pstmt.setString(2, d.getApellido());
      pstmt.setInt(3, d.getCI());
      pstmt.setInt(4, d.getIdDocente());

      pstmt.executeUpdate();

      return true;
    } catch (SQLException ex) {
      Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }
  }

  public static boolean eliminar(Docente d) {
    String sql = "DELETE FROM infaut.docente WHERE id_docente=?";

    try (PreparedStatement pstmt = Conexion.getCon().prepareStatement(sql)) {
      pstmt.setInt(1, d.getIdDocente());
      pstmt.executeUpdate();
      return true;
    } catch (SQLException ex) {
      Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }
  }

  public static ArrayList<Docente> consulta() throws SQLException {
    ArrayList<Docente> teachers = new ArrayList<Docente>();
    try (Statement stmt = Conexion.getCon().createStatement()) {
      String sql = "SELECT * FROM infaut.docente WHERE 1=1";
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {

        teachers.add(
            new Docente(
                rs.getInt("id_docente"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getInt("ci")));
      }
    } catch (SQLException ex) {
      System.err.println("Hubo un error al obtener los docentes: ");
      Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
      return null;
    }

    return teachers;
  }
}
