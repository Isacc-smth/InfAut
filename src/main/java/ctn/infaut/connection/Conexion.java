package ctn.infaut.connection;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Conexion {
    private static HikariDataSource ds;
    private static HikariConfig config = new HikariConfig();

    private static String url ="jdbc:mysql://localhost:3306/isaacfeltes?currentSchema=infaut";
    private static String user = "postgres";
    private static String password = "SpookyKacho";
    private Conexion() {};

    static {
        try (FileInputStream db = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(db);

            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");

        } catch (IOException ie) {
            System.out.println("Usando config default... \n");
            System.out.println("url: " + url);
            System.out.println("user: " + user);
            System.out.println("password: " + password);
        }

        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);
        ds = new HikariDataSource(config);
    }

    /**
     * Guarda la configuracion puesta por el usuario mediante {@link ctn.infaut.ConfigurarDB}
     *
     * @param url en formato JDBC
     * @param user nombre de usuario
     * @param password contraseña de la base de datos
     *
     * */
    public static void guardarConfig(String url, String user, String password) {
        Properties props = new Properties();
        props.setProperty("db.url", url);
        props.setProperty("db.user", user);
        props.setProperty("db.password", password);

        try (OutputStream os = new FileOutputStream("db.properties")) {
            props.store(os, "Configuracion puesta por el usuario");

            config.setJdbcUrl(url);
            config.setUsername(user);
            config.setPassword(password);

            ds = new HikariDataSource(config);
        } catch (IOException e) {
            System.err.println("Ocurrio un eror al configurar la conexion: " + e.getMessage());
        }
    }


    /**
     * Metodo usado internamente para obtener conexion a la base de datos
     *
     * @return la conexion a la base de datos
     * @throws SQLException si falla la conexion
     * */
    public static Connection getCon() throws SQLException {
        System.out.println(url);
        return ds.getConnection();
    }
}
