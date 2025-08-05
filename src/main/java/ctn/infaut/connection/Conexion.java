package ctn.infaut.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// Es static para no hacer 5 instancias innecesarias
public class Conexion {
  static Properties props = new Properties();

  public static Connection getCon() throws SQLException {
    try (FileInputStream db = new FileInputStream("db.properties")) {
      // NOTE: Fijate lo que te puse en el .gitignore
      props.load(db);
      String url = props.getProperty("db.url");

      Connection con = DriverManager.getConnection(url);
      System.out.println("Se conectó con éxito a la base de datos");
      return con;
    } catch (SQLException e) {
      System.err.println("Error al conectar con la base de datos: " + e.getMessage());
      return null;
    } catch (IOException fe) {
      System.err.println("Error al leer las credenciales de la base de datos:" + fe.getMessage());
      return null;
    }
  }
}
