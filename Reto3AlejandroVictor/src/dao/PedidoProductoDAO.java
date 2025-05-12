package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Clases.Categoria;
import Clases.Cliente;
import Clases.Pedido;
import Clases.PedidoProducto;
import Clases.Producto;
import Util.Conexion;

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

}
