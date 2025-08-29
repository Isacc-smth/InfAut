package ctn.infaut.controllers;

public class Alumno {
    private String nombre;
    private String apellido;

    private Integer idAlumno;
    private Integer ci;
		private Integer idCurso;

	public Integer getIdCurso() { return this.idCurso; }
    public String getNombre() { return this.nombre; }
    public String getApellido() { return this.apellido; }
    public Integer getIdAlumno() { return this.idAlumno; }
    public Integer getCi() { return this.ci; }

    public void setIdCurso(Integer id) { this.idCurso = id; }
    public void setIdAlumno(Integer idAl) { this.idAlumno = idAl; }
    public void setCi(Integer ci) { this.ci = ci; }
    public void setNombre(String n) { this.nombre = n; }
    public void setApellido(String a) { this.apellido = a; }

    public Alumno(Integer idAl, String n, String a, Integer ci, Integer idCu) {
        this.idAlumno = idAl;
        this.nombre = n;
        this.apellido = a;
        this.ci = ci;
        this.idCurso = idCu;
    }

    public Alumno(String n, String a, Integer ci, Integer idCu) {
        this.nombre = n;
        this.apellido = a;
        this.ci = ci;
        this.idCurso = idCu;
    }
}
