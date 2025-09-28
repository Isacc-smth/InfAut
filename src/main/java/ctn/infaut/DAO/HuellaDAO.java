package ctn.infaut.DAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import ctn.infaut.connection.Conexion;
import ctn.infaut.controllers.Huella;

// TODO: Refactor

/**
 * Clase para controllar las consultas SQL realizadas por {@link ctn.infaut.MenuHuellaController}.
 * <p>
 * Este metodo tambien se puede utilizar para hacer la verificacion de las huellas
 * </p>
 * <h2>Uso</h2>
 * <pre>{@code
 * HuellaDAO huella_dao = new HuellaDAO();
 * }</pre>
 *
 * @throws java.sql.SQLException cuando la conexion con la base de datos falla. Usualmente se debe a credenciales 
 * incorrectas o errores al conectar con el servicio.
 * 
 * @see ctn.infaut.MenuHuellaController
 */
public class HuellaDAO {
    private String keyPgCrypto;
    // private final Properties props = new Properties();

	public String getKeyPgCrypto() { return keyPgCrypto; }

    public HuellaDAO() { 
        // try (FileInputStream keys = new FileInputStream("db.properties")) {
        //     props.load(keys);
        //     // this.keyPgCrypto =  props.getProperty("DB_ENCRYPTION_KEY");
        // } catch (IOException e) {
        //     System.err.println("CRITICO: no se pudo obtener la clave de desencriptacion: " +
        //             e.getMessage()); 
       // }
    }
    
    /**
     * Metodo para insertar una huella en la base de datos
     * 
     * @param h imagen a insertar. La imagen se guarda como un arreglo de bytes
     * @return true si la consulta se ejecuta correctamente, false de lo contrario
     *
     */
    public boolean insertar(Huella h) {
        String sql = "INSERT INTO infaut.huella (imagen_huella, id_alumno) VALUES (?,?)";
        try (PreparedStatement pstmt = Conexion.getCon().prepareStatement(sql)) {
            pstmt.setBytes(1, h.getSerializada());
            pstmt.setInt(2, h.getIdAlumno());
            pstmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.err.println("Hubo un error al insertar la huella: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Metodo para eliminar una huella en la base de datos
     * 
     * @param h imagen a eliminar.
     * @return true si la consulta se ejecuta correctamente, false de lo contrario
     */
    public boolean eliminar(Huella h) {
        String sql = "DELETE FROM infaut.huella WHRE id_huella = ?";

        try (PreparedStatement pstmt = Conexion.getCon().prepareStatement(sql)) {
            pstmt.setInt(1, h.getIdHuella());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Hubo un error al modificar la huella: " + e.getMessage());
            return false;
        }
        
    }


    /**
     * Metodo para modificar una huella existente en la base de datos
     *
     *
     * @param h la huella a modificar
     * @return true si la consulta se ejecuta correctamente, false en caso contrario
     */
    public boolean modificar(Huella h) { 
        String sql = "UPDATE infaut.huella SET id_alumno = ? WHERE id_huella = ?";

        try (PreparedStatement pstmt = Conexion.getCon().prepareStatement(sql)) {
            pstmt.setInt(1, h.getIdAlumno());
            pstmt.setInt(2, h.getIdHuella());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) { 
            System.err.println("Hubo un error al modificar la huella: " + e.getMessage());
            return false;
        } 
    }
    
    /**
     * Metodo para obtener las huellas de la base de datos
     * 
     * @return una lista con las huellas disponibles en la base de datos si la consulta se ejecuta
     * correctamente, null de lo contrario
     * 
     */
    public static ArrayList<Huella> obtenerHuellas() {
        ArrayList<Huella> huellas = new ArrayList<Huella>();
        String sql = "SELECT * FROM infaut.huella WHERE 1=1";
        try (Statement stmt = Conexion.getCon().createStatement()) {
           ResultSet rs = stmt.executeQuery(sql);
           while (rs.next()) {
               huellas.add(new Huella(
                    rs.getInt("id_huella"),
                    rs.getBytes("imagen_huella"),
                    rs.getInt("id_alumno")
               ));
           } 
           return huellas;
        } catch (SQLException e) {
            System.err.println("Hubo un error al obtener las huellas: " + e.getMessage());
            return null;
        }
    }
}
