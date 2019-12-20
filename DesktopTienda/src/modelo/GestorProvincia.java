
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;


public class GestorProvincia {
    
    private static Connection conexion;

    public GestorProvincia(){
        
        modelo.Conexion conn=new modelo.Conexion();
        conexion=conn.getConexion();      
    }
    
     public Vector cargarPronvinciasDeBD(int id){
        
        Vector datosCmb=new Vector();
        modelo.Provincia provincias=null;
        String sql=null;    
        sql="SELECT * FROM desktoptienda.provincia ORDER BY provincia_nombre DESC";
        provincias=new modelo.Provincia(0,"Seleccione");
        datosCmb.add(provincias);            
        
        try {
          PreparedStatement ps = conexion.prepareStatement(sql);
	  ResultSet rs = ps.executeQuery();
          while(rs.next()){
              provincias=new modelo.Provincia();
              provincias.setIdProvincia(rs.getInt("idProvincia"));
              provincias.setNombrePronvicia(rs.getString("provincia_nombre"));
              datosCmb.add(provincias);
              
          }
            
          ps.close();;
          rs.close();
          return datosCmb;
        } catch (Exception e) {
            
        }
        return datosCmb;
    }
    
}
