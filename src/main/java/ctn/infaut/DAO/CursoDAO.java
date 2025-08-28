/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ctn.infaut.DAO;

import ctn.infaut.connection.Conexion;
import ctn.infaut.controllers.Curso;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author
 * isaacfeltes
 */
public class CursoDAO {
    private Conexion con;

    public CursoDAO() throws SQLException {
        con = new Conexion();
    }

    public boolean insertar(Curso c) throws SQLException {
        String sql = "INSERT INTO infaut.curso (descripcion) VALUES (?)";
        try (PreparedStatement pstmt = con.getCon().prepareStatement(sql)) {
            pstmt.setString(1, c.getDescripcion());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Ocurrio un error al insertar el curso: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(Curso c) throws SQLException {
        String sql = "DELETE FROM infaut.curso WHERE id_curso = ?";
        try (PreparedStatement pstmt = con.getCon().prepareStatement(sql)) {
            pstmt.setInt(1, c.getIdCurso());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Ocurrio un error al eliminar el curso: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Curso> consulta() throws SQLException {
        ArrayList<Curso> rooms = new ArrayList<>();
        String sql = "SELECT * FROM infaut.curso WHERE 1=1";

        try (Statement stmt = con.getCon().createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                rooms.add(new Curso(
                        rs.getInt("id_curso"),
                        rs.getString("descripcion")));
            }
            return rooms;
        } catch (SQLException e) {
            System.err.println("Ocurrio un error al obtener las filas: " + e.getMessage());
            return null;
        }
    }

    public boolean modificar(Curso a) throws SQLException {
        String sql = "UPDATE infaut.curso SET descripcion = ? WHERE id_curso = ?";
        try (PreparedStatement pstmt = con.getCon().prepareStatement(sql)) {
            pstmt.setString(1, a.getDescripcion());
            pstmt.setInt(2, a.getIdCurso());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Ocurrio un error al modificar el curso: " + e.getMessage());
            return false;
        }
    }
}

