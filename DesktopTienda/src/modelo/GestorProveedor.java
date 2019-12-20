package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class GestorProveedor {

    private static Connection conexion;

    public GestorProveedor() {
        modelo.Conexion conn = new modelo.Conexion();
        conexion = conn.getConexion();
    }

    public LinkedList<Proveedor> getProveedorBy(int parametro, String valor) {
        LinkedList<Proveedor> pro = new LinkedList<Proveedor>();

        String sql = "";

        switch (parametro) {
            case 1:
                sql = "SELECT * FROM desktoptienda.proveedor WHERE numero_documento LIKE '%"+valor+"%' ";
                break;

            case 2:
                sql = "SELECT * FROM desktoptienda.proveedor WHERE nombre_comercial LIKE '%"+valor+"%' ";
                break;

            default:
                sql = "SELECT * FROM desktoptienda.proveedor";
                break;

        }

        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                pro.add(new Proveedor(
                        rs.getInt("idProveedor"),
                        rs.getLong("numero_documento"),
                        rs.getInt("cod_tipo_documento"),
                        rs.getString("nombre_proveedor"),
                        rs.getString("apellido_proveedor"),
                        rs.getString("nombre_comercial"),
                        rs.getString("direccion"),
                        rs.getInt("cod_ciudad"),
                        rs.getInt("cod_provincia"),
                        rs.getLong("telefono")
                ));
            }

            st.close();
            rs.close();

        } catch (SQLException Exe) {
            JOptionPane.showMessageDialog(null, "Error " + Exe.getMessage());
        }

        return pro;

    }

}
