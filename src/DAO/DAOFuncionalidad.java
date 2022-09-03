package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import BDControl.DatabaseManager;
import Classes.Funcionalidad;

public class DAOFuncionalidad {
	
	private static final String INSERTAR = "INSERT INTO FUNCIONALIDAD(ID_FUNCIONALIDAD,NOMBRE,DESCRIPCION) VALUES (SEQ_FUNCIONALIDAD.NEXTVAL,?,?)";
	private static final String ELIMINAR_FUNCIONALIDAD = "DELETE FROM FUNCIONALIDAD WHERE NOMBRE = ?";
	private static final String MODIFICAR_FUNCIONALIDAD = "UPDATE FUNCIONALIDAD SET DESCRIIPCION = ? WHERE NOMBRE = ?";
	
	
	public static boolean insertarFuncion(Funcionalidad f){
		
		try {
			
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(INSERTAR);
			
			sentencia.setString(1, f.getNombre());
			sentencia.setString(2, f.getDescripcion());
			
			/*System.out.println("*****************************************************************");
			System.out.println("Se ha insertado una funcionalidad");
			System.out.println("*****************************************************************");*/
			
			int retorno = sentencia.executeUpdate();
			
			return retorno > 0;
			
		}catch(SQLException e) {
			
			System.out.println("*****************************************************************");
			System.out.println("Error en la consulta -> " + INSERTAR);
			System.out.println("*****************************************************************");
			e.printStackTrace();
			return false;
			
		}
	}
	
	public static boolean eliminar(Funcionalidad f) {
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(ELIMINAR_FUNCIONALIDAD);
			sentencia.setString(1, f.getNombre());
			
			int retorno = sentencia.executeUpdate();
			return retorno > 0;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean actualizar(Funcionalidad f) {
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(MODIFICAR_FUNCIONALIDAD);
			sentencia.setString(1, f.getDescripcion());
			sentencia.setString(2, f.getNombre());
			
			int retorno = sentencia.executeUpdate();
			return retorno > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
