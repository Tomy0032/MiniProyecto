package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import clases.*;

public class ConexionBD {

	public static void main(String[] args) {
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Se encontró el Driver para Oracle DB - La librería está referenciada");
			
			Connection connection = null;
			
			try {
				
				connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"MINIPROYECTO",
					"MINIPROYECTO");
				
				System.out.println("Conexión con exito, es posible acceder a la base de datos!");
				
				//-----------------------------------------------------------------------------
				
				//String insert = "INSERT INTO ROL (ID_ROL,NOMBRE) VALUES(3,'OPERADOR DE SECCION')";
				//String select = "SELECT * FROM ROL";
				
				String insert = "INSERT INTO FUNCIONALIDAD (ID_FUNCIONALIDAD,NOMBRE) VALUES(5,'SUELDOS')";
				String select = "SELECT * FROM PERSONA";
								
				Persona p1 = new Persona("53719385","Gonzalez","Gonzalez","Tomas",null,Date.valueOf("2003-09-21"),"1234","tomas");
				
				insert = p1.insertPersona();
				
				try {
					
					Statement sentencia = connection.createStatement();
					
					ResultSet result = sentencia.executeQuery(insert);				
					ResultSet result2 = sentencia.executeQuery(select);
					
					
					
					System.out.println("----------------------------------------------");
					System.out.println(select);
					System.out.println("----------------------------------------------");
							
					while(result2.next()) {
						
						System.out.println(
								result2.getString("ID_PERSONA") + " " + result2.getString("NOMBRE1") + " " +	result2.getString("APELLIDO1"));
						
					}
					
					System.out.println("----------------------------------------------");
					
				}catch(SQLException e) {
					
					System.out.println("*****************************************************************");
					System.out.println("Error en la consulta -> " + select);
					System.out.println("*****************************************************************");
					e.printStackTrace();
					return;
					
				}
				
				//-----------------------------------------------------------------------------
				
				try {
					
					connection.close();
					System.out.println("Conexión cerrada con éxito, ya no es posible acceder a la base de datos");
					
				}catch(SQLException e) {
					
					System.out.println("Error al cerrar la conexión!!");
					e.printStackTrace();
					return;
					
				}
				
			}catch(SQLException e) {
				
				System.out.println("No logramos instanciar una conexión!");
				e.printStackTrace();
				return;
				
			}
		
		}catch(ClassNotFoundException e) {
			
			System.out.println("No tienes el driver en tu build-path?");
			e.printStackTrace();
			return;
			
		}

	}

}
