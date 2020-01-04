
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;

public class GestorTipoProducto {
    
private static Connection conexion;

    public GestorTipoProducto() {
         modelo.Conexion conn=new modelo.Conexion();
         conexion=conn.getConexion();      
    }
    
    public Vector cargarTipoProductos(int id){
        Vector datosCmb=new Vector();
        modelo.TipoProducto tipoProducto=null;
        String sql=null;
        
        if(id==0){
            sql="SELECT * FROM desktoptienda.tipo_producto ORDER BY nombre_tipo_producto DESC";
            tipoProducto=new modelo.TipoProducto(0,"Seleccione");
            datosCmb.add(tipoProducto);            
        }else{
            sql="SELECT * FROM desktoptienda.tipo_documento WHERE idTipo_documento="+id;
        }
        try {
          PreparedStatement ps = conexion.prepareStatement(sql);
	  ResultSet rs = ps.executeQuery();
          while(rs.next()){
              tipoProducto=new modelo.TipoProducto();
              tipoProducto.setIdTipoProducto(rs.getInt("idTipo_producto"));
              tipoProducto.setNombreTipoProducto(rs.getString("nombre_tipo_producto"));
              datosCmb.add(tipoProducto);
              
          }
            
          ps.close();;
          rs.close();
          return datosCmb;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return datosCmb;
    }
    }

  

