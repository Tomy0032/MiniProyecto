package DAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BDControl.DatabaseManager;
import Classes.Persona;

public class DAOPersona {
	
	private static Connection connection = DatabaseManager.getConnection();
	
	private final static
	String INSERTAR_PERSONA = "INSERT INTO PERSONA(ID_PERSONA,DOCUMENTO,APELLIDO1,APELLIDO2,NOMBRE1,NOMBRE2,FEC_NAC,CLAVE,MAIL) VALUES (SEQ_PERSONA.NEXTVAL,?,?,?,?,?,?,?,?)";
	
	private final static
	String COMPROBAR_LOGIN = "SELECT * FROM PERSONA WHERE MAIL=? AND CLAVE=?";
	
	public static boolean insertarPersona(Persona p) {
				
		try {
			
			PreparedStatement sentencia = connection.prepareStatement(INSERTAR_PERSONA);
			
			sentencia.setString(1, p.getDocumento());
			sentencia.setString(2, p.getApellido1());
			sentencia.setString(3, p.getApellido2());
			sentencia.setString(4, p.getNombre1());
			sentencia.setString(5, p.getNombre2());
			sentencia.setDate(6, p.getFechaNac());
			sentencia.setString(7, p.getClave());
			sentencia.setString(8, p.getMail());
			
			int resultado = sentencia.executeUpdate();
			
			return resultado>0;
					
		}catch(SQLException e) {
			return false;		
		}
	}
	
	public static boolean comprobarLogin(String correo, String clave) {
		
		try {
			
			PreparedStatement sentencia = connection.prepareStatement(COMPROBAR_LOGIN);
			sentencia.setString(1, correo);
			sentencia.setString(2, clave);
			
			int resultado = sentencia.executeUpdate();
			
			return resultado>0;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;			
		}
		
	}
	
	public static Persona seleccionarUsuarioLogin(String mail, String clave) {
		
		try {
			
			PreparedStatement sentencia = connection.prepareStatement(COMPROBAR_LOGIN);
			sentencia.setString(1, mail);
			sentencia.setString(2, clave);
			
			ResultSet resultado = sentencia.executeQuery();
			
			String documento = null;
			String apellido1 = null;
			String apellido2 = null;
			String nombre1 = null;
			String nombre2 = null;
			Date fechaNac = null;
			
			while(resultado.next()) {
				documento = resultado.getString("DOCUMENTO");
				apellido1 = resultado.getString("APELLIDO1");
				apellido2 = resultado.getString("APELLIDO2");
				nombre1 = resultado.getString("NOMBRE1");
				nombre2 = resultado.getString("NOMBRE2");
				fechaNac = resultado.getDate("FEC_NAC");
			}
			Persona p = new Persona(documento, apellido1, apellido2, nombre1, nombre2, fechaNac, clave, mail);
			
			return p;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;			
		}
		
	}

}
