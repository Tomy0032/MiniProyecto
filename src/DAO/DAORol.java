package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import BDControl.DatabaseManager;
import Classes.Rol;


public class DAORol {
	private static final String INSERTAR_ROL = "INSERT INTO ROL(ID_ROL,NOMBRE, DESCRIPCION) VALUES (seq_rol.NEXTVAL, ?, ?)";
	private static final String UPDATE_ROL = "UPDATE ROL SET DESCRIPCION=? WHERE NOMBRE=?";
	private static final String DELETE_ROL = "DELETE FROM ROL WHERE NOMBRE=?";
	private static final String ALL_ROLES = "SELECT * FROM ROL";
	private static final String BUSCAR_NOMBRE = "SELECT * FROM ROL WHERE NOMBRE=?";
	private static final String BUSCAR_ID = "SELECT * FROM ROL WHERE ID_ROL=?";
	
	public static boolean insertarRol(/*Connection connection,*/ Rol r) {
			try {
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(INSERTAR_ROL);
			
			statement.setString(1, r.getNombre());
			statement.setString(2, r.getDescripcion());
			
			int retorno = statement.executeUpdate();
			
			System.out.println("*****************************************************************");
			System.out.println("Se ha insertado un rol");
			System.out.println("*****************************************************************");
			
			return retorno>0;
			} catch(SQLException e) {
				System.out.println("*****************************************************************");
				System.out.println("Error en la consulta -> " + INSERTAR_ROL);
				System.out.println("*****************************************************************");
				e.printStackTrace();
				return false;
			}
		}
		public static boolean modificarRol (Rol rol) {
			try {
				PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(UPDATE_ROL);
				
				statement.setString(1, rol.getDescripcion());
				statement.setString(2, rol.getNombre());
				
				int retorno = statement.executeUpdate();
				
				System.out.println("*****************************************************************");
				System.out.println("Se ha modificado un rol");
				System.out.println("*****************************************************************");
				
				return retorno > 0;
			} catch (SQLException e) {
				System.out.println("*****************************************************************");
				System.out.println("Error en la consulta -> " + UPDATE_ROL);
				System.out.println("*****************************************************************");
				e.printStackTrace();
				return false;
			}
		}
			
			public static boolean deleteRol(Rol rol) {
				try {
					PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(DELETE_ROL);
					statement.setString(1, rol.getNombre());
					
					int retorno = statement.executeUpdate();
					System.out.println("*****************************************************************");
					System.out.println("Se ha eliminado un rol");
					System.out.println("*****************************************************************");
					
					return retorno>0;
				} catch(SQLException e) {
					System.out.println("*****************************************************************");
					System.out.println("Error en la consulta -> " + DELETE_ROL);
					System.out.println("*****************************************************************");
					e.printStackTrace();
					return false;
				}
				
		}
			
			public static LinkedList<Rol> findAll(){
					
				LinkedList<Rol> roles = new LinkedList<>();
				Rol rol = null;
				
				try {
					PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(ALL_ROLES);
					
					ResultSet resultado = statement.executeQuery();
					
					while(resultado.next()) {
						rol = new Rol(resultado.getString("NOMBRE"),resultado.getString("DESCRIPCION"));
						rol.setIdRol(resultado.getInt("ID_ROL"));
						roles.add(rol);
					}
					
					return roles;
					
				}catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
				
			}
			
			public static Rol buscarNombreRol(String nombre){
				
				Rol rol = null;
				
				try {
					PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(BUSCAR_NOMBRE);
					statement.setString(1, nombre);
					ResultSet resultado = statement.executeQuery();
					
					while(resultado.next()) {
						rol = new Rol(resultado.getString("NOMBRE"),resultado.getString("DESCRIPCION"));
						rol.setIdRol(resultado.getInt("ID_ROL"));
					}
					
					return rol;
					
				}catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
				
			}
			
			public static Rol buscarIdRol(int idRol){
				
				Rol rol = null;
				
				try {
					PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(BUSCAR_ID);
					statement.setInt(1, idRol);
					ResultSet resultado = statement.executeQuery();
					
					while(resultado.next()) {
						rol = new Rol(resultado.getString("NOMBRE"),resultado.getString("DESCRIPCION"));
						rol.setIdRol(resultado.getInt("ID_ROL"));
					}
					
					return rol;
					
				}catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
				
			}

}
	
	

