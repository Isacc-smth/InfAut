package ctn.infaut.controllers;

/**
 * Singleton que representa el aula seleccionado por
 * {@link ctn.infaut.BuscarAulaController}
 * <p>
 * Esta clase asegura que solamente haya una instancia durante todo el codigo,
 * por que hay motivo para tener varias. La instancia se obtiene mediante 
 * {@link #getInstance()}
 * </p>
 *
 * <h2>Uso</h2>
 * <pre>{@code
 * AulaSingleton seleccion = AulaSingleton.getInstance();
 * String descripcion = seleccion.getDescripcion();
 * }</pre>
 * 
 * @see ctn.infaut.BuscarAulaController
 *
 * @author isaacfeltes
 * 
 * */
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
