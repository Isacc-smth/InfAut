package ctn.infaut.controllers;

public class Alumno {
    private Integer idAlumno;
    private String nombre;
    private String apellido;
    private Integer ci;

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public Integer getIdAlumno() {
        return this.idAlumno;
    }

    public Integer getCi() {
        return this.ci;
    }

    public void setIdAlumno(Integer idAl) {
        this.idAlumno = idAl;
    }

    public void setCi(Integer ci) {
        this.ci = ci;
    }

    public void setNombre(String n) {
        this.nombre = n;
    }

    public void setApellido(String a) {
        this.apellido = a;
    }

    public Alumno(Integer idAl, String n, String a, Integer ci) {
        this.idAlumno = idAl;
        this.nombre = n;
        this.apellido = a;
        this.ci = ci;
    }

    public Alumno(String n, String a, Integer ci) {
        this.nombre = n;
        this.apellido = a;
        this.ci = ci;
    }
}
