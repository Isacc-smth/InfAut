package ctn.infaut.DTO;

public class CursoMateriaDTO {
   private Integer idCurso; 
   private String curso;
   private Integer idMateria;
   private String materia;

   // Getters
   public Integer getIdCurso() { return idCurso; }
   public Integer getIdMateria() { return idMateria; }

   // Setters
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
