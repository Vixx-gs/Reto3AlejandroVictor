package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Util.Funciones;
import clases.Categoria;
import clases.Cliente;
import clases.Producto;
import dao.CategoriaDAO;
import dao.ClientesDAO;
import dao.ProductosDAO;

public class Main1 {

	public static void main(String[] args) throws SQLException {
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
								// crearPedido(pedido);
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
					int subOpcion4;
					do {
						try {
							subOpcion4 = Funciones.dimeEntero("1. Bajo stock\n" + "2. Pedidos por cliente\n"
									+ "3. Productos más vendidos\n" + "0. Volver", sc);
							switch (subOpcion4) {
							case 1:
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

	public static void gestionCategorias() {
		Categoria categoria = new Categoria();
		Scanner sc = new Scanner(System.in);
		String nombre = Funciones.dimeString("Nuevo Nombre", sc);
		categoria.setNombre(nombre);
		CategoriaDAO.inserta(categoria);
	}

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

	public static void vercode(Scanner sc) {
		int code = Funciones.dimeEntero("Introduce codigo", sc);
		ClientesDAO.buscarCliente(code);
	}

	public static void mostrarCat(int num, Scanner sc) {
		System.out.println("A continuacion se mostraran las categorias");
		System.out.println(CategoriaDAO.listarCategorias());
		num = Funciones.dimeEntero("Seleccione una de las siguientes categorias", sc);

		System.out.println(ProductosDAO.listaProductosPorCategoria(num));
	}

	public static void buscarProd(Scanner sc) throws SQLException {
		String nombre = Funciones.dimeString("Introduce un nombre", sc);
		String talla = Funciones.dimeString("Introduce una talla", sc);
		String color = Funciones.dimeString("Introduce un color", sc);
		List<Producto> productos = ProductosDAO.buscarProductos(nombre, talla, color);
		for (Producto p : productos) {
			System.out.println("nombre" + p.getNombre());
			System.out.println("talla" + p.getTalla());
			System.out.println("color" + p.getColor());
		}
	}

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

			}
		} while (!true);

		return null;
	}

}