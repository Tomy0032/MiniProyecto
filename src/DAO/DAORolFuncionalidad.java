package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import BDControl.DatabaseManager;
import Classes.Funcionalidad;
import Classes.Rol;

public class DAORolFuncionalidad {
	
	private final static String INSERTAR = "INSERT INTO ROL_FUNCION(ID_ROL,ID_FUNCION) VALUES (?,?)";
	
	public static boolean insertar(Rol r, Funcionalidad f) {
		
		try {
			
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(INSERTAR);
			
			sentencia.setInt(1, r.getIdRol());
			sentencia.setInt(2, f.getIdFuncionalidad());
			
			int resultado = sentencia.executeUpdate();
			
			return resultado>0;
					
		}catch(SQLException e) {
			return false;		
		}
	}

}
