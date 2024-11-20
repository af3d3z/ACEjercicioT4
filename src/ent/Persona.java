package ent;

import java.util.Date;

import java.lang.reflect.Field;

public class Persona {
	/***
	 * Atributos de clase
	 */
	/***
	 * Id de la persona
	 */
	private int id;
	/***
	 * Nombre de la persona
	 */
	private String nombre;
	/***
	 * Apellidos de la persona
	 */
	private String apellidos;
	/***
	 * Fecha de nacimiento de la persona
	 */
	private Date fechaNac;
	
	/**
	 * Getters y setters
	 */
	public int getId() {
		return this.id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFechaNac() {
		return fechaNac;
	}
	
	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	
	/***
	 * Constructor base
	 */
	public Persona() {}
	
	public Persona(String nombre) {
		this.nombre = nombre;
	}
	
	/***
	 * Constructor con nombre y apellidos
	 * @param nombre de la persona
	 * @param apellidos de la persona
	 */
	public Persona(String nombre, String apellidos) {
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	
	
	/***
	 * Constructor con todos los parámetros
	 * @param id de la persona
	 * @param nombre id de la persona
	 * @param apellidos id de la persona 
	 * @param fechaNac id de la persona
	 */
	public Persona(int id, String nombre, String apellidos, Date fechaNac) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNac = fechaNac;
	}

}
