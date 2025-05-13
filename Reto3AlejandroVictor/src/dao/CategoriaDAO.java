package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Clases.Categoria;
import Util.Conexion;

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
	
	public static void inserta(Categoria categoria)
	{
		try {
			//abro conexion
			Connection con = Conexion.abreConexion();
			//creo select
			PreparedStatement pst = con.prepareStatement("insert into Categoria(categoria) values (?,?)",Statement.RETURN_GENERATED_KEYS);
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


