package ctn.infaut.services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.ArrayList;

import ctn.infaut.connection.Conexion;
import ctn.infaut.controllers.Aula;

public class AulaService {
  public static boolean insertar(Aula a) {
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

  public static boolean eliminar(Aula a) {
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

  public static ArrayList<Aula> consulta() {
    ArrayList<Aula> rooms = new ArrayList<>();
    String sql = "SELECT * FROM infaut.aula WHERE 1=1";

    try (Statement stmt = Conexion.getCon().prepareStatement(sql)) {
      ResultSet rs = stmt.getResultSet();
      while (rs.next()) {
        rooms.add(new Aula(
            rs.getInt("id_aula"),
            rs.getString("descripcion")));
      }
    } catch (SQLException e) {
      System.err.println("Ocurrio un error al obtener las filas: " + e.getMessage());
      return null;
    }

    return rooms;
  }

  public static boolean modificar(Aula a) {
    String sql = "UPDATE FROM infaut.aula SET descripcion = ? WHERE id_aula = ?";
    try (PreparedStatement pstmt = Conexion.getCon().prepareStatement(sql)) {
      pstmt.setString(1, a.getDescripcion());
      pstmt.setInt(1, a.getIdAula());

      pstmt.executeUpdate();
      return true;
    } catch (SQLException e) {
      System.err.println("Ocurrio un error al modificar el aula: " + e.getMessage());
      return false;
    }
  }
}
