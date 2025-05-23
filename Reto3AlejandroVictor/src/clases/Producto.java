package clases;
/**
 * Representa un producto disponible en el catálogo.
 * Contiene información como nombre, precio, color, talla, descripción, stock y categoría.
 * 
 * @author Victor Gallego 
 */
public class Producto {


	private int idProducto;
	private Categoria idCategoria;
	private String nombre;
	private double precio;
	private String descripcion;
	private String color;
	private String talla;
	private int stock;
	
	public Producto () {
		
	}
	

	public Producto(Categoria idCategoria, String nombre, double precio, String descripcion, String color, String talla,
			int stock) {
		super();
		this.idCategoria = idCategoria;
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.color = color;
		this.talla = talla;
		this.stock = stock;
	}
	


	public Producto(String nombre, int stock) {
		super();
		this.nombre = nombre;
		
		this.stock = stock;
	}


	
	public Producto(String nombre, double precio, String descripcion, String color, String talla, int stock) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.color = color;
		this.talla = talla;
		this.stock = stock;
	}


	public Producto(int idProducto, Categoria idCategoria, String nombre, double precio, String descripcion,
			String color, String talla, int stock) {
		super();
		this.idProducto = idProducto;
		this.idCategoria = idCategoria;
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.color = color;
		this.talla = talla;
		this.stock = stock;
	}
	/**
     * Obtiene el identificador único del producto.
     * 
     * @return el ID del producto
     */
	public int getIdProducto() {
		return idProducto;
	}
	/**
     * Establece el identificador único del producto.
     * 
     * @param idProducto el ID a establecer
     */
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	/**
     * Obtiene el identificador único de la categoria.
     * 
     * @return el ID de la categoria
     */
	public Categoria getIdCategoria() {
		return idCategoria;
	}
	/**
     * Establece el identificador único de la categoria.
     * 
     * @param IdCategoria el ID a establecer
     */
	public void setIdCategoria(Categoria idCategoria) {
		this.idCategoria = idCategoria;
	}
	/**
     * Obtiene el identificador único del nombre.
     * 
     * @return el nombre del nombre
     */
	public String getNombre() {
		return nombre;
	}
	/**
     * Establece el identificador único del nombre.
     * 
     * @param nombre el nombre a establecer
     */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
     * Obtiene el identificador único del precio.
     * 
     * @return el precio del precio
     */
	public double getPrecio() {
		return precio;
	}
	/**
     * Establece el identificador único del precio.
     * 
     * @param Precio el precio a establecer
     */
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	/**
     * Obtiene el identificador único de la descripcion.
     * 
     * @return la descripcion de la descripcion
     */
	public String getDescripcion() {
		return descripcion;
	}
	/**
     * Establece el identificador único de la descripcion.
     * 
     * @param Descripcion la descripcion a establecer
     */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
     * Obtiene el identificador único del color.
     * 
     * @return el color del color
     */
	public String getColor() {
		return color;
	}
	/**
     * Establece el identificador único del color.
     * 
     * @param Color el color a establecer
     */
	public void setColor(String color) {
		this.color = color;
	}
	/**
     * Obtiene el identificador único de la talla.
     * 
     * @return la talla de la talla
     */
	public String getTalla() {
		return talla;
	}
	/**
     * Establece el identificador único de la talla.
     * 
     * @param Talla la talla a establecer
     */
	public void setTalla(String talla) {
		this.talla = talla;
	}
	/**
     * Obtiene el identificador único del stock.
     * 
     * @return el stock del stock
     */
	public int getStock() {
		return stock;
	}
	/**
     * Establece el identificador único del stock.
     * 
     * @param stock el stock a establecer
     */
	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", idCategoria=" + idCategoria + ", nombre=" + nombre
				+ ", precio=" + precio + ", descripcion=" + descripcion + ", color=" + color + ", talla=" + talla
				+ ", stock=" + stock + "]";
	}
	

}

 