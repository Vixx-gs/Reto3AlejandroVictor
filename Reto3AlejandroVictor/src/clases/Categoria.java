package clases;

/**
 * Representa una categoría de productos u objetos dentro del sistema.
 * Esta clase contiene el identificador único y el nombre de una categoría.
 * Puede ser utilizada para clasificar o agrupar elementos en distintas áreas de la aplicación.
 * 
 * @author Victor Gallego
 * @version 1
 */
public class Categoria {

	private int idCategoria;
	private String nombre;

	/**
	 * Crea una nueva instancia de {@code Categoria} sin inicializar sus campos.
	 */
	public Categoria() {
		
	}

	/**
	 * Crea una nueva instancia de {@code Categoria} con el nombre especificado.
	 *
	 * @param nombre el nombre de la categoría
	 */
	public Categoria(String nombre) {
		super();
		this.nombre = nombre;
	}

	/**
	 * Crea una nueva instancia de {@code Categoria} con el identificador especificado.
	 *
	 * @param idCategoria el identificador único de la categoría
	 */
	public Categoria(int idCategoria) {
		super();
		this.idCategoria = idCategoria;
	}

	/**
	 * Crea una nueva instancia de {@code Categoria} con todos sus campos inicializados.
	 *
	 * @param idCategoria el identificador único de la categoría
	 * @param nombre      el nombre de la categoría
	 */
	public Categoria(int idCategoria, String nombre) {
		super();
		this.idCategoria = idCategoria;
		this.nombre = nombre;
	}

	/**
	 * Devuelve el identificador único de la categoría.
	 *
	 * @return el id de la categoría
	 */
	public int getIdCategoria() {
		return idCategoria;
	}

	/**
	 * Establece el identificador único de la categoría.
	 *
	 * @param idCategoria el nuevo id de la categoría
	 */
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * Devuelve el nombre de la categoría.
	 *
	 * @return el nombre de la categoría
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre de la categoría.
	 *
	 * @param nombre el nuevo nombre de la categoría
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve una representación en cadena del objeto {@code Categoria}.
	 *
	 * @return cadena con el id y el nombre de la categoría
	 */
	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + ", nombre=" + nombre + "]";
	}
}
