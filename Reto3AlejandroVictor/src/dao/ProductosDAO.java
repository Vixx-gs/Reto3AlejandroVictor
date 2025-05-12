package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Clases.Categoria;
import Clases.Producto;
import Util.Conexion;

public class ProductosDAO {
	public static List<Producto> lista() {
		List<Producto> lista = new ArrayList<Producto>();  
		try {
			// Abro conexion
			Connection con = Conexion.abreConexion();
			// Creo Select

			PreparedStatement pst = con
					.prepareStatement("Select idproducto, idcategoria, nombre, precio, descripcion, "
							+ "color, talla, stock from producto order by nombre asc");
			// Metemos la consulta en un resultado de consultas
			ResultSet rs = pst.executeQuery();
			Categoria cat = new Categoria(rs.getInt("idcategoria"), 
					rs.getString("nombre"));
			while (rs.next()) {
				lista.add(new Producto(rs.getInt("idproducto"), cat , rs.getString("nombre"), rs.getDouble("precio"), rs.getString("descripcion"),
						 rs.getString("color"), rs.getString("talla"), rs.getInt("stock")));
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
}
