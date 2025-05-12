package Clases;

public class PedidoProducto {
	private int idPedidoProducto;
	private Producto idProducto;
	private Pedido idPedido;
	private int unidades;
	private double precio;
	
	public PedidoProducto() {  
		
	}

	public PedidoProducto(int idPedidoProducto, Producto idProducto, Pedido idPedido, int unidades, double precio) {
		super();
		this.idPedidoProducto = idPedidoProducto;
		this.idProducto = idProducto;
		this.idPedido = idPedido;
		this.unidades = unidades;
		this.precio = precio;
	}

	public int getIdPedidoProducto() {
		return idPedidoProducto;
	}

	public void setIdPedidoProducto(int idPedidoProducto) {
		this.idPedidoProducto = idPedidoProducto;
	}

	public Producto getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Producto idProducto) {
		this.idProducto = idProducto;
	}

	public Pedido getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Pedido idPedido) {
		this.idPedido = idPedido;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "PedidoProducto [idPedidoProducto=" + idPedidoProducto + ", idProducto=" + idProducto + ", unidades="
				+ unidades + ", precio=" + precio + "]";
	}
	
	

}
