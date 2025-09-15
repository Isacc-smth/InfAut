package ctn.infaut.controllers;

public class MateriaSingleton {
    private Integer idMateria;
    private String nombre;
    private String horaInicio;
    private String horaFin;

    private MateriaSingleton() {}

    // Getters
    public String getHoraInicio() { return this.horaInicio; }
    public String getHoraFin() { return this.horaFin; }
    public Integer getIdMateria() { return this.idMateria; }
    public String getNombre() { return this.nombre; }

    // Setters
    public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }
	public void setHoraFin(String horaFin) { this.horaFin = horaFin; }
	public void setIdMateria(Integer idMateria) { this.idMateria = idMateria; }
	public void setNombre(String nombre) { this.nombre = nombre; }

    public static MateriaSingleton getInstance() { return MateriaSingletonHolder.INSTANCE; }

    private static class MateriaSingletonHolder {
        private static final MateriaSingleton INSTANCE = new MateriaSingleton();
    }
}
