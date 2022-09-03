package Classes;

public class RolFuncionalidad {
	
	private int idRol;
	private String nombreRol;
	private int idFuncionalidad;
	private String nombreFuncionalidad;
		
	
	public RolFuncionalidad(String nombreRol, String nombreFuncionalidad) {
		this.nombreRol = nombreRol;
		this.nombreFuncionalidad = nombreFuncionalidad;
	}

	public int getIdRol() {
		return idRol;
	}
	
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
	
	public int getIdFuncionalidad() {
		return idFuncionalidad;
	}
	
	public void setIdFuncionalidad(int idFuncionalidad) {
		this.idFuncionalidad = idFuncionalidad;
	}
	
	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public String getNombreFuncionalidad() {
		return nombreFuncionalidad;
	}

	public void setNombreFuncionalidad(String nombreFuncionalidad) {
		this.nombreFuncionalidad = nombreFuncionalidad;
	}

	@Override
	public String toString() {
		return "RolFuncionalidad [idRol=" + idRol + ", idFuncionalidad=" + idFuncionalidad + "]";
	}	

}
