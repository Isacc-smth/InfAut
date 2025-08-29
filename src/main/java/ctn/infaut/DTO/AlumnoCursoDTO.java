package ctn.infaut.DTO;

import ctn.infaut.controllers.Alumno;
import ctn.infaut.controllers.Curso;

/**
 * Clase para almacenar los datos de {@link ctn.infaut.DAO.AlumnoDAO.#consulta}
 *
 * @see ctn.infaut.DAO.AlumnoDAO
 *
 * */
public class AlumnoCursoDTO {
    // Tabla Alumno
    private Integer idAlumno;
    private String nombre;
    private String apellido;
    private Integer ci;
    private Integer idCurso;
    private String descripcion;

    // Getters
	public void setIdAlumno(Integer idAlumno) { this.idAlumno = idAlumno; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	public void setApellido(String apellido) { this.apellido = apellido; }
	public void setCi(Integer ci) { this.ci = ci; }
	public void setIdCurso(Integer idCurso) { this.idCurso = idCurso; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    // Getters
	public String getDescripcion() { return descripcion; }
    public Integer getIdCurso() { return idCurso; }
    public Integer getCi() { return ci; }
    public Integer getIdAlumno() { return idAlumno; }
    public String getApellido() { return apellido; }
    public String getNombre() { return nombre; }


    // Tomamos las clases de Alumno y curso
    public AlumnoCursoDTO(Alumno alumno, Curso curso) {
        this.idAlumno = alumno.getIdAlumno();
        this.nombre = alumno.getNombre();
        this.apellido = alumno.getApellido();
        this.ci = alumno.getCi();

        this.idCurso = curso.getIdCurso();
        this.descripcion = curso.getDescripcion();
    }

    // Tomar los campos por separado. Probablemente el único que terminemos usando pero el otro constructor esta por si acaso
    public AlumnoCursoDTO(Integer idAlumno, String nombre, String apellido, Integer ci, Integer idCurso, String descripcion) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ci = ci;

        this.idCurso = idCurso;
        this.descripcion = descripcion;
    }


}
