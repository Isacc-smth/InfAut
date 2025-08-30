package ctn.infaut.controllers;

public class Huella {
    private Integer idHuella;
    private Integer idAlumno;
    private byte[] imagenHuella;

    public Huella(Integer idHuella, Integer idAlumno, byte[] imagenHuella) {
        this.idHuella = idHuella;
        this.idAlumno = idAlumno;
        this.imagenHuella = imagenHuella;
    }

    // Getters
    public Integer getIdHuella() { return idHuella; }
    public Integer getIdAlumno() { return idAlumno; }
    public byte[] getImagenHuella() { return imagenHuella; }
  
    // Setters
    public void setIdHuella(Integer idHuella) { this.idHuella = idHuella; }
    public void setIdAlumno(Integer idAlumno) { this.idAlumno = idAlumno; }

    public void setImagenHuella(byte[] imagenHuella) { 
        this.imagenHuella = imagenHuella; 
    }



}