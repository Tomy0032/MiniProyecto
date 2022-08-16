package clases;

public class Funcionalidad {

	private String nombre;
	private String descripcion;
	
	public Funcionalidad(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
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
