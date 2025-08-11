package ctn.infaut.controllers;

public class Aula {
  private Integer idAula;
  private String descripcion;

  public Integer getIdAula() {
    return this.idAula;
  }

  public String getDescripcion() {
    return this.descripcion;
  }

  private void setIdAula(Integer id) {
    this.idAula = id;
  }

  private void setDescripcion(String desc) {
    this.descripcion = desc;
  }

  public Aula() {
  }

  public Aula(Integer idAula, String descripcion) {
    this.idAula = idAula;
    this.descripcion = descripcion;
  }
}
