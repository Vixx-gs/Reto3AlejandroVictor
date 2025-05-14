package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Clases.Categoria;
import Clases.Producto;
import Util.Conexion;
import Util.Funciones;

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
			PreparedStatement pst = con.prepareStatement("insert into Producto(producto) values" + "(?,?,?,?,?,?,?)",
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
	
	public List<Producto>buscarProductos(Connection con, String nombre, String talla, String color) throws SQLException{
		//abro conexion
		con = Conexion.abreConexion();
		
		//Genero consulta
		PreparedStatement pst = con.prepareStatement("Select * from productos where 1=1");
		
		
		
		
		
				return null;
		
	}
}
