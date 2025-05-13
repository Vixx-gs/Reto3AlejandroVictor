package Main;

import java.util.Random;
import java.util.Scanner;

import Clases.Categoria;
import Clases.Cliente;
import Clases.Producto;
import Util.Funciones;

public class main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random r = new Random();
		
		int opcion=0;
		do {
			try {
				opcion=Funciones.dimeEntero("Introduzca una opcion del 1 al 4", sc);
				if (opcion<5&&opcion>0)
					break;
				
			} catch (Exception e) {
				System.out.println("Formato invalido");
			}
		} while (true);
		
		switch(opcion) {
		case 1: System.out.println("Mantenimientos:\n"
				+ "1.1. Gestión de categorías: pide por consola los datos de una nueva categoría e insertala en la BD.\n"
				+ "1.2. Gestión de productos: pide por consola los datos de un producto, muestras las categorías con su id,\n"
				+ "que el cliente elija una e inserta el producto en la BD.\n"
				+ "1.3. Gestión de clientes: se mostrará el siguiente submenú\n"
				);
		do {
			try {
				opcion=Funciones.dimeEntero("Elija un submenu", sc);
			if (opcion<4&&opcion>0)
				break;
			} catch (Exception e) {
				System.out.println("Formato invalido");
			}
			
		} while (true);
		
		if(opcion==3) {
			System.out.println("1.3.1. Alta de nuevos clientes: pide por consola los datos de un nuevo cliente e insértalo en la BD\n"
					+ "1.3.2. Búsqueda por código: pide por consola el código del cliente y búscalo en la BD, mostrando todos\n"
					+ "sus datos si está. Si no existe que lo diga. Pide por consola todos sus datos excepto el id y lo\n"
					+ "actualizas en la base de datos.\n"
					+ "");
		}
		
			
			break;
			
			
		case 2: 
			break;
		
		case 3:
			break;
		
		case 4:
			break;
			
		}

	}
	public static void gestionCategorias(Categoria cat) {
		
	}
	public static void gestionClientes(Cliente cli) {
		System.out.println("/nDatos actuales del cliente");
		System.out.println("");
	}
	public static void gestionProductos(Producto prod) {
		Scanner sc = new Scanner(System.in);
		
		
		String sIdCat=Funciones.dimeString("Introduzca una categoria", sc);
		String sNombre=Funciones.dimeString("Introduzca un nombre de un producto", sc);
		Double dPrecio=Funciones.dimeDouble("Introduzca el precio", sc);
		String sColor=Funciones.dimeString("Introduzca el color", sc);
		String sTalla=Funciones.dimeString("Introduzca la talla", sc);
		String sDescripcion=Funciones.dimeString("Introduzca una descripcion", sc);
	}

}