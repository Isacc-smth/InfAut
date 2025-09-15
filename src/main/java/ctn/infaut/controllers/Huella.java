package ctn.infaut.controllers;

import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintTemplate;

import ctn.infaut.huella.HuellaUtils;

public class Huella {
    private Integer idHuella;
    private Integer idAlumno;
    private FingerprintTemplate imagen;
    private byte[] serializada;
    private String horaEntrada;

    // Instanciar huella desde el path de una imagen seleccionada 
    public Huella(Integer idHuella, Integer idAlumno, String pathHuella) {
        this.idHuella = idHuella;
        this.idAlumno = idAlumno;

        // No quiero reescribir el metodo para obtener la huella, porque es larguito
        this.serializada = HuellaUtils.serializarDesdePath(pathHuella);
    }

    // La base de datos retorna un array de bytes
    public Huella(Integer idHuella, byte[] huella, Integer idAlumno, String horaEntrada) {
        this.idAlumno = idAlumno;
        this.idHuella = idHuella;
        this.serializada = huella;
        this.horaEntrada = horaEntrada;
    } 

    // Getters
    public Integer getIdHuella() { return idHuella; }
    public Integer getIdAlumno() { return idAlumno; }
    public FingerprintTemplate getImagen() { return imagen; }
    public byte[] getSerializada() { return serializada; }
    public String getHoraEntrada() { return horaEntrada; }
  
    // Setters
    public void setIdHuella(Integer idHuella) { this.idHuella = idHuella; }
    public void setIdAlumno(Integer idAlumno) { this.idAlumno = idAlumno; }
    public void setTemplatenHuella(byte[] huella) { this.serializada = huella; }

}
