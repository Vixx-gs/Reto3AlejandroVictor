package clases;

import java.sql.Date;

/**
 * Representa un pedido realizado por un cliente.
 * Esta clase contiene información sobre un pedido, incluyendo el identificador
 * del pedido, el cliente asociado, el precio total, la dirección de envío y la fecha
 * del pedido.
 * 
 * @author Victor Gallego
 * @version 1
 */
public class Pedido {
	private int idpedido;
	private Cliente idcliente;
	private double precioTotal;
	private String direccionEnvio;
	private Date fecha;

	/**
	 * Crea un nuevo {@code Pedido} con todos sus campos inicializados.
	 *
	 * @param idpedido        identificador único del pedido
	 * @param idcliente       cliente que realizó el pedido
	 * @param precioTotal     precio total del pedido
	 * @param direccionEnvio  dirección a la que se enviará el pedido
	 * @param fecha           fecha en la que se realizó el pedido
	 */
	public Pedido(int idpedido, Cliente idcliente, double precioTotal, 
			String direccionEnvio, Date fecha) {
		super();
		this.idpedido = idpedido;
		this.idcliente = idcliente;
		this.precioTotal = precioTotal;
		this.direccionEnvio = direccionEnvio;
		this.fecha = fecha;
	}

	/**
	 * Crea un nuevo {@code Pedido} sin inicializar sus campos.
	 * Los atributos se establecerán en sus valores por defecto.
	 */
	public Pedido() {
		super();
	}

	/**
	 * Devuelve el identificador único del pedido.
	 *
	 * @return el id del pedido
	 */
	public int getIdpedido() {
		return idpedido;
	}

	/**
	 * Establece el identificador único del pedido.
	 *
	 * @param idpedido el nuevo id del pedido
	 */
	public void setIdpedido(int idpedido) {
		this.idpedido = idpedido;
	}

	/**
	 * Devuelve el cliente que realizó el pedido.
	 *
	 * @return el cliente asociado al pedido
	 */
	public Cliente getIdcliente() {
		return idcliente;
	}

	/**
	 * Establece el cliente que realizó el pedido.
	 *
	 * @param idcliente el nuevo cliente del pedido
	 */
	public void setIdcliente(Cliente idcliente) {
		this.idcliente = idcliente;
	}

	/**
	 * Devuelve el precio total del pedido.
	 *
	 * @return el precio total
	 */
	public double getPrecioTotal() {
		return precioTotal;
	}

	/**
	 * Establece el precio total del pedido.
	 *
	 * @param precioTotal el nuevo precio total
	 */
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	/**
	 * Devuelve la dirección de envío del pedido.
	 *
	 * @return la dirección de envío
	 */
	public String getDireccionEnvio() {
		return direccionEnvio;
	}

	/**
	 * Establece la dirección de envío del pedido.
	 *
	 * @param direccionEnvio la nueva dirección de envío
	 */
	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}

	/**
	 * Devuelve la fecha en que se realizó el pedido.
	 *
	 * @return la fecha del pedido
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Establece la fecha en que se realizó el pedido.
	 *
	 * @param fecha la nueva fecha del pedido
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Devuelve una representación en cadena del objeto, útil para depuración.
	 *
	 * @return cadena con los valores clave del pedido
	 */
	@Override
	public String toString() {
		return "Pedido [idpedido=" + idpedido + ", precioTotal=" + precioTotal + ", direccionEnvio=" + direccionEnvio
				+ ", fecha=" + fecha + "]";
	}
}