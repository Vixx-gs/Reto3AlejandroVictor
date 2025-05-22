package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import Util.Conexion;
import clases.Categoria;
import clases.Producto;

public class ProductosDAO {
	public static List<Producto> lista() {
		List<Producto> lista = new ArrayList<Producto>();
		try {
			// Abro conexion
			Connection con = Conexion.abreConexion();
			// Creo Select

			PreparedStatement pst = con.prepareStatement("Select idproducto, idcategoria, nombre, precio, descripcion, "
					+ "color, talla, stock from producto order by nombre asc");
			// Metemos la consulta en un resultado de consultas
			ResultSet rs = pst.executeQuery();
			Categoria cat = new Categoria(rs.getInt("idcategoria"), rs.getString("nombre"));
			while (rs.next()) {
				lista.add(new Producto(rs.getInt("idproducto"), cat, rs.getString("nombre"), rs.getDouble("precio"),
						rs.getString("descripcion"), rs.getString("color"), rs.getString("talla"), rs.getInt("stock")));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cierraConexion();
		}
		return lista;

	}

	public static void insertaProducto(Producto prod) {
		try {
			// Abro conexion
			Connection con = Conexion.abreConexion();

			// Genero consulta
			PreparedStatement pst = con.prepareStatement(
					"insert into productos(idcategoria, nombre, precio, descripcion, color, talla, stock) values"
							+ "(?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, prod.getIdCategoria().getIdCategoria());
			pst.setString(2, prod.getNombre());
			pst.setDouble(3, prod.getPrecio());
			pst.setString(4, prod.getDescripcion());
			pst.setString(5, prod.getColor());
			pst.setString(6, prod.getTalla());
			pst.setInt(7, prod.getStock());

			pst.execute();
			// recupero clave
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next())
				prod.setIdProducto(rs.getInt(1));
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cierraConexion();
		}
	}

	public static void pideProducto(Producto prod) {
		try {
			// Abro conexion
			Connection con = Conexion.abreConexion();
			// Preparo consulta
			PreparedStatement pst = con.prepareStatement("Select * from producto " + "where nombre= ?");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
<<<<<<< HEAD
	public static List<Producto> bajoStock(){
		List<Producto> listaProductos= new ArrayList<>();
		try{
			//Abre conexion
			Connection con =Conexion.abreConexion();

			//Preparo consulta
			PreparedStatement pst= con.prepareStatement("select idcategoria, nombre, precio, descripción, color, talla, stock  from productos where stock>5");

			//Conjunto de resultados
			ResultSet rs=pst.executeQuery();

			Categoria cat= new Categoria(rs.getInt("idCategoria"));
			while(rs.next()){
			Producto prod= new Producto(cat, rs.getString("nombre"), rs.getDouble("precio"),
									rs.getString("descripcion"), rs.getString("color"),
									rs.getString("talla"), rs.getInt("stock"));

			listaProductos.add(prod);
			}
} catch (Exception e) {
			
			// TODO: handle exception
		}
			return listaProductos;
			}
		
	public static List<Producto> listaProductosPorCategoria(int num) {
		List<Producto> listaProductos = new ArrayList<Producto>();
		try {
			// Abro conexion
			Connection con = Conexion.abreConexion();
			// Preparo consulta
			/*
			 * PreparedStatement pst=
			 * con.prepareStatement("select nombre, precio, descripcion," +
			 * "color, talla, stock " + "from productos" + "where idcategoria=?");
			 */
			PreparedStatement pst = con.prepareStatement("select * from productos where idcategoria=?");
			pst.setInt(1, num);

			// Conjunto de resultados
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Producto prod = new Producto(rs.getString("nombre"), rs.getDouble("precio"),
						rs.getString("descripcion"), rs.getString("color"), rs.getString("talla"), rs.getInt("stock"));

				listaProductos.add(prod);
			}
		} catch (Exception e) {

			// TODO: handle exception
		}
		return listaProductos;

	}

	public static List<Producto> buscarProductos(String nombre, String talla, String color) throws SQLException {
		List<Producto> productos = new ArrayList<>();
		String vacio = "";

		String prodsql = "CALL buscarProductos(?,?,?)";
		try (Connection con = Conexion.abreConexion(); PreparedStatement pst = con.prepareStatement(prodsql)) {
			if (nombre.equals(vacio))
				pst.setNull(1, Types.VARCHAR);
			else
				pst.setString(1, nombre);

			if (talla.equals(vacio))
				pst.setNull(2, Types.VARCHAR);
			else
				pst.setString(2, talla);

			if (color.equals(vacio))
				pst.setNull(3, Types.VARCHAR);
			else
				pst.setString(3, color);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Categoria cat1 = new Categoria(rs.getInt("idCategoria"), rs.getString("nombre"));
				productos.add(new Producto(rs.getInt("idProducto"), cat1, rs.getString("nombre"),
						rs.getDouble("precio"), rs.getString("descripcion"), rs.getString("color"),
						rs.getString("talla"), rs.getInt("stock")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productos;

	}

	public static Producto obtenerporNombre(String nombre) throws SQLException {
		Producto producto = null;
		try (Connection con = Conexion.abreConexion();) {
			PreparedStatement pst = con.prepareStatement("Select * from producto where nombre = ?");
			pst.setString(1, nombre);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				Categoria cat = new Categoria(rs.getInt("idCategoria"));
				producto = new Producto(cat, rs.getString("nombre"), rs.getDouble("precio"),
						rs.getString("descripcion"), rs.getString("color"), rs.getString("talla"), rs.getInt("stock"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return producto;

	}
}
