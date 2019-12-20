
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class GestorCiudad {
    private static Connection conexion;

    public GestorCiudad() {
        modelo.Conexion conn=new modelo.Conexion();
        conexion=conn.getConexion();        
    }
    
     public Vector cargarCiudadesDeBD(int id){
        
        Vector datosCmb=new Vector();
        modelo.Ciudad ciudades=null;
        String sql=null;
        
        if(id==0){
            sql="SELECT * FROM desktoptienda.ciudad ORDER BY ciudad_nombre DESC";
            ciudades=new modelo.Ciudad(0,"Seleccione");
            datosCmb.add(ciudades);            
        }else{
            sql="SELECT * FROM desktoptienda.ciudad WHERE idTipo_documento="+id;
        }
        try {
          PreparedStatement ps = conexion.prepareStatement(sql);
	  ResultSet rs = ps.executeQuery();
          while(rs.next()){
              ciudades=new modelo.Ciudad();
              ciudades.setIdCiudad(rs.getInt("idCiudad"));
              ciudades.setNombreCidad(rs.getString("ciudad_nombre"));
              ciudades.setcP(rs.getInt("cp"));
              ciudades.setProvinciaId(rs.getInt("provincia_id"));
              datosCmb.add(ciudades);
              
          }
            
          ps.close();;
          rs.close();
          return datosCmb;
        } catch (Exception e) {
            
        }
        return datosCmb;
    }
    
    
}
