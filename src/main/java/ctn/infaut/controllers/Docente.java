package ctn.infaut.controllers;

import ctn.infaut.connection.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

public class Docente implements Conexion {

  private int idDocente;
  private String nombre;
  private String apellido;
  private int numeroCI;

  @Override
  public boolean insertar() {
    String sql = "INSERT INTO infaut.Docente (nombre, apellido CI) VALUES (?, ?, ?)";
    try (PreparedStatement pstmt = Conexion.getCon().prepareStatement(sql)) {
      pstmt.setString(1, this.nombre);
      pstmt.setString(2, this.apellido);
      pstmt.setInt(3, this.numeroCI);

      pstmt.executeUpdate();

      return true;

    } catch (SQLException e) {
      Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, e);
      return false;
    }
  }

  public Docente(int idDocente, String nombre, String apellido, int numeroCI) {
    this.idDocente = idDocente;
    this.nombre = nombre;
    this.apellido = apellido;
    this.numeroCI = numeroCI;
  }

  public Docente() {
  }

  @Override
  public boolean modificar() {
    String sql = "UPDATE * FROM infaut.docente SET, nombre = ?, apellido = ?, ci = ? WHERE iddocente = ?";

    try (PreparedStatement pstmt = Conexion.getCon().prepareStatement(sql)) {

      pstmt.setString(1, this.nombre);
      pstmt.setString(2, this.apellido);
      pstmt.setInt(3, this.numeroCI);
      pstmt.setInt(4, idDocente);

      return true;
    } catch (SQLException ex) {
      return false;
    }
  }

  @Override
  public boolean eliminar() {
    return false;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public int getNumeroCI() {
    return numeroCI;
  }

  public void setNumeroCI(int numeroCI) {
    this.numeroCI = numeroCI;
  }
}
