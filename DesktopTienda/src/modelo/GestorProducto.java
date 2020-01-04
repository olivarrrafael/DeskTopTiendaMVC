
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


public class GestorProducto {
Producto producto;    
private static Connection conexion;

    public GestorProducto() {
         modelo.Conexion conn=new modelo.Conexion();
         conexion=conn.getConexion();      
    }
    
    public void registroProductoBD(Producto pro){
        
        String sql = "INSERT INTO desktoptienda.producto (nombre_producto,descripcion_producto,precio_costo,precio_venta,stock,cod_tipo_producto,cod_proveedor,fecha_ingreso,fecha_vencimiento)"+
                "Values(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps=conexion.prepareStatement(sql);
            
            ps.setString(1,pro.getNombreProducto());
            ps.setString(2,pro.getDescripcionProducto());
            ps.setDouble(3,pro.getPrecioCosto());
            ps.setDouble(4,pro.getPrecioVenta());
            ps.setInt(5,pro.getStock());
            ps.setInt(6,pro.getTipoProducto());
            ps.setInt(7,pro.getProveedor());
            ps.setDate(8,convertDate(pro.getFechaIngreso()));
            ps.setDate(9,convertDate(pro.getFechaVencimiento()));
            
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null,"producto registrado");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }                
    }
    
    public LinkedList<Producto> buscarProducto(int parametro, int tipoProduct,int proveedor) {
        LinkedList<Producto> pro = new LinkedList<Producto>();

        String sql = "";

        switch (parametro) {
            case 1:
                sql = "SELECT * FROM desktoptienda.proveedor WHERE cod_tipo_producto LIKE '%"+tipoProduct+"%' ";
                break;

            case 2:
                sql = "SELECT * FROM desktoptienda.proveedor WHERE cod_proveedor LIKE '%"+proveedor+"%' ";
                break;
            case 3:
                sql= "SELECT * FROM desktoptienda.proveedor WHERE cod_tipo_producto LIKE '%"+tipoProduct+"%' AND nombre_comercial LIKE '%"+proveedor+"%' ";
            default:
                sql = "SELECT * FROM desktoptienda.producto";
                break;

        }

        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                pro.add(new Producto(
                        rs.getInt("idProducto"),
                        rs.getString("nombre_producto"),
                        rs.getString("descripcion_producto"),
                        rs.getDouble("precio_costo"),
                        rs.getDouble("precio_venta"),
                        rs.getInt("stock"),
                        rs.getInt("cod_tipo_producto"),
                        rs.getInt("cod_proveedor"),
                        rs.getDate("fecha_ingreso"),
                        rs.getDate("fecha_vencimiento")
                ));
            }

            st.close();
            rs.close();

        } catch (SQLException Exe) {
            JOptionPane.showMessageDialog(null, "Error algo" + Exe.getMessage());
        }

        return pro;

    }
    
    
    
    public void eliminarProductoBD(int id){
        String sql="DELETE FROM desktoptienda.producto WHERE idProducto='"+id+"'";
        try {
            PreparedStatement ps=conexion.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null,"producto eliminado");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex.getMessage());
        }
}
    
    private java.sql.Date convertDate(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
    
    
}
