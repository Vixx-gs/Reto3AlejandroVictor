package clases;

/**
 * Representa la relación entre un producto y un pedido.
 * Esta clase almacena los detalles de un producto específico dentro de un pedido,
 * incluyendo la cantidad de unidades, el precio unitario en ese momento, así como las
 * referencias al producto y al pedido al que pertenece.
 * 
 * @author Victor Gallego
 * @version 1
 */
public class PedidoProducto {
	private int idPedidoProducto;
	private Producto idProducto;
	private Pedido idPedido;
	private int unidades;
	private double precio;

	/**
	 * Crea una nueva instancia de {@code PedidoProducto} sin inicializar sus campos.
	 */
	public PedidoProducto() {  
		
	}

	/**
	 * Crea una nueva instancia de {@code PedidoProducto} con todos los atributos especificados.
	 *
	 * @param idPedidoProducto identificador único de esta relación pedido-producto
	 * @param idProducto       el producto incluido en el pedido
	 * @param idPedido         el pedido que contiene el producto
	 * @param unidades         la cantidad de unidades del producto en el pedido
	 * @param precio           el precio del producto en ese pedido
	 */
	public PedidoProducto(int idPedidoProducto, Producto idProducto, Pedido idPedido, int unidades, double precio) {
		super();
		this.idPedidoProducto = idPedidoProducto;
		this.idProducto = idProducto;
		this.idPedido = idPedido;
		this.unidades = unidades;
		this.precio = precio;
	}

	/**
	 * Devuelve el identificador único de la relación pedido-producto.
	 *
	 * @return el id de esta relación
	 */
	public int getIdPedidoProducto() {
		return idPedidoProducto;
	}

	/**
	 * Establece el identificador único de la relación pedido-producto.
	 *
	 * @param idPedidoProducto el nuevo id de la relación
	 */
	public void setIdPedidoProducto(int idPedidoProducto) {
		this.idPedidoProducto = idPedidoProducto;
	}

	/**
	 * Devuelve el producto asociado a esta relación.
	 *
	 * @return el producto
	 */
	public Producto getIdProducto() {
		return idProducto;
	}

	/**
	 * Establece el producto asociado a esta relación.
	 *
	 * @param idProducto el nuevo producto
	 */
	public void setIdProducto(Producto idProducto) {
		this.idProducto = idProducto;
	}

	/**
	 * Devuelve el pedido asociado a esta relación.
	 *
	 * @return el pedido
	 */
	public Pedido getIdPedido() {
		return idPedido;
	}

	/**
	 * Establece el pedido asociado a esta relación.
	 *
	 * @param idPedido el nuevo pedido
	 */
	public void setIdPedido(Pedido idPedido) {
		this.idPedido = idPedido;
	}

	/**
	 * Devuelve la cantidad de unidades del producto en el pedido.
	 *
	 * @return el número de unidades
	 */
	public int getUnidades() {
		return unidades;
	}

	/**
	 * Establece la cantidad de unidades del producto en el pedido.
	 *
	 * @param unidades el nuevo número de unidades
	 */
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	/**
	 * Devuelve el precio unitario del producto en el pedido.
	 *
	 * @return el precio del producto
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Establece el precio unitario del producto en el pedido.
	 *
	 * @param precio el nuevo precio del producto
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * Devuelve una representación en cadena del objeto {@code PedidoProducto},
	 * útil para depuración.
	 *
	 * @return cadena con los detalles del producto en el pedido
	 */
	@Override
	public String toString() {
		return "PedidoProducto [idPedidoProducto=" + idPedidoProducto + ", idProducto=" + idProducto + ", unidades="
				+ unidades + ", precio=" + precio + "]";
	}
}