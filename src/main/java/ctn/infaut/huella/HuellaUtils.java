package ctn.infaut.huella;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintTemplate;

/** 
 * Clase que contiene utilidades para obtener y serializar
 * huellas
 *
 * @author isaacfeltes
 * */
public interface HuellaUtils {
    /** 
     * Método para generar una imagen comprimida y serilizada desde
     * un archivo, solamente utilizado en pruebas. En la práctica,
     * deberá obtenerlas mediante el SDK del dispositivo o algún protocolo
     * como el RS-485 que fue mi caso. 
     *
     * @param path La ruta del archivo
     *
     * @return Una huella serializada lista para usarse
     * */
    public static FingerprintTemplate obtenerTemplateDesdePath(String path) {
        try {
            FingerprintImage imagen = new FingerprintImage(Files.readAllBytes(Paths.get(path)));
            FingerprintTemplate template = new FingerprintTemplate(imagen);

            return template;
        } catch (IOException e) {
            System.err.println("No se pudo obtener la huella: " + e.getMessage());
            return null;
        }
    }

    /** 
     * Método para generar una imagen comprimida desde
     * un archivo, solamente utilizado en pruebas. En la práctica,
     * deberá obtenerlas mediante el SDK del dispositivo o algún protocolo
     * como el RS-485 que fue mi caso. 
     *
     * @param path La ruta del archivo
     *
     * @return Una huella serializada lista para usarse
     * */
    public static byte[] serializarDesdePath(String path) {
        FingerprintTemplate template = obtenerTemplateDesdePath(path);
        if (template != null) return template.toByteArray();
        else throw new IllegalStateException("Ocurrio un error al obtener la huella, revise mas arriba");
    }
}
