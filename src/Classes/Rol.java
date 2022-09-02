package Classes;

public class Rol {

	private String nombre;
	private String descripcion;
		
	public Rol(String nombre, String descripcion) {
		super();
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
	
	boolean acceso(Persona p) {
		return true;
	}

	public String insertarRol() {
		String insert = "INSERT INTO ROL VALUES (SEQ_FUNCIONALIDAD.NEXTVAL,?,?)";
		return insert;
	}
	
	@Override
	public String toString() {
		return "Rol [nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
	
}
