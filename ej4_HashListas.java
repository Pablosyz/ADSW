package es.upm.dit.adsw.ej4;

import java.util.ArrayList;
import java.util.List;


/**
 * @author pablo.sanchez.yanez@alumnos.upm.es
 * 			basado en transparencias del Moodle de la asigantura
 * @version 2016.03.05
 *
 */
public class HashListas {
	private final List<Registro>[] slots;
	private int nDatos = 0;
	
	
	/**
	 * Constructor
	 * @param nSlots numero de ranuras (slots).
	 */
	@SuppressWarnings("unchecked")
	public HashListas(int nSlots){
		//noInspection unchecked
		this.slots = new List[nSlots];
		for (int i = 0; i < nSlots; i++) {
			this.slots[i] = new ArrayList<Registro>(0);
		}
	}
	public void put(String clave) {
		if (clave == null || clave.isEmpty())
			throw new IllegalArgumentException("put(null, valor)");
		int h = Math.abs(clave.hashCode() % this.slots.length);		
		put(this.slots[h], clave);
		
	}

	private void put(List<Registro> list, String clave) {
		for (Registro reg : list) {
			if (OpMeter.compareTo(reg.getClave(), clave) == 0) {
				reg.inc();
				return;
			}
		}
		list.add(new Registro(clave));
		nDatos++;
		
	}
//	public String get(String clave) {
//		if (clave == null || clave.isEmpty())
//			throw new IllegalArgumentException("get(null)");
//		int h = Math.abs(clave.hashCode() % this.slots.length);
//		return get(this.slots[h], clave);
//	}
//
//	private String get(List<Registro> list, String clave) {
//		for (Registro reg : list) {
//			if (OpMeter.compareTo(reg.getClave(), clave) == 0)
//				return reg.;		
//		}
//		return null;
//	}
	
	public void remove(String clave) {
		if (clave == null || clave.isEmpty())
			throw new IllegalArgumentException("remove(null)");
		int h = Math.abs(clave.hashCode() % this.slots.length);
		remove(this.slots[h], clave);
	}

	private void remove(List<Registro> list, String clave) {
		Registro found = null;
		for (Registro reg : list) {
			if (OpMeter.compareTo(reg.getClave(), clave) == 0) {
				found = reg;
				break;
			}
		}
		if (found == null)
			return;
		list.remove(found);
		nDatos--;
		return;
		}
	
	public int size() {
		return this.nDatos;
	}

	public void clear() {
		for (List<Registro> slot : this.slots)
			slot.clear();
		nDatos = 0;
	}

}
