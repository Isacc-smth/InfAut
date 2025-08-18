package ctn.infaut.controllers;

public class Materia {
    Integer idMateria;
    String nombre;
    String horaInicio;
    String horaFin;
    Integer idAula;

    public Integer getIdMateria() {
        return this.idMateria;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Integer getIdAula() {
        return this.idAula;
    }

    public String getHoraInicio() {
        return this.horaInicio;
    }

    public String getHoraFin() {
        return this.horaFin;
    }

    public void setIdMateria(Integer id) {
        this.idMateria = id;
    }

    public void setIdAula(Integer id) {
        this.idAula = id;
    }

    public Materia() {
    }

    public Materia(Integer im, String n, String hi, String hf) {
        this.idMateria = im;
        this.nombre = n;
        this.horaInicio = hi;
        this.horaFin = hf;
    }

    public Materia(Integer im, String n, String hi, String hf, Integer ia) {
        this.idMateria = im;
        this.nombre = n;
        this.horaInicio = hi;
        this.horaFin = hf;
        this.idAula = ia;
    }
}
