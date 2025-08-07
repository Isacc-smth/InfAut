package ctn.infaut.controllers;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

import ctn.infaut.connection.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Docente implements Conexion {

  private int idDocente;
  private String nombre;
  private String apellido;
  private int numeroCI;

  public Docente() {
  }

  public Docente(int idDocente, String nombre, String apellido, int numeroCI) {
    this.idDocente = idDocente;
    this.nombre = nombre;
    this.apellido = apellido;
    this.numeroCI = numeroCI;
  }

  public Docente(String nombre, String apellido, int numeroCI) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.numeroCI = numeroCI;
  }

  // TODO: Mover los metodos relacionados al SQL en el service correspondiente

  @Override
  public boolean insertar() {
    String sql = "INSERT INTO infaut.docente (nombre, apellido, ci) VALUES (?, ?, ?)";
    try (PreparedStatement pstmt = Conexion.getCon().prepareStatement(sql)) {
      pstmt.setString(1, this.nombre);
      pstmt.setString(2, this.apellido);
      pstmt.setInt(3, this.numeroCI);

      pstmt.executeUpdate();

      return true;

    } catch (SQLException ex) {
      Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }
  }

  @Override
  public boolean modificar() {
    String sql = "UPDATE infaut.docente SET nombre = ?, apellido = ?, ci = ? WHERE id_docente = ?";

    try (PreparedStatement pstmt = Conexion.getCon().prepareStatement(sql)) {

      pstmt.setString(1, this.nombre);
      pstmt.setString(2, this.apellido);
      pstmt.setInt(3, this.numeroCI);
      pstmt.setInt(4, this.idDocente);

      pstmt.executeUpdate();

      return true;
    } catch (SQLException ex) {
      Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }
  }

  @Override
  public boolean eliminar() {
    String sql = "DELETE FROM infaut.docente WHERE id_docente=?";

    try (PreparedStatement pstmt = Conexion.getCon().prepareStatement(sql)) {
      pstmt.setInt(1, this.idDocente);
      pstmt.executeUpdate();
      return true;
    } catch (SQLException ex) {
      Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
      return false;
    }
  }

  public ArrayList<Docente> consulta() throws SQLException {
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

  public int getIdDocente() {
    return this.idDocente;
  }

  public void setIdDocente(int idDocente) {
    this.idDocente = idDocente;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return this.apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public int getCI() {
    return numeroCI;
  }

  public void setCI(int numeroCI) {
    this.numeroCI = numeroCI;
  }
}
