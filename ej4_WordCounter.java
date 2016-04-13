/**
 * 
 */
package es.upm.dit.adsw.ej4;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Analizador de textos
 * 
 * @author pablo.sanchez.yanez@alumnos.upm.es
 * 
 * @version 2016.03.21
 *
 */
public class WordCounter {
	private Map<String, Registro> dict;
	private Registro[] datos;
	private long words;
	/**
	 * Constructor.
	 */
	public WordCounter() {
		this.dict = new HashMap<String, Registro>();
	}

	/**
	 * Carga un fichero de texto
	 * 
	 * @param file
	 *            fichero.
	 * @throws IOException
	 *             si hay problemas con el fichero.
	 */
	public void load(File file) throws IOException {
		this.dict.clear();
		this.datos = null;
		this.words = 0;
		Scanner scanner = new Scanner(file, "UTF-8");
		scanner.useDelimiter("[^\\p{javaLowerCase}\\p{javaUpperCase}]+");
		while (scanner.hasNext()) {
			String word = scanner.next().toLowerCase();
			if (!this.dict.containsKey(word)){
				this.dict.put(word, new Registro(word));
			}
			else 
				this.dict.get(word).inc();
			this.words++;
		}
		scanner.close();
		System.out.println("Numero total de palabras del fichero: " + words + "\n");
	}
	
	/**
	 * Se encarga de pasar un diccionario a un array.
	 * 
	 * @param dict 
	 * @return
	 */
	private Registro[] descarga(){
		this.datos = new Registro[this.dict.size()];
		int at = 0;
		for (Registro registro : dict.values())
			this.datos[at++] = registro;
		return this.datos;
	}
	
	/**
	 * Tamaño de la tabla de contadores y del registro de palabras.
	 * 
	 * @return numero de palabras
	 */
	public int size() {
		getDatos();
		return this.datos.length;			
	}
	

	/**
	 * Descarga el diccionario en el array, atributos de la clase.
	 * @return datos
	 * 			 Atributo de tipo Array de Registro(s)
	 */
	private Registro[] getDatos() {
		if (this.datos == null) {
			descarga();
			Arrays.sort(this.datos);
		}
		return this.datos;
	}
	/**
	 * Devuelve las n palabras más usadas (si n es positivo).
	 * Devuelve las n palabras menos utilizadas (si n es negativo).
	 * 
	 * @param n
	 *            numero de palabras contando desde el principio o desde el
	 *            final.
	 * @return listaTop 
	 * 			  lista de n registros
	 */
	public List<Registro> getTop(int n) throws IllegalArgumentException {
		if (n == 0)
			throw new IllegalArgumentException("\'n\' no puede ser 0.");
		getDatos();	
		if (Math.abs(n)> this.datos.length)
			throw new IllegalArgumentException("No hay tantas palabras que se repitan.");
		List<Registro> listaTop = new ArrayList<Registro>();	
		if (n<1) {	
			System.out.println("Las n palabras menos repetidas son:");
			listaTop = Arrays.asList(this.datos);
			return listaTop.subList(0, Math.abs(n));
		}
		else	
			System.out.println("Las n palabras mas repetidas son:");
			for (int i = 0; i < n; i++){
				listaTop.add(this.datos[(this.datos.length -1) - i]);
			}
			return listaTop;
			
			 
	}
	
	/**
	 * Devuelve cuántas palabras hay por debajo del umbral c.
	 * 
	 * @param c umbral de cuenta
	 * @return número d palabras que aparecen en el texto menos de c veces.
	 */
	public int countBelow(int c) throws Exception {		
		getDatos();
		if (c > this.datos.length)
			throw new Exception("No hay palabras repetidas tantas veces");
		int palabrasDebajo = 0;			
		for (Registro reg : this.datos){
			if (reg.getCnt() <= c)
				palabrasDebajo++;
		}		
		return palabrasDebajo;

	}

}
