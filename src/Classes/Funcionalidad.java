package Classes;

public class Funcionalidad {

	private int idFuncionalidad;
	private String nombre;
	private String descripcion;
	
	public Funcionalidad(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public int getIdFuncionalidad() {
		return idFuncionalidad;
	}

	public void setIdFuncionalidad(int idFuncionalidad) {
		this.idFuncionalidad = idFuncionalidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String insertarFuncionalidad() {
		String insert = "INSERT INTO FUNCIONALIDAD VALUES (SEQ_FUNCIONALIDAD.NEXTVAL,?,?)";
		return insert;
	}

	@Override
	public String toString() {
		return "Funcionalidad [nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
	
}
