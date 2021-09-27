import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EmpresaInnovadora {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws ParseException {
		Persona[] personas = new Persona[10];
		Paquete[] paquetes = new Paquete[20];
		String confir = "";
		int opcion, dni;
		int i = 0;
		System.out.println("\t\tBIENVENIDO A EMPRESA INNOVADORA SAC");
		do {
			System.out.println("Elija una opción:");
			System.out.println("(1) Realizar el registro de personas\r\n" + "(2) Realizar el registro de paquetes\r\n"
					+ "(3) Registrar fecha de entrega del paquete\r\n"
					+ "(4) Mostrar los paquetes que tengan más de cierta cantidad de kilos\r\n"
					+ "(5) Mostrar los paquetes que tengan el costo igual ingresado\r\n"
					+ "(6) Mostrar los paquetes pendientes de ser enviados\r\n"
					+ "(7) Mostrar los datos de la persona al proporcionar un paquete\r\n"
					+ "(8) Mostrar los datos de los paquetes al ingresar el dni de la persona");
			opcion = sc.nextInt();

			switch (opcion) {
			case 1:
				personas[i] = regPersona(i);
				break;
			case 2:
				dni = ingIden();
				paquetes[i] = regPaquete(paquetes, opcion);
				paquetes[i].RegDatosDNI(dni);
				break;
			case 3:
				dni = ingIden();
				Date fechaRecepcion;
				if (dniEnPaq(dni, paquetes)) {
					fechaRecepcion = regFecha(opcion);
					paquetes = añadirFechaApaq(paquetes, dni, fechaRecepcion);
				} else
					System.out.println("Paquete no registrado, debe registrar antes el paquete");
				break;
			case 4:
				Maskilos(paquetes);
				break;
			case 5:
				igualCosto(paquetes);
				break;
			case 6:
				mostPendientes(paquetes);
				break;
			case 7:
				datosxPaquete(paquetes);
				break;
			case 8:
				dni = ingIden();
				datosxdni(dni, paquetes);
				break;
			}
			paquetes = añadirPerApaq(personas, paquetes);
			System.out.println("Desea continuar? si/no");
			confir = sc.next();
			i++;
		} while (confir.equals("si"));

	}

	public static Persona regPersona(int i) {
		System.out.println("Persona" + (i + 1));
		System.out.print("Ingrese nombre:");
		String nombre = sc.next();
		System.out.print("Ingrese numero de dni:");
		int dni = sc.nextInt();
		System.out.print("Ingrese numero telefonico:");
		int telef = sc.nextInt();
		Persona persona = new Persona(nombre, dni, telef);
		return persona;
	}

	public static Paquete regPaquete(Paquete[] paquetes, int opcion) throws ParseException {
		System.out.print("Ingrese Identificador:");
		int identificador = sc.nextInt();
		Date fechaEntrega = regFecha(opcion);
		System.out.print("Ingrese direccion destino:");
		String direccion = sc.next();
		System.out.print("Ingrese peso:");
		int peso = sc.nextInt();
		System.out.print("Ingrese costo:");
		int costo = sc.nextInt();
		Date fechaRecepcion = null;
		Paquete paq = new Paquete(identificador, fechaEntrega, fechaRecepcion, peso, direccion, costo, null);
		return paq;

	}

	public static Date regFecha(int opcion) throws ParseException {
		if (opcion == 2)
			System.out.print("Ingrese fecha de recepción:");
		else
			System.out.print("Ingrese fecha de entrega:");
		String fechaComoTexto = sc.next();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = sdf.parse(fechaComoTexto);
		return fecha;
	}

	public static int ingIden() {
		System.out.print("Ingrese DNI:");
		int dni = sc.nextInt();
		return dni;
	}

	public static Paquete[] añadirPerApaq(Persona[] per, Paquete[] paquetes) {
		for (int i = 0; i < per.length; i++) {
			for (int j = 0; j < paquetes.length; j++) {
				if (per[i] == null || paquetes[j] == null)
					continue;
				if (per[i].getDni() == paquetes[j].datosDNI()) {
					paquetes[j].setPersonaOrigen(per[i]);
				}
			}
		}
		return paquetes;
	}

	public static Paquete[] añadirFechaApaq(Paquete[] paquetes, int dni, Date fecha) {
		for (int i = 0; i < paquetes.length; i++) {
			if (paquetes[i] == null)
				continue;
			if (paquetes[i].datosDNI() == dni)
				paquetes[i].setFechaRecepcion(fecha);
		}
		return paquetes;
	}

	public static boolean dniEnPaq(int dni, Paquete[] paquetes) {
		boolean confir = false;
		for (Paquete i : paquetes) {
			if (i == null)
				continue;
			if (i.datosDNI() == dni)
				confir = true;
		}
		return confir;
	}

	public static void mosPaquetes(Paquete[] paquetes) {
		for (Paquete i : paquetes) {
			if (i == null)
				continue;
			else
				System.out.println(i.datosPaquete());
		}
	}

	public static void Maskilos(Paquete[] paquetes) {
		System.out.print("Ingrese cantidad de kilos:");
		double kg = sc.nextDouble();
		System.out.println("Paquetes con más peso:");
		for (int i = 0; i < paquetes.length; i++) {
			if (paquetes[i] == null)
				continue;
			if (paquetes[i].getPeso() > kg) {
				System.out.println(paquetes[i].datosPaquete());
			}
		}
	}

	public static void igualCosto(Paquete[] paquetes) {
		System.out.println("Ingrese el costo:");
		double costo = sc.nextDouble();
		for (int i = 0; i < paquetes.length; i++) {
			if (paquetes[i] == null)
				continue;
			if (costo == paquetes[i].getCosto()) {
				System.out.println(paquetes[i].datosPaquete());
			}
		}
	}

	public static void mostPendientes(Paquete[] paquetes) {
		for (Paquete i : paquetes) {
			if (i == null)
				continue;
			else if (i.getFechaRecepcion() == null) {
				System.out.println(
						"El paquete con identificador " + i.getIdentificador() + "\ndel cliente" + i.datosDueño());
			}
		}
	}

	public static void datosxdni(int dni, Paquete[] paquetes) {
		for (Paquete i : paquetes) {
			if (i == null)
				continue;
			if (i.datosDNI() == dni)
				System.out.println(i.datosPaquete());
		}
	}

	public static void datosxPaquete(Paquete[] paquetes) {
		System.out.println("Ingrese N° identificador del paquete:");
		int identif = sc.nextInt();
		for (Paquete i : paquetes) {
			if (i == null)
				continue;
			if (i.getIdentificador() == identif) {
				System.out.println(i.datosDueño());
			}
		}

	}
}