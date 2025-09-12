package ctn.infaut.DAO;

import java.sql.SQLException;

import ctn.infaut.connection.Conexion;

public class CursoMateriaDAO {
    private final Conexion con;

    public CursoMateriaDAO() throws SQLException {
        con = new Conexion();
    }
}
