package ctn.infaut.controllers;

import java.sql.Timestamp;
import java.time.LocalDateTime;


public class Asistencia {
    public Integer idAsistencia;
    public Integer idAlumno;
    public Timestamp fecha;

    public Asistencia(Integer idAsistencia, Integer idAlumno) {
        this.idAsistencia = idAsistencia;
        this.idAlumno = idAlumno;
        this.fecha = Timestamp.valueOf(LocalDateTime.now());
    }

	public Integer getIdAsistencia() { return idAsistencia; }
	public Integer getIdAlumno() { return idAlumno; }
    public Timestamp getFecha() { return fecha; }
}
