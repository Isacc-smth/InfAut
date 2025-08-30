package ctn.infaut.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ctn.infaut.connection.Conexion;
import ctn.infaut.controllers.Huella;

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
    private final Conexion con;

    public HuellaDAO() throws SQLException { this.con = new Conexion(); }
    
    /**
     * Metodo para insertar una huella en la base de datos
     * 
     * @param h imagen a insertar. La imagen se guarda como un arreglo de bytes
     * @return true si la consulta se ejecuta correctamente, false de lo contrario
     */
    public boolean insertar(Huella h) {
        String sql = "INSERT INTO infaut.huella VALUES (?,?,?)";
        try (PreparedStatement pstmt = con.getCon().prepareStatement(sql)) {
            pstmt.setInt(1, h.getIdHuella());
            pstmt.setInt(2, h.getIdAlumno());
            pstmt.setBytes(3, h.getImagenHuella());
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

        try (PreparedStatement pstmt = con.getCon().prepareStatement(sql)) {
            pstmt.setInt(1, h.getIdHuella());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Hubo un error al modificar la huella: " + e.getMessage());
            return false;
        }
        
    }

    // Es poco probable que usemos pero por si acaso. Puede ser que quite
    /**
     * Metodo para modificar una huella existente en la base de datos
     *
     * @param h la huella a modificar
     * @return true si la consulta se ejecuta correctamente, false en caso contrario
     */
    public boolean modificar(Huella h) { 
        String sql = "UPDATE infaut.huella SET id_alumno = ?, imagen_huella = ? WHERE id_huella = ?";

         try (PreparedStatement pstmt = con.getCon().prepareStatement(sql)) {
                pstmt.setInt(1, h.getIdAlumno());
                pstmt.setBytes(2, h.getImagenHuella());
                pstmt.setInt(3, h.getIdHuella());

                pstmt.executeUpdate();
                return true;
             } catch (SQLException e) { 
                System.err.println("Hubo un error al modificar la huella: " + e.getMessage());
                 return false;
             } 
    }
    
    @TODO("Encriptar las huellas antes de registrar")
    /**
     * Metodo para obtener las huellas de la base de datos
     * 
     * @return una lista con las huellas disponibles en la base de datos si la consulta se ejecuta
     * correctamente, null de lo contrario
     * 
     */
    public ArrayList<Huella> obtenerHuellas() {
        ArrayList<Huella> huellas = new ArrayList<Huella>();
        String sql = "SELECT * FROM infaut.huella WHERE 1=1";
        try (Statement stmt = con.getCon().createStatement()) {
           ResultSet rs = stmt.executeQuery(sql);
           while (rs.next()) {
               huellas.add(new Huella(
                    rs.getInt("id_huella"),
                    rs.getInt("id_alumno"),
                    rs.getBytes("imagen_huella")
               ));

           } 
        } catch (SQLException e) {
            System.err.println("Hubo un error al obtener las huellas: " + e.getMessage());
            return null;
        }
        
        return huellas;
    }
}
