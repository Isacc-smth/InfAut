package ctn.infaut.DTO;

public class HuellaAlumnoMateriaDTO {
   private Integer idHuella;
   private Integer idAlumno;
   private Integer idMateria;
   private String horaEntrada;

   // Getters
   public Integer getIdHuella() { return idHuella; }
   public Integer getIdAlumno() { return idAlumno; }
   public Integer getIdMateria() { return idMateria; }
   public String getHoraEntrada() { return horaEntrada; }

   // Setters
   public void setIdAlumno(Integer idAlumno) { this.idAlumno = idAlumno; }
   public void setIdMateria(Integer idMateria) { this.idMateria = idMateria; }
   public void setHoraEntrada(String horaEntrada) { this.horaEntrada = horaEntrada; }
   public void setIdHuella(Integer idHuella) { this.idHuella = idHuella; }

}
