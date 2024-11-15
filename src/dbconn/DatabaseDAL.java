package dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ent.Persona;

public class DatabaseDAL {
	private Connection conn = null;
	
	public void connect() {
		String connURL = "jdbc:mysql://dns11036.phdns11.es:3306/ad2425_afernandez";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.conn = DriverManager.getConnection(connURL, "afernandez", "12345");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * Recoge de la base de datos las personas ordenadas por su edad y las devuelve en una lista
	 * @return ArrayList de personas ordenadas por su edad
	 */
	public ArrayList<Persona> getPersonasOrdenadasEdad() {
		ArrayList<Persona> listaPersonas = new ArrayList<Persona>();

		try {
			PreparedStatement listaEdad = this.conn.prepareStatement("SELECT * FROM ejercicio ORDER BY edad");
			ResultSet lista = listaEdad.executeQuery();
			while (lista.next()) {
				listaPersonas.add(new Persona(lista.getInt("id"), lista.getString("nombre"), lista.getString("apellidos"), lista.getDate("edad")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPersonas;
	}
	
	/***
	 * Recoge de la bd una lista de personas con su nombre y apellido, ordenadas por el apellido
	 * @return ArrayList de personas
	 */
	public ArrayList<Persona> getPersonasOrdenadasApellido() {
		ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
		try {
			PreparedStatement listaBD = this.conn.prepareStatement("SELECT nombre, apellidos FROM ejercicio ORDER BY apellidos");
			ResultSet listaResultados = listaBD.executeQuery();
			while(listaResultados.next()) {
				listaPersonas.add(new Persona(listaResultados.getString("nombre"), listaResultados.getString("Apellidos")));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return listaPersonas;
	}
	
	public DatabaseDAL() {
		connect();
	}
}
