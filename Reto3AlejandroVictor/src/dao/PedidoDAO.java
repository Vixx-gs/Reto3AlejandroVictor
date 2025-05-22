package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	 public static void inserta(Pedido pedido) {
	        try (Connection con = Conexion.abreConexion()) {
	            String sql = "INSERT INTO pedido (idCliente, precioTotal, direccionEnvio, fecha) VALUES (?, ?, ?, ?)";
	            PreparedStatement ps = con.prepareStatement(sql);

	            ps.setInt(1, pedido.getIdcliente().getIdCliente());
	            ps.setDouble(2, pedido.getPrecioTotal());
	            ps.setString(3, pedido.getDireccionEnvio());
	            ps.setDate(4, new Date(System.currentTimeMillis())); // o pedido.getFecha() si ya lo tiene

	            int filas = ps.executeUpdate();

	            if (filas > 0) {
	                System.out.println("Pedido guardado");
	            } else {
	                System.out.println("Error al guardar el pedido");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
