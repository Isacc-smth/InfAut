package ctn.infaut.controllers;

public class Materia {
    Integer idMateria;
    String nombre;
    String horaInicio;
    String horaFin;
    Integer idAula;

    public Integer getIdMateria() {
        return this.idMateria;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Integer getIdAula() {
        return this.idAula;
    }

    public String getHoraInicio() {
        return this.horaInicio;
    }

    public String getHoraFin() {
        return this.horaFin;
    }

    public void setIdMateria(Integer id) {
        this.idMateria = id;
    }

    public void setIdAula(Integer id) {
        this.idAula = id;
    }

    public void getHoraInicio(String hora) {
        this.horaInicio = hora;
    }

    public void getHoraFin(String hora) {
        this.horaFin = hora;
    }

    public Materia() {
    }

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
