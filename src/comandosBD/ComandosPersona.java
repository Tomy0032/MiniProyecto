package comandosBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import clases.Persona;

public class ComandosPersona {
	
	public static void insertarPersona(Connection connection, Persona p) {
		
		String insert = "INSERT INTO PERSONA(ID_PERSONA,DOCUMENTO,APELLIDO1,APELLIDO2,NOMBRE1,NOMBRE2,FEC_NAC,CLAVE,MAIL) VALUES (SEQ_PERSONA.NEXTVAL,?,?,?,?,?,?,?,?)";
		
		try {
			
			PreparedStatement sentencia = connection.prepareStatement(insert);
			
			sentencia.setString(1, p.getDocumento());
			sentencia.setString(2, p.getApellido1());
			sentencia.setString(3, p.getApellido2());
			sentencia.setString(4, p.getNombre1());
			sentencia.setString(5, p.getNombre2());
			sentencia.setDate(6, p.getFechaNac());
			sentencia.setString(7, p.getClave());
			sentencia.setString(8, p.getMail());
			
			int resultado = sentencia.executeUpdate();
			
			System.out.println("*****************************************************************");
			System.out.println("Se ha insertado una persona");
			System.out.println("*****************************************************************");
			
		}catch(SQLException e) {
			
			System.out.println("*****************************************************************");
			System.out.println("Error en la consulta -> " + insert);
			System.out.println("*****************************************************************");
			e.printStackTrace();
			
		}
	}

}
