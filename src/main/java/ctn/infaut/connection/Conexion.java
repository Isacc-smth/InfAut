package ctn.infaut.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariConfig;

public class Conexion {
    private static HikariDataSource ds;
    private static HikariConfig config = new HikariConfig();

    private Conexion() {};

    static {
        try (FileInputStream db = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(db);

            config.setJdbcUrl(props.getProperty("db.url"));
            config.setUsername(props.getProperty("db.user"));
            config.setPassword(props.getProperty("db.password"));

            ds = new HikariDataSource(config);

            System.out.println("Se conectó con éxito a la base de datos, YIPEEE!");
        } catch (FileNotFoundException fe) {
            System.err.println("No se encontró el archivo db.properties:" + fe.getMessage());
        } catch (IOException ie) {
            System.err.println("Error al leer las credenciales de la base de datos" + ie.getMessage());

        }
    }

    public static Connection getCon() throws SQLException {
        return ds.getConnection();
    }
}
