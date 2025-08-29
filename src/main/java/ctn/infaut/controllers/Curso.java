/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ctn.infaut.controllers;

/**
 *
 * @author isaacfeltes
 */
public class Curso {

	private Integer idCurso;
	private String descripcion;

	public Curso(String d) { this.descripcion = d; }

	public Curso(Integer id, String d) {
		this.idCurso = id;
		this.descripcion = d;
	}

	public Integer getIdCurso() { return idCurso; }
	public String getDescripcion() { return descripcion; }

    public void setIdCurso(Integer id) { this.idCurso = id; }
	public void setDescripcion(String d) { this.descripcion = d; }
}
