package ctn.infaut.DTO;

public class AlumnoHuellaDTO {
    private Integer idHuella;
    private Integer idAlumno;
    private String nombre;
    private String apellido;
    private String horaEntrada;

    // NOTE: La hora se ingresa a la base de datos como text por lo que es mas adecuado usarlo como un string.
    // Antes de insertar o modifiar ya se hace la validacion correspondiente
    public AlumnoHuellaDTO(int idHuella, int idAlumno, String nombre, String apellido, String horaEntrada) {
        this.idHuella = idHuella;
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.apellido = apellido;
        this.horaEntrada = horaEntrada; 
    }

    // Getters
    public Integer getIdHuella() { return idHuella; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public Integer getIdAlumno() { return idAlumno; }
    public String getHoraEntrada() { return horaEntrada; }
}
