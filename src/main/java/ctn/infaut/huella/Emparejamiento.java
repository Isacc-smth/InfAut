package ctn.infaut.huella;

import java.util.ArrayList;

import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintMatcher;

import ctn.infaut.DAO.HuellaDAO;
import ctn.infaut.DTO.AlumnoHuellaDTO;
import ctn.infaut.controllers.Huella;

public class Emparejamiento {
    private static ArrayList<Huella> huellas;

    private Emparejamiento() {}

    static {
        huellas = HuellaDAO.obtenerHuellas();
    }

    public static boolean emparejar(Huella sujeto) {
        double umbral = 40.0;
        FingerprintMatcher emparejador = new FingerprintMatcher(sujeto.getImagen());

        for (Huella candidato : huellas) {
            double puntaje = emparejador.match(candidato.getImagen());
            if (puntaje >= umbral) 
                return true;
        }
        return false;
    }
}
