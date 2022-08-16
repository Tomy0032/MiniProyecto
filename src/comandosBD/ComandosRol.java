package comandosBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import clases.Rol;

public class ComandosRol {

	public static void insertarRol(Connection connection, Rol r) {
		
		String insert = "INSERT INTO ROL(ID_ROL,NOMBRE,DESCRIPCION) VALUES (SEQ_ROL.NEXTVAL,?,?)";
		
		try {
			
			PreparedStatement sentencia = connection.prepareStatement(insert);
			
			sentencia.setString(1, r.getNombre());
			sentencia.setString(2, r.getDescripcion());
			
			int resultado = sentencia.executeUpdate();
			System.out.println("*****************************************************************");
			System.out.println("Se ha insertado un rol");
			System.out.println("*****************************************************************");
			
		}catch(SQLException e) {
			
			System.out.println("*****************************************************************");
			System.out.println("Error en la consulta -> " + insert);
			System.out.println("*****************************************************************");
			e.printStackTrace();
			
		}
		
	}
	
}
