package clases;

/**
 * Representa un cliente de la aplicación.  
 * Cada instancia de esta clase almacena la información básica de un cliente:
 * un identificador único, el nombre, la dirección y un código auxiliar
 * (por ejemplo, un código postal o un código interno de la empresa).
 * 
 * @author Tu Nombre
 * @version 1.0
 */
public class Cliente {
	private int idCliente;
	private String nombre;
	private String direccion;
	private int codigo;
	
	/**
	 * Crea un nuevo {@code Cliente} sin inicializar sus campos.
	 * Los atributos se establecerán en sus valores por defecto
	 * (cero para tipos primitivos y {@code null} para objetos).
	 */
	public Cliente() {
		
	}

	/**
	 * Crea un nuevo {@code Cliente} con los datos especificados.
	 *
	 * @param idCliente identificador único del cliente
	 * @param nombre    nombre completo del cliente
	 * @param direccion dirección física del cliente
	 * @param codigo    código auxiliar (p. ej. código postal o interno)
	 */
	public Cliente(int idCliente, String nombre, String direccion, int codigo) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.direccion = direccion;
		this.codigo = codigo;
	}

	/**
	 * Devuelve el identificador único del cliente.
	 *
	 * @return el id del cliente
	 */
	public int getIdCliente() {
		return idCliente;
	}

	/**
	 * Establece el identificador único del cliente.
	 *
	 * @param idCliente el nuevo id del cliente
	 */
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * Devuelve el nombre completo del cliente.
	 *
	 * @return el nombre del cliente
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre completo del cliente.
	 *
	 * @param nombre el nuevo nombre del cliente
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve la dirección física del cliente.
	 *
	 * @return la dirección del cliente
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Establece la dirección física del cliente.
	 *
	 * @param direccion la nueva dirección del cliente
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Devuelve el código auxiliar del cliente.
	 *
	 * @return el código del cliente
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Establece el código auxiliar del cliente.
	 *
	 * @param codigo el nuevo código del cliente
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Devuelve una representación en cadena del objeto, útil para depuración.
	 *
	 * @return cadena con los valores de todos los campos del cliente
	 */
	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nombre=" + nombre + ", direccion=" + direccion + ", codigo="
				+ codigo + "]";
	}
}