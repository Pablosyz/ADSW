package es.upm.dit.adsw.ej4;

/**
 * Contenedor para asociar un contador a una palabra.
 *
 * @author jose a. manas
 * @version 8.3.2016
 */
public class Registro implements Comparable<Registro> {
    private final String clave;
    private int cnt;

    /**
     * Constructor.
     * El contador de inicaliza a 1.
     *
     * @param clave palabra.
     */
    public Registro(String clave) {
        this.clave = clave;
        this.cnt = 1;
    }

    /**
     * Getter.
     *
     * @return palabra.
     */
    public String getClave() {
        return clave;
    }

    /**
     * Getter.
     *
     * @return contador.
     */
    public int getCnt() {
        return cnt;
    }

    /**
     * Incrementa el contador.
     */
    public void inc() {
        this.cnt++;
    }

	@Override
	public int compareTo(Registro o) {
		return cnt - o.cnt;
	}
}
