package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Util.Funciones;
import clases.Categoria;
import clases.Cliente;
import clases.Pedido;
import clases.PedidoProducto;
import clases.Producto;
import dao.CategoriaDAO;
import dao.ClientesDAO;
import dao.PedidoDAO;
import dao.PedidoProductoDAO;
import dao.ProductosDAO;
/**
 * Clase principal del sistema de gestión comercial.
 * Esta clase contiene el método `main` que lanza el menú de operaciones 
 * para gestionar categorías, productos, clientes, pedidos e informes.
 * 
 * Funcionalidades principales:
 * - Mantenimiento de datos (categorías, productos, clientes)
 * - Visualización de catálogo de productos
 * - Gestión de pedidos
 * - Generación de informes
 * 
 * Uso extensivo de clases DAO para persistencia de datos y 
 * clases de utilidad para entrada de datos desde consola.
 * 
 * @author Victor Gallego	 
 */
public class Main1 {
	 /**
     * Método principal que lanza el menú interactivo del sistema.
     * Permite seleccionar entre mantenimientos, catálogo de productos, pedidos e informes.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     * @throws SQLException si ocurre un error con la base de datos
     */
	public static void main(String[] args) throws SQLException {
		/**
	     * Actualiza el stock de un producto con bajo nivel de inventario.
	     * 
	     * @param sc Scanner para entrada del usuario
	     */
		Scanner sc = new Scanner(System.in);
		Random r = new Random();
		Producto producto = new Producto();

		int opcion = -1;

		// Bucle principal del menú

		do {
			try {
				opcion = Funciones.dimeEntero("Introduzca una de las siguientes opciones:\n" + "1. Mantenimientos\n"
						+ "2. Catálogo productos\n" + "3. Pedidos\n" + "4. Informes\n" + "0. Salir", sc);

				switch (opcion) {
				case 1:
					int subOpcion1 = 0;
					do {
						try {
							subOpcion1 = Funciones.dimeEntero("1. Gestión de categorías\n" + "2. Gestión de productos\n"
									+ "3. Gestión de clientes\n" + "0. Volver", sc);
							switch (subOpcion1) {
							case 1:
								gestionCategorias();
								break;
							case 2:
								gestionProductos();
								break;
							case 3:
								int subOpcionClientes = 0;
								do {
									try {
										subOpcionClientes = Funciones.dimeEntero("1. Alta de nuevos clientes\n"
												+ "2. Búsqueda por código\n" + "0. Volver", sc);
										switch (subOpcionClientes) {
										case 1:
											gestionClientes();
											break;
										case 2:
											vercode(sc);
											break;
										case 0:
											break;
										default:
											System.out.println("Opción inválida");
										}
									} catch (Exception e) {
										System.out.println("Formato inválido");
										sc.nextLine();
									}
								} while (subOpcionClientes != 0);
								break;
							case 0:
								break;
							default:
								System.out.println("Opción inválida");
							}
						} catch (Exception e) {
							System.out.println("Formato inválido");
							sc.nextLine();
						}
					} while (subOpcion1 != 0);
					break;

				case 2:
					int subOpcion2 = 0;
					do {
						try {
							subOpcion2 = Funciones.dimeEntero(
									"1. Listar productos por categoría\n" + "2. Buscar productos\n" + "0. Volver", sc);
							switch (subOpcion2) {
							case 1:
								mostrarCat(0, sc);
								break;
							case 2:
								buscarProd(sc);
								break;
							case 0:
								break;
							default:
								System.out.println("Opción inválida");
							}
						} catch (Exception e) {
							System.out.println("Formato inválido");
							sc.nextLine();
						}
					} while (subOpcion2 != 0);
					break;

				case 3:
					int subOpcion3 = 0;
					do {
						try {
							subOpcion3 = Funciones.dimeEntero("1. Crear pedido\n" + "2. Ver pedidos\n" + "0. Volver",
									sc);
							switch (subOpcion3) {
							case 1:
								guardarPedido(null, null); 
								break;
							case 2:
								// verPedido(pedido);
								break;
							case 0:
								break;
							default:
								System.out.println("Opción inválida");
							}
						} catch (Exception e) {
							System.out.println("Formato inválido");
							sc.nextLine();
						}
					} while (subOpcion3 != 0);
					break;

				case 4:
					int subOpcion4 = 0;
					do {
						try {
							subOpcion4 = Funciones.dimeEntero("1. Bajo stock\n" + "2. Pedidos por cliente\n"
									+ "3. Productos más vendidos\n" + "0. Volver", sc);
							switch (subOpcion4) {
							case 1:
								actualizarStock(sc);
								break;
							case 2:
								break;
							case 3:
								break;
							case 0:
								break;
							default:
								System.out.println("Opción inválida");
							}
						} catch (Exception e) {
							System.out.println("Formato inválido");
							sc.nextLine();
						}
					} while (subOpcion4 != 0);
					break;

				case 0:
					System.out.println("Saliendo del sistema.");
					break;

				default:
					System.out.println("Opción inválida");
				}
			} catch (Exception e) {
				System.out.println("Formato inválido");
				sc.nextLine();
			}
		} while (opcion != 0);
	}
	/**
     * Inserta una nueva categoría en la base de datos.
     */
	public static void actualizarStock(Scanner sc) {
		String nombre = "";
		int num = 0;
		System.out.println("Los productos de bajo stock son ");
		List<Producto> listaBajoStock = ProductosDAO.bajoStock();
		for (Producto producto : listaBajoStock) {
			System.out.println(producto);
		}
		nombre = Funciones.dimeString("Seleccione un producto de los mostrados para actualizar", sc);
		do {
			num = Funciones.dimeEntero("Diga en cuanto quiere actualizarlo", sc);
			if (num > 0)
				break;
		} while (true);
		List<Producto> listaDeActualizarProductos=ProductosDAO.actualizarStock(nombre, num);
		
		for (Producto producto : listaDeActualizarProductos) {
			System.out.println(producto);
			
		}
		
		System.out.println("Productos actualizados correctamente");
		
	}
	/**
     * Inserta una nueva categoría en la base de datos.
     */
	public static void gestionCategorias() {
		Categoria categoria = new Categoria();
		Scanner sc = new Scanner(System.in);
		String nombre = Funciones.dimeString("Nuevo Nombre", sc);
		categoria.setNombre(nombre);
		CategoriaDAO.inserta(categoria);
	}
	
