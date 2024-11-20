package dbconn;
import java.util.ArrayList;
import java.util.Scanner;

import ent.Persona;

public class Main {
	public static void menu() {
		System.out.println("MENU:");
		System.out.println("1. Listado ordenado por edad.");
		System.out.println("2. Listado de los nombres y apellidos ordenados por apellido.");
		System.out.println("3. Listado de nombres y apellidos de más de 30 años.");
		System.out.println("4. Listado de los nombres que comiencen por \"J\" ordenados por apellido .");
		System.out.println("5. Edad media de la muestra.");
		System.out.println("6. Listado de los apellidos que contengan las letras \"oh\" o las letras \"ma\" (si el resultado fuera nulo, cambiar las letras)");
		System.out.println("7. Listado de las personas en la franja de edad comprendida entre los 24 y los 32 años.");
		System.out.println("8. Listado de las personas mayores de 65 años.");
		System.out.println("0. Salir");
	}
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		DatabaseDAL db = new DatabaseDAL();
		int option = 100;
		
		
		while (option != 0) {
			menu();
			option = sc.nextInt();
			switch(option) {
				case 1->{
					ArrayList<Persona> personas = db.getPersonasOrdenadasEdad();
					for(Persona persona: personas) {
						System.out.println("ID: " + persona.getId());
						System.out.println("Nombre: " + persona.getNombre());
						System.out.println("Apellidos: " + persona.getApellidos());
						System.out.println("Edad: " + persona.getFechaNac());
						System.out.println("---------------------------------------");
					}
				}
				case 2 -> {
					ArrayList<Persona> personas = db.getPersonasOrdenadasApellido();
					for(Persona persona: personas) {
						System.out.println("Nombre: " + persona.getNombre());
						System.out.println("Apellidos: " + persona.getApellidos());
						System.out.println("---------------------------------------");
					}
				}
				case 3 -> {
					ArrayList<Persona> personas = db.getPersonasMayores30();
					for(Persona persona : personas) {
						System.out.println("Nombre: " + persona.getNombre());
						System.out.println("Apellidos: " + persona.getApellidos());
						System.out.println("---------------------------------------");
					}
				}
				case 4 -> {
					ArrayList<Persona> personas = db.getListaNombresJOrdenadosPorApellido();
					for (Persona persona : personas) {
						System.out.println("Nombre: " + persona.getNombre());
						System.out.println("---------------------------------------");
					}
				}
				case 5 -> {
					double media = db.getMediaEdad();
					System.out.println("Media de la muestra: " + media);
				}
				case 6 -> {
					ArrayList<Persona> personas = db.getApellidosOhYMa();
					for (Persona persona: personas) {
						System.out.println("Nombre: " + persona.getNombre());
						System.out.println("Apellidos: " + persona.getApellidos());
						System.out.println("---------------------------------------");
					}
				}
				case 7 -> {
					ArrayList<Persona> personas = db.getEdadEntre24y32();
					for(Persona persona : personas) {
						System.out.println("ID: " + persona.getId());
						System.out.println("Nombre: " + persona.getNombre());
						System.out.println("Apellidos: " + persona.getApellidos());
						System.out.println("Edad: " + persona.getFechaNac());
						System.out.println("---------------------------------------");
					}
				}
				case 8 -> {
					ArrayList<Persona> personas = db.getMayores65();
					for(Persona persona : personas) {
						System.out.println("ID: " + persona.getId());
						System.out.println("Nombre: " + persona.getNombre());
						System.out.println("Apellidos: " + persona.getApellidos());
						System.out.println("Edad: " + persona.getFechaNac());
						System.out.println("---------------------------------------");
					}
				}
				case 0 -> {
					System.out.println("Saliendo...");
					sc.close();
				}
			}
		}
	}
}
