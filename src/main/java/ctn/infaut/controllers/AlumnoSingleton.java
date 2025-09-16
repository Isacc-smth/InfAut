/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ctn.infaut.controllers;

/**
 *
 * @author
 * isaacfeltes
 */
public class AlumnoSingleton {
    private Integer idAlumno;

	private String nombre;
    private String apellido;

    private Integer ci;
    private Integer idCurso;

    public void setIdAlumno(Integer idAlumno) { this.idAlumno = idAlumno; } 
	public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setCi(Integer ci) { this.ci = ci; }
	public void setIdCurso(Integer idCurso) { this.idCurso = idCurso; }

    public Integer getIdAlumno() { return idAlumno; }
	public String getNombre() { return nombre; }
	public String getApellido() { return apellido; }
	public Integer getCi() { return ci; }
	public Integer getIdCurso() { return idCurso; }
	
	private AlumnoSingleton() {}

	public static AlumnoSingleton getInstance() { return AlumnoSingletonHolder.INSTANCE; }
	
	private static class AlumnoSingletonHolder {
		private static final AlumnoSingleton INSTANCE = new AlumnoSingleton();
	}
}
