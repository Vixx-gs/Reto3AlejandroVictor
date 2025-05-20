package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Util.Conexion;
import clases.Cliente;
import clases.Pedido;

public class PedidoDAO {
	public static List<Pedido> lista() {
		List<Pedido> lista = new ArrayList<Pedido>();  
		try {
			// Abro conexion
			Connection con = Conexion.abreConexion();
			// Creo Select

			PreparedStatement pst = con
					.prepareStatement("Select idCategoria, nombre " + "from categoria order by nombre asc");
			// Metemos la consulta en un resultado de consultas
			ResultSet rs = pst.executeQuery();
			Cliente cl = new Cliente(rs.getInt("idcliente"), 
					rs.getString("nombre"), rs.getString("direccion"), rs.getInt("codigo"));
			while (rs.next()) {
				lista.add(new Pedido(rs.getInt("idpedido"), cl, rs.getDouble("precioTotal"), rs.getString("direccionEnvio"), rs.getDate("fecha")));
				
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
