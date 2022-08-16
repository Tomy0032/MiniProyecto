package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import clases.*;
import comandosBD.*;

public class ConexionBD {

	public static void main(String[] args) {
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("Se encontró el Driver para Oracle DB - La librería está referenciada");
			
			Connection connection = null;
			
			try {
				
				connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"MINIPROYECTO",
					"MINIPROYECTO");
				
				//System.out.println("Conexión con exito, es posible acceder a la base de datos!");
				
				//-----------------------------------------------------------------------------
				
				//String insert = "INSERT INTO ROL (ID_ROL,NOMBRE) VALUES(3,'OPERADOR DE SECCION')";
				//String select = "SELECT * FROM ROL";
												
				Persona persona = new Persona(
						"12345678",
						"Gonzalez",
						null,
						"Pepito",
						null,
						Date.valueOf("1999-01-01"),
						"1234",
						"pepito@gmail.com");
				
				Rol rol = new Rol("Administrador","Rol de administrador");
				
				Funcionalidad funcionalidad = new Funcionalidad("Prueba 7","Prueba");
				
				
				ComandosPersona.insertarPersona(connection, persona);
				//ComandosRol.insertarRol(connection, rol);
				//ComandosFunciones.insertarFuncion(connection, funcionalidad);
				
				String select = "SELECT * FROM PERSONA";
				
				try {
					
					Statement sentencia = connection.createStatement();
		
					ResultSet result2 = sentencia.executeQuery(select);
					
					
					
					System.out.println("----------------------------------------------");
					System.out.println(select);
					System.out.println("----------------------------------------------");
							
					while(result2.next()) {
						
						System.out.println(
								result2.getString("ID_PERSONA") + " - " + result2.getString("NOMBRE1") + " - " +	result2.getString("APELLIDO1"));
						
					}
					
					System.out.println("----------------------------------------------");
					
				}catch(SQLException e) {
					
					System.out.println("*****************************************************************");
					System.out.println("Error en la consulta -> " + select);
					System.out.println("*****************************************************************");
					e.printStackTrace();
					return;
					
				}
				
				select = "SELECT * FROM ROL";
				
				try {
					
					Statement sentencia = connection.createStatement();
		
					ResultSet result2 = sentencia.executeQuery(select);
					
					
					
					System.out.println("----------------------------------------------");
					System.out.println(select);
					System.out.println("----------------------------------------------");
							
					while(result2.next()) {
						
						System.out.println(
								result2.getString("ID_ROL") + " - " + result2.getString("NOMBRE") + " - " +	result2.getString("DESCRIPCION"));
						
					}
					
					System.out.println("----------------------------------------------");
					
				}catch(SQLException e) {
					
					System.out.println("*****************************************************************");
					System.out.println("Error en la consulta -> " + select);
					System.out.println("*****************************************************************");
					e.printStackTrace();
					return;
					
				}
				
				select = "SELECT * FROM FUNCIONALIDAD";

				try {
					
					Statement sentencia = connection.createStatement();
				
					ResultSet result2 = sentencia.executeQuery(select);
					
					
					
					System.out.println("----------------------------------------------");
					System.out.println(select);
					System.out.println("----------------------------------------------");
							
					while(result2.next()) {
						
						System.out.println(
								result2.getString("ID_FUNCIONALIDAD") + " - " + result2.getString("NOMBRE") + " - " +	result2.getString("DESCRIPCION"));
						
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
					//System.out.println("Conexión cerrada con éxito, ya no es posible acceder a la base de datos");
					
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
