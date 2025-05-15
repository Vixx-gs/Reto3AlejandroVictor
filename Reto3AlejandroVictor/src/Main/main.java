package Main;

import java.util.Random;
import java.util.Scanner;

import Clases.Categoria;
import Clases.Cliente;
import Clases.Producto;
import Util.Funciones;
import dao.CategoriaDAO;
import dao.ProductosDAO;

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
		case 1: 
			int op1=0;
			switch(op1) {
			case 1:
				Categoria catn = new Categoria();
				gestionCategorias(catn);
			}
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
	public static void gestionCategorias(Categoria categoria) {
		Scanner sc = new Scanner(System.in);
		String nombre = Funciones.dimeString("Nuevo Nombre", sc);
		categoria.setNombre(nombre);
		CategoriaDAO.inserta(categoria);
	}
	public static void gestionClientes(Cliente cliente) {
		Scanner sc = new Scanner(System.in);
		System.out.println("/nDatos actuales del cliente");
		System.out.println("Nombre: " + cliente.getNombre());
		System.out.println("Direccion: "+ cliente.getDireccion());
		System.out.println("Codigo: "+ cliente.getCodigo());
		
		System.out.println("/nIntroduce los nuevos datos");
		String nombre = Funciones.dimeString("Nuevo Nombre", sc);
		cliente.setNombre(nombre);
		
		String direccion = Funciones.dimeString("Nueva Direccion", sc);
		cliente.setDireccion(direccion);
		
		int codigo = Funciones.dimeEntero("Nuevo Codigo", sc);
		cliente.setCodigo(codigo); 
		
	}
	public static void gestionProductos(Producto producto) {
		Scanner sc = new Scanner(System.in);
		int categoriaElegida=0;
		Producto prod= new Producto();
		
		int idCat=Funciones.dimeEntero("Introduzca una categoria", sc);
		producto.setIdCategoria(new Categoria(idCat));
		String sNombre=Funciones.dimeString("Introduzca un nombre de un producto", sc);
		producto.setNombre(sNombre);
		Double dPrecio=Funciones.dimeDouble("Introduzca el precio", sc);
		producto.setPrecio(dPrecio);
		String sColor=Funciones.dimeString("Introduzca el color", sc);
		producto.setColor(sColor);
		String sTalla=Funciones.dimeString("Introduzca la talla", sc);
		producto.setTalla(sTalla);
		String sDescripcion=Funciones.dimeString("Introduzca una descripcion", sc);
		producto.setDescripcion(sDescripcion);
		int iStock=Funciones.dimeEntero("introduzca un stock", sc);
		producto.setStock(iStock);
		
		
		
		do {
			try {
				System.out.println("A continuacion mostraremos las categorias "
						+ "existentes ");
				for (Categoria cat : CategoriaDAO.listaIdCat()) {
					System.out.println(cat.getIdCategoria());
				}
				//Elije una categoria existente
				categoriaElegida=Funciones.dimeEntero("Introduzca una categoria"
						+ " existente", sc);
				
				//Comprobamos si existe la categoria, si existe salimos del bucle
				if(CategoriaDAO.listaIdCat().contains(categoriaElegida)) {
					break;
					
				}
				
				
				
			} catch (Exception e) {
				
			}
		} while (true);
		
		
		Categoria cat= new Categoria(idCat);
		prod= new Producto(cat,sNombre
				,dPrecio, sColor, sTalla, sDescripcion, iStock);
		
		ProductosDAO.insertaProducto(prod);
		
		
	}

}