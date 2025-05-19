package Main;

import java.util.Random;
import java.util.Scanner;

import Clases.Categoria;
import Clases.Cliente;
import Clases.Producto;
import Util.Funciones;
import dao.CategoriaDAO;
import dao.ClientesDAO;
import dao.ProductosDAO;

public class Main1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random r = new Random();
		
		int opcion = 0;
		
		do {
			try {
				opcion = Funciones.dimeEntero("Introduzca una de las siguientes opciones. \n"
						+ "1.Mantenimientos \n"
						+ "2. Catalogo productos \n"
						+ "3.Pedidos \n"
						+ "4. Informes", sc);
				if (opcion < 5 && opcion > 0)
					break;

			} catch (Exception e) {
				System.out.println("Formato invalido");
			}
		} while (true);

/*SubMenu1*/ switch (opcion) {
		case 1:

			System.out.println(
					"1.1. Gestión de categorías: pide por consola los datos de una nueva categoría e insertala en la BD.\n"
							+ "1.2. Gestión de productos: pide por consola los datos de un producto, muestras las categorías con su id,\n"
							+ "que el cliente elija una e inserta el producto en la BD.\n"
							+ "1.3. Gestión de clientes: se mostrará el siguiente submenú\n" + "");

			do {
				try {
					opcion = Funciones.dimeEntero("Introduzca una opcion del 1 al 3", sc);
					if (opcion < 4 && opcion > 0)
						break;

				} catch (Exception e) {
					System.out.println("Formato invalido");
				}
			} while (true);
			/* switch2 */ switch (opcion) {
			case 1:
				gestionCategorias();
				break;

			case 2:
				gestionProductos();
				break;
			case 3:
				gestionClientes();
				System.out.println(
						"1.3.1. Alta de nuevos clientes: pide por consola los datos de un nuevo cliente e insértalo en la BD\n"
								+ "1.3.2. Búsqueda por código: pide por consola el código del cliente y búscalo en la BD, mostrando todos\n"
								+ "sus datos si está. Si no existe que lo diga. Pide por consola todos sus datos excepto el id y lo\n"
								+ "actualizas en la base de datos.\n" + "");
				do {
					try {
						opcion = Funciones.dimeEntero("Introduzca una opcion del 1 al 2", sc);
						if (opcion < 3 && opcion > 0)
							break;

					} catch (Exception e) {
						System.out.println("Formato invalido");
					}
				} while (true);
				
				switch(opcion) {
				case 1:
					gestionClientes();
					break;
				case 2: int code = Funciones.dimeEntero("Introduce codigo", sc);
					ClientesDAO.buscarCliente(code);
					break;
				}
				
				break;
			}

/*SubMenu2*/ case 2:
			System.out.println("2.1 Listar productos por categoria \n"
					+ "2.2 Buscar productos.");
			
			do {
				try {
					opcion = Funciones.dimeEntero("Introduzca una opcion del 1 al 2", sc);
					if (opcion < 3 && opcion > 0)
						break;

				} catch (Exception e) {
					System.out.println("Formato invalido");
				}
			} while (true);

			switch(opcion) {
			case 1: /*listarProductos(producto);*/
				break;
			case 2: 
				break;
			}
			
			break;

/*SubMenu3*/	case 3: System.out.println("3.1 Crear pedido \n"
				+ "3.2 Ver pedidos.");
		do {
			try {
				opcion = Funciones.dimeEntero("Introduzca una opcion del 1 al 2", sc);
				if (opcion < 3 && opcion > 0)
					break;

			} catch (Exception e) {
				System.out.println("Formato invalido");
			}
		} while (true);
		switch(opcion) {
		case 1: /*crearPedido(pedido);*/
			break;
		case 2: /*verPedido(pedido);*/
			break;
		}
		
			break;

/*SubMenu4*/case 4: System.out.println("Informes \n"
				+ "4.1- Bajo stock \n "
				+ "4.2-Pedidos por cliente \n"
				+ "4.3-Productos mas vendidos ");
		do {
			try {
				opcion = Funciones.dimeEntero("Introduzca una opcion del 1 al 3", sc);
				if (opcion < 4 && opcion > 0)
					break;

			} catch (Exception e) {
				System.out.println("Formato invalido");
			}
		} while (true);
		
		switch(opcion) {
		case 1:
			break;
		case 2: 
			break;
		case 3:
			break;
		}
		
			break;

		}

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

}