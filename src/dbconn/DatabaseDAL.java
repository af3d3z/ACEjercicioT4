package dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	/***
	 * Recoge de la bd una lista de personas mayores de 30 años con su nombre y apellidos
	 * @return
	 */
	public ArrayList<Persona> getPersonasMayores30() {
		ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
		try {
			PreparedStatement listaMayores30 = this.conn.prepareStatement("SELECT nombre, apellidos FROM ejercicio WHERE edad >= 30");
			ResultSet listaResultados = listaMayores30.executeQuery();		
			while(listaResultados.next()) {
				listaPersonas.add(new Persona(listaResultados.getString("nombre"), listaResultados.getString("apellidos")));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return listaPersonas;
	}
	
	/***
	 * Recoge de la base de datos una lista de las personas cuyo nombre empiece por J y está ordenado por el apellido
	 * @return
	 */
	public ArrayList<Persona> getListaNombresJOrdenadosPorApellido(){
		ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
		try {
			PreparedStatement listaNombresJOrdenadosApellido = this.conn.prepareStatement("SELECT nombre FROM ejercicio WHERE nombre LIKE 'J%' ORDER BY apellidos");
			ResultSet listaResultados = listaNombresJOrdenadosApellido.executeQuery();
			while(listaResultados.next()) {
				listaPersonas.add(new Persona(listaResultados.getString("nombre")));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return listaPersonas;
	}
	
	/***
	 * Calcula la media de todas las personas en la bd
	 * @return
	 */
	public double getMediaEdad() {
		double edadMedia = 0;
		try {
			PreparedStatement mediaEdad = this.conn.prepareStatement("SELECT AVG(YEAR(CURRENT_DATE()) - YEAR(edad)) as media FROM ejercicio");
			ResultSet resultado = mediaEdad.executeQuery();
	        if (resultado.next()) {
	            edadMedia = resultado.getDouble("media");
	        }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return edadMedia;
	}
	
	/***
	 * Devuelve una lista de las personas cuyos apellidos contengan "oh" o si no se encuentra ninguno "ma"
	 * @return
	 */
	public ArrayList<Persona> getApellidosOhYMa(){
		ArrayList<Persona> listaPersona = new ArrayList<Persona>();
		try {
			PreparedStatement apellidosLetras = this.conn.prepareStatement("SELECT nombre, apellidos FROM ejercicio WHERE apellidos LIKE '%oh%'");
			ResultSet resultado = apellidosLetras.executeQuery();
			while(resultado.next()) {
				String nombre = resultado.getString("nombre");
				String apellidos = resultado.getString("apellidos");
			
				if (resultado.wasNull()) {
					apellidosLetras = this.conn.prepareStatement("SELECT apellidos FROM ejercicio WHERE apellidos LIKE '%ma%'");
					resultado = apellidosLetras.executeQuery();
					while(resultado.next()) {
						listaPersona.add(new Persona(resultado.getString("nombre"), resultado.getString("apellidos")));
					}
				}else {
					listaPersona.add(new Persona(nombre, apellidos));
				}
					
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return listaPersona;
	}
	
	/***
	 * Devuelve una lista de personas cuya edad está comprendida entre 24 y 32 años
	 * @return
	 */
	public ArrayList<Persona> getEdadEntre24y32(){
		ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
		try {
			PreparedStatement edadesComprendidas = this.conn.prepareStatement("SELECT * FROM ejercicio WHERE YEAR(CURRENT_DATE()) - YEAR(edad) >= 24 AND YEAR(CURRENT_DATE()) - YEAR(edad) <= 32 ");
			ResultSet resultado = edadesComprendidas.executeQuery();
			while(resultado.next()) {
				listaPersonas.add(new Persona(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("apellidos"), resultado.getDate("edad")));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listaPersonas;
	}
	
	/***
	 * Devuelve una lista de personas mayores de 65 años
	 * @return
	 */
	public ArrayList<Persona> getMayores65(){
		ArrayList<Persona> personas = new ArrayList<Persona>();
		try {
			PreparedStatement mayores = this.conn.prepareStatement("SELECT * FROM ejercicio WHERE YEAR(CURRENT_DATE()) - YEAR(edad) > 65 ");
			ResultSet resultado = mayores.executeQuery();
			while(resultado.next()) {
				personas.add(new Persona(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("apellidos"), resultado.getDate("edad")));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return personas;
	}
	
	
	public DatabaseDAL() {
		connect();
	}
}
