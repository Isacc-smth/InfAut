package ctn.infaut.controllers;

import ctn.infaut.huella.HuellaUtils;

public class Huella {
    private Integer idHuella;
    private Integer idAlumno;
    private byte[] imagenHuella;

    public Huella(Integer idHuella, Integer idAlumno, String pathHuella) {
        this.idHuella = idHuella;
        this.idAlumno = idAlumno;

        // No quiero reescribir el metodo para obtener la huella, porque es larguito
        this.imagenHuella = HuellaUtils.fromPath(pathHuella);
    }

    // La base de datos retorna un array de bytes
    public Huella(Integer idHuella, Integer idAlumno, byte[] huella) {
        this.idAlumno = idAlumno;
        this.idHuella = idHuella;
        this.imagenHuella = huella;
    } 

    // Getters
    public Integer getIdHuella() { return idHuella; }
    public Integer getIdAlumno() { return idAlumno; }
    public byte[] getImagenHuella() { return imagenHuella; }
  
    // Setters
    public void setIdHuella(Integer idHuella) { this.idHuella = idHuella; }
    public void setIdAlumno(Integer idAlumno) { this.idAlumno = idAlumno; }
    public void setImagenHuella(byte[] huella) { this.imagenHuella = huella; }
}