	/**
     * Inserta un nuevo cliente en el sistema, pidiendo información al usuario.
     */
	public static void gestionClientes() {
		Cliente cliente = new Cliente();
		Scanner sc = new Scanner(System.in);
		System.out.println("/nDatos actuales del cliente");
		System.out.println("Nombre: " + cliente.getNombre());
		System.out.println("Direccion: " + cliente.getDireccion());
		System.out.println("Codigo: " + cliente.getCodigo());

		System.out.println("/nIntroduce los nuevos datos");
		String nombre = Funciones.dimeString("Nuevo Nombre", sc);
		cliente.setNombre(nombre);

		String direccion = Funciones.dimeString("Nueva Direccion", sc);
		cliente.setDireccion(direccion);

		int codigo = Funciones.dimeEntero("Nuevo Codigo", sc);
		cliente.setCodigo(codigo);

	}
	 /**
     * Inserta un nuevo producto en el sistema, pidiendo información al usuario.
     */
	public static void gestionProductos() {
		Scanner sc = new Scanner(System.in);
		int categoriaElegida = 0;
		Producto producto = new Producto();

		String sNombre = Funciones.dimeString("Introduzca un nombre de un producto", sc);
		producto.setNombre(sNombre);
		Double dPrecio = Funciones.dimeDouble("Introduzca el precio", sc);
		producto.setPrecio(dPrecio);
		String sColor = Funciones.dimeString("Introduzca el color", sc);
		producto.setColor(sColor);
		String sTalla = Funciones.dimeString("Introduzca la talla", sc);
		producto.setTalla(sTalla);
		String sDescripcion = Funciones.dimeString("Introduzca una descripcion", sc);
		producto.setDescripcion(sDescripcion);
		int iStock = Funciones.dimeEntero("introduzca un stock", sc);
		producto.setStock(iStock);

		do {
			try {
				System.out.println("A continuacion mostraremos las categorias " + "existentes ");
				for (Categoria cat : CategoriaDAO.listaIdCat()) {
					System.out.println(cat.getIdCategoria());
				}
				// Elije una categoria existente
				categoriaElegida = Funciones.dimeEntero("Introduzca una categoria" + " existente", sc);

				// Comprobamos si existe la categoria, si existe salimos del bucle
				if (!(CategoriaDAO.listaIdCat().contains(categoriaElegida))) {
					break;

				}

			} catch (Exception e) {

			}
		} while (true);

		Categoria cat = new Categoria(categoriaElegida);
		producto.setIdCategoria(cat);
		ProductosDAO.insertaProducto(producto);

	}
	/**
     * Permite buscar un cliente por código e imprimir su información.
     * 
     * @param sc Scanner para entrada del usuario
     */
	public static void vercode(Scanner sc) {
		int code = Funciones.dimeEntero("Introduce codigo", sc);
		ClientesDAO.buscarCliente(code);
	}
	 /**
     * Muestra las categorías disponibles y lista los productos de la seleccionada.
     * 
     * @param num Número de categoría (usado internamente)
     * @param sc Scanner para entrada del usuario
     */
	public static void mostrarCat(int num, Scanner sc) {
		System.out.println("A continuacion se mostraran las categorias");
		System.out.println(CategoriaDAO.listarCategorias());
		num = Funciones.dimeEntero("Seleccione una de las siguientes categorias", sc);

		List<Producto> listaProductos = ProductosDAO.listaProductosPorCategoria(num);
		for (Producto p : listaProductos) {
			System.out.println(p);
		}
	}
	/**
     * Busca productos por nombre, talla y color ingresados por el usuario.
     * 
     * @param sc Scanner para entrada del usuario
     * @throws SQLException si ocurre un error en la consulta
     */
	public static void buscarProd(Scanner sc) throws SQLException {
		System.out.println("Introduce nombre");
		String nombre = sc.next();
		System.out.println("Introduce talla");
		String talla = sc.next();
		System.out.println("Introduce color");
		String color = sc.next();
		List<Producto> productos = ProductosDAO.buscarProductos(nombre, talla, color);
		for (Producto p : productos) {
			System.out.println("nombre" + p.getNombre());
			System.out.println("talla" + p.getTalla());
			System.out.println("color" + p.getColor());
		}
	}
	  /**
     * Permite al usuario ingresar un código de cliente hasta que sea válido o -1 para cancelar.
     * 
     * @param sc Scanner para entrada del usuario
     * @return Cliente encontrado o null si se cancela
     * @throws SQLException si ocurre un error de base de datos
     */
	public static Cliente obtenerCliente(Scanner sc) throws SQLException {
		do {
			int codigo = Funciones.dimeEntero("Introduce un codigo hasta que sea -1 para salir", sc);
			if (codigo == -1) {
				return null;
			}
			Cliente cliente = ClientesDAO.obtenerporCodigo(codigo);
			if (cliente != null) {
				System.out.println("Cliente encontrado" + cliente.getNombre());
				return cliente;
			} else {
				System.out.println("Cliente no encontrado");
			}

		} while (true);
	}
	  /**
     * Permite seleccionar productos por nombre e ingresar cantidades deseadas.
     * 
     * @param sc Scanner para entrada del usuario
     * @return Lista de productos seleccionados con cantidades
     * @throws SQLException si ocurre un error al obtener productos
     */
	public static List<Producto> seleccionaProductos(Scanner sc) throws SQLException {
		Producto producto = new Producto();
		List<Producto> prodseleccionados = new ArrayList<>();
		do {
			String nombre = Funciones.dimeString("Introduce nombre del producto (Dejar vacio para terminar)", sc);
			if (nombre.isEmpty()) {
				break;
			}
			producto = ProductosDAO.obtenerporNombre(nombre);
			if (producto == null) {
				System.out.println("Producto no encontrado");
			}
			int cantidad = Funciones.dimeEntero("Cuantas unidades?", sc);
			int cantidadDisponible = Math.min(cantidad, producto.getStock());

			if (cantidadDisponible == 0) {
				System.out.println("No hay stock de esta unidad");
			} else {
				System.out.println("Añadida " + cantidadDisponible + " unidades de " + producto.getNombre());
				producto.setStock(cantidadDisponible);
				prodseleccionados.add(producto);
			}
		} while (!true);

		return prodseleccionados;
	}

