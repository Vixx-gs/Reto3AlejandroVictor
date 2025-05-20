package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Util.Conexion;
import Util.Funciones;
import clases.Categoria;
import clases.Cliente;

public class ClientesDAO {
	public static List<Cliente> lista() {
		List<Cliente> lista = new ArrayList<Cliente>();  
		try {
			// Abro conexion
			Connection con = Conexion.abreConexion();
			// Creo Select

			PreparedStatement pst = con
					.prepareStatement("Select idcliente, nombre, direccion, codigo from cliente order by nombre asc");
			// Metemos la consulta en un resultado de consultas
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				lista.add(new Cliente(rs.getInt("idcliente"), 
						rs.getString("nombre"), rs.getString("direccion"), rs.getInt("codigo")));
				
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
	
	public static void insertaClien(Cliente cliente)
	{
		try {
			//abro conexion
			Connection con = Conexion.abreConexion();
			//creo select
			PreparedStatement pst = con.prepareStatement("insert into Cliente(cliente) values (?,?,?)",Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, cliente.getNombre());
			pst.setString(2, cliente.getDireccion());
			pst.setInt(3, cliente.getCodigo());
			pst.execute();
			//recupero clave 
			ResultSet rs = pst.getGeneratedKeys();
			if(rs.next())
				cliente.setIdCliente(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			Conexion.cierraConexion();
		}
	}
	
	public static Cliente buscarCliente(int codigoI) {
		try {
			//abro conexion
			Connection con = Conexion.abreConexion();
			//buscar al cliente
			PreparedStatement pst = con.prepareStatement("Select * from clientes where codigo = ?");
			pst.setInt(1, codigoI);
			//ejecuto query
			ResultSet rs = pst.executeQuery();
			
			//encuentro al cliente y guardo sus datos 
			if(rs.next()) {
				Cliente cliente = new Cliente(rs.getInt("idCliente"), rs.getString("nombre"), rs.getString("direccion"), rs.getInt("codigo"));
				rs.close();
				//devuelvo a ese cliente
				return cliente;
			}
			rs.close();
		} catch (Exception e) {
			return null;
		}
		return null;
	}
	
	public static void actualizarCliente(Cliente cliente) {
		try {
			//abro conexion
			Connection con = Conexion.abreConexion();
			//actualizo el cliente
			PreparedStatement pst = con.prepareStatement("Update cliente set nombre= ?, direccion= ?, where codigo = ?");
			pst.setString(1, cliente.getNombre());
			pst.setString(2, cliente.getDireccion());
			pst.setInt(3, cliente.getCodigo());
			
			int eu = pst.executeUpdate();
			if(eu>0) {
				System.out.println("Cliente actualizado correctamente");
			}
			else {
				System.out.println("No se a podido actualizar el cliente");
			}
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
