package ctn.infaut.controllers;

/**
 * Clase para guardar la aula seleccionada en el buscador
 *
 * @see ctn.infaut.BuscarAulaController
 *
 */
public class AulaSingleton {
    private Integer idAula;
    private String descripcion;


    private AulaSingleton() {}

    // Getters
	public Integer getIdAula() { return idAula; }
    public String getDescripcion() { return descripcion; }

    // Setters
	public void setIdAula(Integer idAula) { this.idAula = idAula; }
	public void setDescripcion(String descripcion) { this.descripcion = descripcion; }


    public static AulaSingleton getInstance() { return AulaSingletonHolder.INSTANCE; }

    private static class AulaSingletonHolder { 
        private static final AulaSingleton INSTANCE = new AulaSingleton(); 
    }
}
