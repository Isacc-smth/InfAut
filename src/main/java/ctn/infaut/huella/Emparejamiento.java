package ctn.infaut.huella;

import java.util.ArrayList;
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;
import ctn.infaut.DAO.HuellaDAO;
import ctn.infaut.controllers.Huella;

// FIXME: Arreglar emparejamiento
public class Emparejamiento {
    private static ArrayList<Huella> huellas;

    static {
        huellas = HuellaDAO.obtenerHuellas();
        // Filtrar huellas inválidas al iniciar
        huellas.removeIf(h -> h.getImagen() == null);
    }

    private Emparejamiento() {}

    /** Empareja un sujeto con las huellas registradas en la base de datos
     *
     * @param sujeto la huella a emparejar
     *
     * @return los datos del sujeto si se encuentra coincidencia, null si no se obtiene nada
     * */
    public static Huella emparejar(FingerprintTemplate sujeto) {
        if (sujeto == null) {
            throw new IllegalArgumentException("El template del sujeto es null");
        }

        double umbral = 40.0;
        FingerprintMatcher matcher = new FingerprintMatcher(sujeto);

        Huella mejorCoincidencia = null;
        double mejorPuntaje = 0.0;

        for (Huella candidato : huellas) {
            if (candidato.getImagen() == null) continue;

            double puntaje = matcher.match(candidato.getImagen());
            System.out.println("Score con alumno " + candidato.getIdAlumno() + ": " + puntaje);

            if (puntaje > mejorPuntaje && puntaje >= umbral) {
                mejorPuntaje = puntaje;
                mejorCoincidencia = candidato;
            }
        }

        return mejorCoincidencia;
    }
}
