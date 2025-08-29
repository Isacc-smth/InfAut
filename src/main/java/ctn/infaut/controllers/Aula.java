package ctn.infaut.controllers;

public class Aula {
    private Integer idAula;
    private String descripcion;

    public Integer getIdAula() { return this.idAula; }
    public String getDescripcion() { return this.descripcion; }

    public Aula() { }

    public Aula(Integer idAula, String descripcion) {
        this.idAula = idAula;
        this.descripcion = descripcion;
    }

    public Aula(String descripcion) { this.descripcion = descripcion; }
}
