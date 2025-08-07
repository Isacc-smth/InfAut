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

public class Docente {

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
