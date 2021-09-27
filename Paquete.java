import java.util.*;
public class Paquete {
	
	private int identificador;
	private Date fechaEntrega;
	private Date fechaRecepcion;
	private int peso;
	private String direccion;
	private int costo;
	private Persona personaOrigen;
	
	public Paquete() {
		
	}
	public Paquete(int identificador, Date fechaEntrega, Date fechaRecepcion, int peso, String direccion, int costo,
			Persona personaOrigen) {
		this.identificador = identificador;
		this.fechaEntrega = fechaEntrega;
		this.fechaRecepcion = fechaRecepcion;
		this.peso = peso;
		this.direccion = direccion;
		this.costo = costo;
		this.personaOrigen = personaOrigen;
	}
	

	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}
	public void setFechaRecepcion(Date fechaRecepción) {
		this.fechaRecepcion = fechaRecepción;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String dirección) {
		this.direccion = dirección;
	}
	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}
	public Persona getPersonaOrigen() {
		return personaOrigen;
	}
	public void setPersonaOrigen(Persona personaOrigen) {
		this.personaOrigen = personaOrigen;
	}
	
	public String datosPaquete() {
		return "Paquete N°:"+identificador+"\nPeso:"+peso+"kg"+"\nCosto:"+costo+"\nPersona:"+personaOrigen.getNombre()+"\nfecha de entrega:"+fechaEntrega+ "\nfecha recepcion:"+fechaRecepcion;
	}
	
	public String datosDueño() {
		return "Nombre:"+personaOrigen.getNombre()+"\nDNI:"+personaOrigen.getDni()+"\nCelular:"+personaOrigen.getCelular();
	}
	
	public int datosDNI() {
		return personaOrigen.getDni();
	} 
	public void RegDatosDNI(int dni) {
		this.personaOrigen=new Persona(null,dni,0);
	}
}
