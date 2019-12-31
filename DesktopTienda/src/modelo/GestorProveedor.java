package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class GestorProveedor {
    
    private static Connection conexion;
    Proveedor proveedor;
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
    
    public void registroProveedorBD(Proveedor pro){
        
        String sql = "INSERT INTO desktoptienda.proveedor (numero_documento,cod_tipo_documento,nombre_proveedor,apellido_proveedor,nombre_comercial,direccion,cod_ciudad,cod_provincia,telefono)"+
                "Values(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps=conexion.prepareStatement(sql);
            
            ps.setLong(1,pro.getNumDoc());
            ps.setInt(2,pro.getCodTipoDoc());
            ps.setString(3,pro.getNombreProv());
            ps.setString(4,pro.getApellidoProv());
            ps.setString(5,pro.getNombreComercial());
            ps.setString(6,pro.getDireccion());
            ps.setInt(7,pro.getCodCiudad());
            ps.setInt(8, pro.getCodProvincia());
            ps.setLong(9,pro.getTelefono());
            
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null,"proveedor registrado");
            
        } catch (SQLException ex) {
            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }                
    }
    
     public void modificarProveedorBD(Proveedor pro){
         
        String sql = "UPDATE proveedor SET numero_documento=?,cod_tipo_documento=?,nombre_proveedor=?,apellido_proveedor=?,nombre_comercial=?,"
                + "direccion=?,cod_ciudad=?,cod_provincia=?,telefono=? "+
                "WHERE idProveedor =?";
        try {
            PreparedStatement ps=conexion.prepareStatement(sql);
            ps.setLong(1,pro.getNumDoc());
            ps.setInt(2,pro.getCodTipoDoc());
            ps.setString(3,pro.getNombreProv());
            ps.setString(4,pro.getApellidoProv());
            ps.setString(5,pro.getNombreComercial());
            ps.setString(6,pro.getDireccion());
            ps.setInt(7,pro.getCodCiudad());
            ps.setInt(8, pro.getCodProvincia());
            ps.setLong(9,pro.getTelefono());
            ps.setInt(10,pro.getId());
            
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null,"proveedor modificado");
            
        } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex.getMessage());
        }                
    }
    
    public void eliminarProveedorBD(int id){
        String sql="DELETE FROM desktoptienda.proveedor WHERE idProveedor='"+id+"'";
        try {
            PreparedStatement ps=conexion.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null,"proveedor eliminado");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
}

}
