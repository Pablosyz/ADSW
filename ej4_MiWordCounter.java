package es.upm.dit.adsw.ej4;

import java.io.File;

/**
 * Pruebas de ejecución para la clase WordCounter
 * 
 * @author pablo.sanchez.yanez@alumnos.upm.es
 * 
 * @version 30.03.2016
 */
public class MiWordCounter {

	public static void main(String[] args) {
		WordCounter prueba1 = new WordCounter();
		try {
			prueba1.load(new File ("src/es/upm/dit/adsw/ej4/ElQuijote.txt"));
			System.out.println("Siendo n = 15:");
			for (Registro lista : prueba1.getTop(15)){
				System.out.println("\t" + lista.getClave() + "\t" + lista.getCnt() + " veces");
			}
			System.out.println("\nSiendo n = -15:");
			for (Registro lista : prueba1.getTop(-15)){
				System.out.println("\t" + lista.getClave() + "\t" + lista.getCnt() + " veces");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			System.out.println("\nHay " + prueba1.countBelow(1000) + " palabras repetidas menos de 1000 veces.");
			System.out.println("\nHay " + prueba1.countBelow(100) + " palabras repetidas menos de 100 veces.");
			System.out.println("\nHay " + prueba1.countBelow(10) + " palabras repetidas menos de 10 veces.");
			System.out.println("\nHay " + prueba1.countBelow(1) + " palabras repetidas menos de 1 veces.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\nEl número de palabras distintas es: " + prueba1.size());
	}
	

}