    /**
     * Calcula el precio total de los productos seleccionados.
     * 
     * @param producto Lista de productos seleccionados
     * @return Precio total
     */
	public static double calcularPrecioTotal(List<Producto> producto) {
		double total = 0;
		for (Producto p : producto) {
			total += p.getPrecio() * p.getStock();
		}
		return total;
	}
	/**
     * Obtiene la dirección de envío para el pedido, permite usar la dirección del cliente o ingresar una nueva.
     * 
     * @param sc Scanner para entrada del usuario
     * @param cliente Cliente actual
     * @return Dirección de envío seleccionada
     */
	public static String obtenerDireccionEnv(Scanner sc, Cliente cliente) {
		System.out.println("Direccion actual " + cliente.getDireccion());
		System.out.println("Usar esta direccion?(s/n)");
		String respuesta = sc.nextLine();

		if (respuesta.equalsIgnoreCase("s")) {
			return cliente.getDireccion();
		} else {
			System.out.println("Introduce nueva direccion de envio");
			return sc.nextLine();
		}
	}
	 /**
     * Guarda un pedido en la base de datos y asocia los productos seleccionados.
     * Reduce el stock de los productos según las cantidades seleccionadas.
     * 
     * @param pedido Objeto Pedido a guardar
     * @param productos Lista de productos incluidos en el pedido
     */
	public static void guardarPedido(Pedido pedido, List<Producto>productos) {
		PedidoDAO.inserta(pedido);
		for(Producto p : productos) {
			PedidoProductoDAO.inserta(pedido.getIdpedido(), p, p.getStock());
			ProductosDAO.reducirStock(p.getIdProducto(), p.getStock());
		}
	}
}