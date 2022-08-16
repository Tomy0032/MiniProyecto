package comandosBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import clases.Funcionalidad;

public class ComandosFunciones {

	public static void insertarFuncion(Connection connection, Funcionalidad f) {
		
		String insert = "INSERT INTO FUNCIONALIDAD(ID_FUNCIONALIDAD,NOMBRE,DESCRIPCION) VALUES (SEQ_FUNCIONALIDAD.NEXTVAL,?,?)";
		
		try {
			
			PreparedStatement sentencia = connection.prepareStatement(insert);
			
			sentencia.setString(1, f.getNombre());
			sentencia.setString(2, f.getDescripcion());
			
			int resultado = sentencia.executeUpdate();
			System.out.println("*****************************************************************");
			System.out.println("Se ha insertado una funcionalidad");
			System.out.println("*****************************************************************");
			
		}catch(SQLException e) {
			
			System.out.println("*****************************************************************");
			System.out.println("Error en la consulta -> " + insert);
			System.out.println("*****************************************************************");
			e.printStackTrace();
			
		}
	}
}
