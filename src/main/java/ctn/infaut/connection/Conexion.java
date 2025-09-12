package ctn.infaut.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Conexion {
    Connection con;

    public Conexion() throws SQLException {
        Properties props = new Properties();
        try (FileInputStream db = new FileInputStream("db.properties")) {
            props.load(db);

            System.out.println("Se conectó con éxito a la base de datos, YIPEEE!");
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        } catch (FileNotFoundException fe) {
            System.err.println("No se encontró el archivo db.properties:" + fe.getMessage());
        } catch (IOException ie) {
            System.err.println("Error al leer las credenciales de la base de datos" + ie.getMessage());

        }
    }

    public Connection getCon() throws SQLException {
        return con;
    }
}
