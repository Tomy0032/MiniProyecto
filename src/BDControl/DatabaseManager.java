package BDControl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DatabaseManager {
	
	private static Connection databaseConnection;

	private static String CONNECTION_STRING = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String USUARIO = "MINIPROYECTO";
	private static String CLAVE = "MINIPROYECTO";
	
	static {
		
		databaseConnection = null;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Se encontró el Driver para Oracle DB - La librería está referenciada");
			
			try {
				
				databaseConnection = DriverManager.getConnection(CONNECTION_STRING,USUARIO,CLAVE);				
				System.out.println("Conexión con exito, es posible acceder a la base de datos!");		
				
			}catch(SQLException e) {
				
				System.out.println("No logramos instanciar una conexión!");
				e.printStackTrace();
				
			}		
		
		}catch(ClassNotFoundException e) {
			
			System.out.println("No tienes el driver en tu build-path?");
			e.printStackTrace();
			
		}

	}
	
	public static Connection getConnection() {
		return databaseConnection;
	}
	
	public static void cerrarConexion(){
		
		try {
			
			databaseConnection.close();
			System.out.println("Conexión cerrada con éxito, ya no es posible acceder a la base de datos");
			
		}catch(SQLException e) {
			
			System.out.println("Error al cerrar la conexión!!");
			e.printStackTrace();
			return;
			
		}
		
	}
	
}
