package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import BDControl.DatabaseManager;
import Classes.Funcionalidad;
import Classes.Rol;

public class DAOFuncionalidad {
	
	private static final String INSERTAR = "INSERT INTO FUNCIONALIDAD(ID_FUNCIONALIDAD,NOMBRE,DESCRIPCION) VALUES (SEQ_FUNCIONALIDAD.NEXTVAL,?,?)";
	private static final String ELIMINAR_FUNCIONALIDAD = "DELETE FROM FUNCIONALIDAD WHERE NOMBRE = ?";
	private static final String MODIFICAR_FUNCIONALIDAD = "UPDATE FUNCIONALIDAD SET DESCRIIPCION = ? WHERE NOMBRE = ?";
	private static final String ALL_FUNCIONALIDADES = "SELECT * FROM FUNCIONALIDAD";
	private static final String BUSCAR_NOMBRE = "SELECT * FROM FUNCIONALIDAD WHERE NOMBRE=?";
	
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
	
	public static LinkedList<Funcionalidad> findAll(){
		
		LinkedList<Funcionalidad> funcionalidades = new LinkedList<>();
		Funcionalidad funcionalidad = null;
		
		try {
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(ALL_FUNCIONALIDADES);
			
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()) {
				funcionalidad = new Funcionalidad(resultado.getString("NOMBRE"),resultado.getString("DESCRIPCION"));
				funcionalidad.setIdFuncionalidad(resultado.getInt("ID_FUNCIONALIDAD"));
				funcionalidades.add(funcionalidad);
			}
			
			return funcionalidades;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static Funcionalidad buscarNombreFuncionalidad(String nombre){
		
		Funcionalidad funcionalidad = null;
		
		try {
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(BUSCAR_NOMBRE);
			statement.setString(1, nombre);
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()) {
				funcionalidad = new Funcionalidad(resultado.getString("NOMBRE"),resultado.getString("DESCRIPCION"));
				funcionalidad.setIdFuncionalidad(resultado.getInt("ID_FUNCIONALIDAD"));
			}
			
			return funcionalidad;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
