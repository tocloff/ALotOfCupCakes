/**
 * MA-ITG ALotOfCupcakes Web ApplicationException
 * Author: Thomas Enrique Mejia Anaya
 */
package modelado;


import java.util.HashMap;

import com.sun.istack.internal.Nullable;

public class Compra {

	private int id;
	private int id_propietario;
	private String comentario;
	private HashMap<Integer, LineaCompra> lineasCompra;
	
	public Compra(int id, int id_propietario, @Nullable String comentario) {
		super();
		this.id = id;
		this.id_propietario = id_propietario;
		this.comentario = comentario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_propietario() {
		return id_propietario;
	}

	public void setId_propietario(int id_propietario) {
		this.id_propietario = id_propietario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public HashMap<Integer, LineaCompra> getLineasCompra() {
		return lineasCompra;
	}

	public void setLineasCompra(HashMap<Integer, LineaCompra> lineasCompra) {
		this.lineasCompra = lineasCompra;
	}
	
}
