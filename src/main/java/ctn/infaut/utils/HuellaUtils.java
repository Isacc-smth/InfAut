package ctn.infaut.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintTemplate;

/** 
 * Clase que contiene utilidades para obtener y serializar
 * huellas
 *
 *
 * @author isaacfeltes
 *
 * */
public interface HuellaUtils {
    /** 
     * Método para generar una imagen comprimida y serilizada desde
     * un archivo. Solamente utilizar para pruebas; en la práctica,
     * deberá obtenerlas mediante el SDK del dispositivo 
     *
     * @param path La ruta del archivo
     *
     * */
    public static byte[] fromPath(String path) {
        try {
            FingerprintImage imagen = new FingerprintImage(Files.readAllBytes(Paths.get(path)));
            FingerprintTemplate template = new FingerprintTemplate(imagen);

            return template.toByteArray();
        } catch (IOException e) {
            System.err.println("No se pudo obtener la huella: " + e.getMessage());
            return null;
        }
    }
}
