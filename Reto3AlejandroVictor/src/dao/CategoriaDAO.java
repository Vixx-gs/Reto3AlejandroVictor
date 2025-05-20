package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Util.Conexion;
import clases.Categoria;
import clases.Producto;

public class CategoriaDAO {

	public static List<Categoria> lista() {
		List<Categoria> lista = new ArrayList<Categoria>();  
		try {
			// Abro conexion
			Connection con = Conexion.abreConexion();
			// Creo Select

			PreparedStatement pst = con
					.prepareStatement("Select idCategoria, nombre " + "from categoria order by nombre asc");
			// Metemos la consulta en un resultado de consultas
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				lista.add(new Categoria(rs.getInt("idcategoria"), 
						rs.getString("nombre")));
				
			}
			rs.close();
		} catch (Exception e) {
				e.printStackTrace();
		}
		finally {
			Conexion.cierraConexion();
		}
		return lista;

	}
	public static List<Categoria> listaIdCat() {
		List<Categoria> lista = new ArrayList<Categoria>();  
		try {
			// Abro conexion
			Connection con = Conexion.abreConexion();
			// Creo Select

			PreparedStatement pst = con.prepareStatement("Select idCategoria from categorias order by nombre asc");
			// Metemos la consulta en un resultado de consultas
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				lista.add(new Categoria(rs.getInt("idcategoria")));
				
			}
			rs.close();
		} catch (Exception e) {
				e.printStackTrace();
		}
		finally {
			Conexion.cierraConexion();
		}
		return lista;

	}
	public static void listarProductosPorCategoria() {
		List<Categoria> lista = new ArrayList<Categoria>();  
		 try {
			Connection con=Conexion.abreConexion();
			PreparedStatement pst= con.prepareStatement("Select * from ");
		} catch (Exception e) {
			
		}
	}
	public static List <Categoria> listarCategorias(){
		List<Categoria>listaCategoria = new ArrayList<>();
		try {
			// Abro conexion
			Connection con = Conexion.abreConexion();
			// Preparo consulta
			PreparedStatement pst = con.prepareStatement("Select idcategoria, nombre"
					+ " from categorias");
			ResultSet rs= pst.executeQuery();
			while(rs.next()) {
				Categoria cat= new Categoria();
				cat.setIdCategoria(rs.getInt("idcategoria"));
				cat.setNombre(rs.getString("nombre"));
				
				
				listaCategoria.add(cat);
		}
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaCategoria;
	}
	
	public static void inserta(Categoria categoria)
	{
		try {
			//abro conexion
			Connection con = Conexion.abreConexion();
			//creo select
			PreparedStatement pst = con.prepareStatement("insert into Categorias(nombre)"
					+ " values (?)",Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, categoria.getNombre());
			pst.execute();
			//recupero clave
			ResultSet rs = pst.getGeneratedKeys();
			if(rs.next())
				categoria.setIdCategoria(rs.getInt(1));
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			Conexion.cierraConexion();
		}
	}
 
}


