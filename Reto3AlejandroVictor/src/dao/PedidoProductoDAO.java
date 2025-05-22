package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Util.Conexion;
import clases.Categoria;
import clases.Cliente;
import clases.Pedido;
import clases.PedidoProducto;
import clases.Producto;

public class PedidoProductoDAO {
	public static List<PedidoProducto> lista(){
		List <PedidoProducto> lista= new ArrayList<PedidoProducto>();
			try {
				//Abre conexion
				Connection con= Conexion.abreConexion();
				//Preparo la consulta
				PreparedStatement pst= con.prepareStatement("Select * from pedidoproducto order by idpedidoproducto asc");
				//Meto el resultado en un conjunto de resultados
				ResultSet rs=pst.executeQuery();
				//Abrimos el bucle While
				Cliente cli= new Cliente(rs.getInt("idcliente"), rs.getString("nombre")
						, rs.getString("direccion"), rs.getInt("codigo") );
				Categoria cat = new Categoria(rs.getInt("idcategoria"), 
						rs.getString("nombre"));
				Producto prod= new Producto(rs.getInt("idproducto"), cat , rs.getString("nombre"), rs.getDouble("precio"), rs.getString("descripcion"),
						 rs.getString("color"), rs.getString("talla"), rs.getInt("stock"));
				Pedido ped= new Pedido(rs.getInt("idpedido"), cli, rs.getDouble("precioTotal"), 
						rs.getString("direccionEnvio"), rs.getDate("fecha"));
				while (rs.next()) {
					lista.add(new PedidoProducto(rs.getInt("idpedidoproducto"), prod, ped
							, rs.getInt("unidades"), rs.getDouble("precio")));
					
							
					
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
	
	public static Cliente buscarClienteId(int codigo) throws SQLException{
		Connection con= Conexion.abreConexion();
		PreparedStatement pst= con.prepareStatement("Select idcliente, nombre, direccion from clientes where codigo = ?");
		pst.setInt(1, codigo);
		ResultSet rs = pst.executeQuery();
		
		if(rs.next()) {
			Cliente datos = new Cliente(rs.getInt("idcliente"), rs.getString("nombre"), rs.getString("direccion"), codigo);
			return datos;
		}
		return null;
	}
	
	   public static void inserta(int idPedido, Producto producto, int cantidad) {
	        try (Connection con = Conexion.abreConexion()){
	            PreparedStatement ps = con.prepareStatement("INSERT INTO pedido_producto (idPedido, idProducto, cantidad, precioUnitario) VALUES (?, ?, ?, ?)");

	            ps.setInt(1, idPedido);
	            ps.setInt(2, producto.getIdProducto());
	            ps.setInt(3, cantidad);
	            ps.setDouble(4, producto.getPrecio());

	            int filas = ps.executeUpdate();

	            if (filas > 0) {
	                System.out.println("Producto añadido al pedido");
	            } else {
	                System.out.println("Error al añadir producto al pedido");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        finally {
	        	Conexion.cierraConexion();
	        }
	    }


}
