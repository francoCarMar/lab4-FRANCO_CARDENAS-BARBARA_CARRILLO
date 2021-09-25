package laboratorio04;

public class Persona {
	private String nombre;
	private int dni;
	private int celular;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public int getCelular() {
		return celular;
	}

	public void setCelular(int celular) {
		this.celular = celular;
	}

	public Persona(String nombre, int dni, int celular) {
		this.nombre = nombre;
		this.dni = dni;
		this.celular = celular;
	}
	
}
