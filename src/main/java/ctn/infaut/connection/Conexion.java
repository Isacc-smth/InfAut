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

    private static String url = "jdbc:mysql://localhost:3306/isaacfeltes";
    private static String user = "postgres";
    private static String password = "SpookyKacho";

    private Conexion() {}

    static {
        cargarConfigDeArchivo();   
        reconfigurarDataSource();
    }

    private static void cargarConfigDeArchivo() {
        try (FileInputStream db = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(db);

            url = props.getProperty("db.url", url);
            user = props.getProperty("db.user", user);
            password = props.getProperty("db.password", password);

        } catch (IOException ie) {
            System.out.println("Usando config default...");
            System.out.println("url: " + url);
            System.out.println("user: " + user);
            System.out.println("password: " + password);
        }
    }

    private static void reconfigurarDataSource() {
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);

        if (ds != null && !ds.isClosed()) {
            ds.close();
        }
        ds = new HikariDataSource(config);
    }

    /**
     * Guarda la configuración en archivo y reconfigura el DataSource.
     *
     * @param newUrl una combinacion entre el puerto, host y el nombre de la BD
     * @param newUser el nombre del usuario en la BD (se asume que tiene los permisos necesarios)
     * @param newPassword la contraseña del usuario (se sugiere que tenga una)
     */
    public static void guardarConfig(String newUrl, String newUser, String newPassword) {
        url = newUrl;
        user = newUser;
        password = newPassword;

        Properties props = new Properties();
        props.setProperty("db.url", url);
        props.setProperty("db.user", user);
        props.setProperty("db.password", password);

        try (OutputStream os = new FileOutputStream("db.properties")) {
            props.store(os, "Configuracion puesta por el usuario");
        } catch (IOException e) {
            System.err.println("Error al guardar config: " + e.getMessage());
        }

        reconfigurarDataSource();
    }

    public static Connection getCon() throws SQLException {
        System.out.println("Usando URL: " + url);
        return ds.getConnection();
    }
}
