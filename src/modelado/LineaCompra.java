package modelado;

public class LineaCompra {

	private int producto;
	private int cantidad;
	
	public LineaCompra(int producto, int cantidad) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public int getProducto() {
		return producto;
	}

	public void setProducto(int producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
