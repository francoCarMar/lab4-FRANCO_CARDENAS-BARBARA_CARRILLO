package laboratorio04;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EmpresaInovadora {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws ParseException {

		Persona[] personas = new Persona[10];
		Paquete[] paquetes = new Paquete[20];
		String confir = "";
		int i = 0;
		do {
			System.out.println("Elija una opcion: " + "n/Registra");
			System.out.println("1. Realizar el registro de personas\r\n" + "2. Realizar el registro de paquetes\r\n"
					+ "3. Registrar cuando el paquete ha sido entregado\r\n"
					+ "4. Mostrar los paquetes que tengan más de cierta cantidad de kilos ingresado por el usuario.\r\n"
					+ "5. Mostrar los paquetes que tengan el costo igual al ingresado por el usuario.\r\n"
					+ "6. Mostrar los paquetes pendientes de ser enviados\r\n"
					+ "7. Mostrar los datos de la persona al proporcionar un paquete.\r\n"
					+ "8. Mostrar los datos de los paquetes al ingresar el dni de la persona.");

			int opcion = sc.nextInt();
			int dni, pos;
			Date fecha;
			switch (opcion) {
			case 1: personas[i] = registroPersonas(i);
				break;
			case 2: dni = ingDni();
				paquetes[i] = ingPaq(i, personas, dni, paquetes);
				pos = buscDniPer(personas, dni);
				if (pos != -1)
					paquetes[i].setPersonaOrigen(personas[pos]);
				else
					System.out.println("Cliente no registrado");
				break;
			case 3:dni = ingDni();
				fecha = entregaF();
				pos = buscDniPaq(paquetes, dni);
				if (pos != -1)
					paquetes[pos].setFechaRecepcion(fecha);
				else
					System.out.println("Cliente no registrado");
				break;
			case 4:
				Maskilos(paquetes);
				break;
			case 5:
				igualCosto(paquetes);
				break;
			case 6:
				System.out.println("");
				break;
			case 7:
				System.out.println("Ingrese N° de paquete:");
				int num = sc.nextInt();
				ingresoPaquete(paquetes[num - 1]);
				break;
			case 8:
				System.out.println("Ingrese dni para buscar paquete:");
				int dnis = sc.nextInt();
				datosxdni(dnis, paquetes);
				break;

			}
            mostrar(paquetes);
			System.out.println("\n" + "¿Desea seguir? si/no:");
			confir = sc.next();
			if (confir.equals("no")) {
				System.out.println("------FIN------");
			}
			i++;
		} while (confir.equals("si"));
	}

	public static Persona registroPersonas(int i) {
		String nomb;
		int dni, cel;
		System.out.println("Ingrese datos " + (i + 1));
		System.out.print("Nombre: ");
		nomb = sc.next();
		System.out.print("dni: ");
		dni = sc.nextInt();
		System.out.print("Celular: ");
		cel = sc.nextInt();
		Persona people = new Persona(nomb, dni, cel);
		return people;
	}

	public static int buscDniPaq(Paquete[] paquete, int dni) {
		int pos = -1;
		for (int i = 0; i < paquete.length; i++) {
			if(paquete[i] == null)continue;
			if (paquete[i].datosDNI() == dni) {
				pos = i;
			}
		}
		return pos;
	}

	public static int buscDniPer(Persona[] per, int dni) {
		int pos = -1;
		for (int i = 0; i < per.length; i++) {
			if(per[i] == null)continue;
			if (per[i].getDni() == dni) {
				pos = i;
			}
		}
		return pos;
	}

	public static Paquete ingPaq(int i, Persona[] personas, int dni, Paquete[] paquetes) throws ParseException {

		int identificador = i + 1;
		System.out.print("Ingrese fecha de entrega");
		String fechaComoTexto = sc.next();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaEntrega = sdf.parse(fechaComoTexto);
		System.out.print("Ingrese direccion destino:");
		String direccion = sc.next();
		System.out.print("Ingrese peso");
		int peso = sc.nextInt();
		System.out.print("Ingrese costo");
		int costo = sc.nextInt();
		Date fechaRecepcion = null;

		Paquete paq = new Paquete(identificador, fechaEntrega, fechaRecepcion, peso, direccion, costo, null);
		return paq;
	}

	public static Date entregaF() throws ParseException {
		System.out.print("Ingrese fecha de entrega");
		String fechaComoTexto = sc.next();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaEntrega = sdf.parse(fechaComoTexto);
		return fechaEntrega;
	}

	public static void Maskilos(Paquete[] paquetes) {
		System.out.println("Ingrese la cantidad de kilos");
		double kg = sc.nextDouble();
		System.out.println("Paquetes con más peso");
		for (int i = 0; i < paquetes.length; i++) {
			if (kg > paquetes[i].getPeso()) {
				System.out.println(paquetes[i].datosPaquete());
			}
		}
	}

	public static void igualCosto(Paquete[] paquetes) {
		System.out.println("Ingrese el costo:");
		double costo = sc.nextDouble();
		for (int i = 0; i < paquetes.length; i++) {
			if (costo == paquetes[i].getCosto()) {
				System.out.println(paquetes[i].datosPaquete());
			}
		}
	}

	public static int ingDni() {
		System.out.print("Ingrese dni del cliente:");
		int dni = sc.nextInt();
		return dni;
	}

	public static void ingresoPaquete(Paquete paquetes) {
		System.out.println(paquetes.datosDueño());
	}

	public static void datosxdni(int dni, Paquete[] paquetes) {
		for (int i = 0; paquetes[i]!=null&&i < paquetes.length; i++) {
			
			if (paquetes[i].datosDNI() == dni) {
				System.out.println(paquetes[i].datosPaquete());
			}
			if(paquetes[i]!=null);continue;
		}
	}
	public static void mostrar(Paquete []paquetes) {
		for(int i=0;paquetes[i]!=null;i++) {
			System.out.println(paquetes[i].toString());
		}
	}
}
