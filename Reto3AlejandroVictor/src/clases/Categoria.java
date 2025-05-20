package clases;

public class Categoria {

	private int idCategoria;
	private String nombre;
	
	public Categoria() {
	
		
	}
	

	public Categoria(String nombre) {
		super();
		this.nombre = nombre;
	}


	public Categoria(int idCategoria) {
		super();
		this.idCategoria = idCategoria;
	}


	public Categoria(int idCategoria, String nombre) {
		super();
		this.idCategoria = idCategoria;
		this.nombre = nombre;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + ", nombre=" + nombre + "]";
	}
	

}
