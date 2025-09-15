package ctn.infaut.DTO;

public class CursoMateriaDTO {
   private Integer idCurso; 
   private String curso;
   private Integer idMateria;
   private String materia;

   // Getters
   public Integer getIdCurso() { return idCurso; }
   public Integer getIdMateria() { return idMateria; }
   public String getCurso() { return curso; }
   public String getMateria() { return materia; }

   // Setters
   public void setMateria(String materia) { this.materia = materia; }
   public void setCurso(String curso) { this.curso = curso; }
   public void setIdCurso(Integer idCurso) { this.idCurso = idCurso; }
   public void setIdMateria(Integer idMateria) { this.idMateria = idMateria; }

   public CursoMateriaDTO(Integer idCurso, String curso, Integer idMateria, String materia) {
       this.idCurso = idCurso;
       this.curso = curso;
       this.idMateria = idMateria;
       this.materia = materia;
   }

   public CursoMateriaDTO(Integer idCurso, String materia) {
       this.idCurso = idCurso;
       this.materia = materia;
   }
}
