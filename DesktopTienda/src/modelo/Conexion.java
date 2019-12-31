package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
	
private static Connection conexion;
	
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost/desktoptienda";
	String ip = "127.0.0.1";
	String bd = "softcafe";
	String user = "root";
	String password = "rafaelrey2";
	
	public Conexion() {
		try {
			Class.forName(driver).newInstance();
			conexion = DriverManager.getConnection(url,user,password);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage()+" \n Error en la Red.");
		}
	}
	
	public Connection getConexion() {
		return conexion;
	}
	
	public Connection cerrarConexion() throws SQLException{
		conexion.close();
		conexion = null;
		return conexion;
	}

}
