
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class GestorTipoDeDocumento {

    private static Connection conexion;

    public GestorTipoDeDocumento() {
        modelo.Conexion conn=new modelo.Conexion();
        conexion=conn.getConexion();        
    }
    
    public Vector cargarTipoDeDocumentoBD(int id){
        
        Vector datosCmb=new Vector();
        modelo.TipoDeDocumento tipoDoc=null;
        String sql=null;
        
        if(id==0){
            sql="SELECT * FROM desktoptienda.tipo_documento ORDER BY nombre_tipo_documento DESC";
            tipoDoc=new modelo.TipoDeDocumento(0,"Seleccione");
            datosCmb.add(tipoDoc);            
        }else{
            sql="SELECT * FROM desktoptienda.tipo_documento WHERE idTipo_documento="+id;
        }
        try {
          PreparedStatement ps = conexion.prepareStatement(sql);
	  ResultSet rs = ps.executeQuery();
          while(rs.next()){
              tipoDoc=new modelo.TipoDeDocumento();
              tipoDoc.setId(rs.getInt("idTipo_documento"));
              tipoDoc.setNombre(rs.getString("nombre_tipo_documento"));
              datosCmb.add(tipoDoc);
              
          }
            
          ps.close();;
          rs.close();
          return datosCmb;
        } catch (Exception e) {
            
        }
        return datosCmb;
    }

    
}
