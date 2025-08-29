package ctn.infaut.controllers;

/**
 * Singleton que representa el curso seleccionado por
 * {@link ctn.infaut.BuscarCursoController}
 * <p>
 * Esta clase asegura que solamente haya una instancia durante todo el codigo,
 * por que hay motivo para tener varias. La instancia se obtiene mediante 
 * {@link #getInstance()}
 * </p>
 *
 * <h2>Uso</h2>
 * <pre>{@code
 * CursoSingleton seleccion = CursoSingleton.getInstance();
 * String descripcion = seleccion.getDescripcion();
 * }</pre>
 * 
 * @see ctn.infaut.BuscarCursoController
 *
 * @author isaacfeltes
 * 
 * */
public class CursoSingleton {
    private Integer idCurso;
    private String descripcion;


    private CursoSingleton() {}

    // Getters
	public Integer getIdCurso() { return idCurso; }
    public String getDescripcion() { return descripcion; }

    // Setters
	public void setIdCurso(Integer idCurso) { this.idCurso = idCurso; }
	public void setDescripcion(String descripcion) { this.descripcion = descripcion; }


    public static CursoSingleton getInstance() { return CursoSingletonHolder.INSTANCE; }

    private static class CursoSingletonHolder { 
        private static final CursoSingleton INSTANCE = new CursoSingleton(); 
    }
}
