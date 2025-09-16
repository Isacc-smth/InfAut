package ctn.infaut.controllers;

import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintTemplate;

import ctn.infaut.huella.HuellaUtils;

public class Huella {
    private Integer idHuella;
    private Integer idAlumno;
    private FingerprintTemplate imagen;
    private byte[] serializada;

    // Instanciar huella desde el path de una imagen seleccionada 
    public Huella( Integer idAlumno, String pathHuella) {
        this.idAlumno = idAlumno;

        // No quiero reescribir el metodo para obtener la huella, porque es larguito
        this.imagen = HuellaUtils.obtenerTemplateDesdePath(pathHuella);
        this.serializada = HuellaUtils.serializarDesdePath(pathHuella);
    }

    // Instanciar huella desde el path de una imagen seleccionada
        public Huella(Integer idHuella, Integer idAlumno, String pathHuella, String horaEntrada) {
            this.idHuella = idHuella;
            this.idAlumno = idAlumno;
            this.imagen = HuellaUtils.obtenerTemplateDesdePath(pathHuella);
            this.serializada = HuellaUtils.serializarDesdePath(pathHuella);
        }

    // La base de datos retorna un array de bytes
    public Huella(Integer idHuella, byte[] huella, Integer idAlumno) {
        this.idAlumno = idAlumno;
        this.idHuella = idHuella;
        this.serializada = huella;
    } 

    // Getters
    public Integer getIdHuella() { return idHuella; }
    public Integer getIdAlumno() { return idAlumno; }
    public FingerprintTemplate getImagen() { return imagen; }
    public byte[] getSerializada() { return serializada; }
  
    // Setters
    public void setIdHuella(Integer idHuella) { this.idHuella = idHuella; }
    public void setIdAlumno(Integer idAlumno) { this.idAlumno = idAlumno; }
    public void setSerializada(byte[] huella) { this.serializada = huella; }

}
