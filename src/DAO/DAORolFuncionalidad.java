package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import BDControl.DatabaseManager;
import Classes.Funcionalidad;
import Classes.Rol;
import Classes.RolFuncionalidad;

public class DAORolFuncionalidad {
	
	private final static String INSERTAR = "INSERT INTO ROL_FUNCION(ID_ROL,ID_FUNCION) VALUES (?,?)";
	private final static
	String SELECT_ALL = 
		"SELECT R.NOMBRE ROL, F.NOMBRE FUNCIONALIDAD FROM ROL_FUNCION RF INNER JOIN ROL R ON R.ID_ROL = RF.ID_ROL INNER JOIN FUNCIONALIDAD F ON F.ID_FUNCIONALIDAD = RF.ID_FUNCION ORDER BY R.NOMBRE";
	
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
	
	public static LinkedList<RolFuncionalidad> findAll(){
		
		LinkedList<RolFuncionalidad> nombres = new LinkedList<>();
		RolFuncionalidad rf = null;
		
		try {
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(SELECT_ALL);
			
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()) {
				String rol = resultado.getString("ROL"); 
				String funcionalidad = resultado.getString("FUNCIONALIDAD");
				rf = new RolFuncionalidad(rol,funcionalidad);
				nombres.add(rf);
			}
			
			return nombres;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
