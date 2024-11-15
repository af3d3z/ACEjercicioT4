package dbconn;
import java.util.ArrayList;
import java.util.Scanner;

import ent.Persona;

public class Main {
	public static void menu() {
		System.out.println("MENU:");
		System.out.println("1. Listado ordenado por edad.");
		System.out.println("2. Listado de los nombres y apellidos ordenados por apellido.");
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
					System.out.println("Lista de personas ordenadas por edad: ");
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
					System.out.println("Lista de personas ordenadas por su apellido:");
					for(Persona persona: personas) {
						System.out.println("Nombre: " + persona.getNombre());
						System.out.println("Apellidos: " + persona.getApellidos());
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
