package ctn.infaut.controllers;

/**
 * Clase que representa a la tabla de las aulas
 *
 * @author isaacfeltes
 * */
public class Materia {
    Integer idMateria;
    String nombre;
    String horaInicio;
    String horaFin;
    Integer idAula;


    // Getters
    public Integer getIdMateria() { return this.idMateria; }
    public String getNombre() { return this.nombre; }
    public Integer getIdAula() { return this.idAula; }
    public String getHoraInicio() { return this.horaInicio; }
    public String getHoraFin() { return this.horaFin; }

    // Setters
    public void setIdMateria(Integer id) { this.idMateria = id; }
    public void setIdAula(Integer id) { this.idAula = id; }
public void setHoraInicio(String hora) { this.horaInicio = hora; }
    public void setHoraFin(String hora) { this.horaFin = hora; }

    public Materia() {
    }

    /**
     * Crea una materia con los datos básicos (sin idMateria)
     *
     * @implNote hi y hf admiten el formato 24hs de la forma HH:MM
     *
     * @param ia identificador único de la materia. Opcional
     * @param n nombre de la materia
     * @param hi hora de inicio. A qué hora empieza la clase
     * @param hf hora de cierre. A qué hora termina la clase
     * @param ia id del aula donde se imparte la clase
     *
     * @throws IllegalArgumentException cuando el formato de la hora es incorrecto
     * */
    public Materia(String n, String hi, String hf, Integer ia) {
        if (!isValidTime(hi) || !isValidTime(hf)) {
            throw new IllegalArgumentException("Formato de la hora incorrecto, use el formato HH:MI o HH:MI:SS");
        }

        this.nombre = n;
        this.horaInicio = hi;
        this.horaFin = hf;
        this.idAula = ia;
    }
    public Materia(Integer im, String n, String hi, String hf, Integer ia) {
        if (!isValidTime(hi) || !isValidTime(hf)) {
            throw new IllegalArgumentException("Formato de la hora incorrecto, use el formato HH:MI o HH:MI:SS");
        }

        this.idMateria = im;
        this.nombre = n;
        this.horaInicio = hi;
        this.horaFin = hf;
        this.idAula = ia;
    }

    /**
     * Método auxiliar para validar el formato de las horas.
     *
     * @implNote NO SE ADMITE EL FORMATO 12 HORAS, esto a fin de forzar una consistencia
     * en la columna
     *
     * @param time La hora como cadena en formato 24h (HH:MM)
     *
     * @return true, si la hora es válida, false de lo contrario
     * */
    private boolean isValidTime(String time) throws NumberFormatException {
        String[] tokens = time.replace(" ", "").split(":");

        if (tokens.length != 2 || !time.contains(":")) {
            System.err.println("Cantidad de tokens no es 2");
            return false;
        }

        Integer hours = Integer.parseInt(tokens[0]);
        if (hours < 0 || hours >= 24) {
            System.err.println("La hora no es un número válido");
            return false;
        }

        Integer minutes = Integer.parseInt(tokens[1]);
        if (minutes < 0 || minutes >= 60) {
            System.err.println("Los minutos no son un número válido");
            return false;
        }

        return true;
    }
}
