package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Clases.Categoria;
import Clases.Cliente;
import Util.Conexion;
import Util.Funciones;

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
	
	public static void buscarCliente() {
		Scanner sc = new Scanner(System.in);
		try {
			//pedir el codigo del cliente
			int codigoI = Funciones.dimeEntero("Introduce el codigo del cliente", sc);
			//abro conexion
			Connection con = Conexion.abreConexion();
			//buscar al cliente
			PreparedStatement pst = con.prepareStatement("Select * from clientes where codigo = ?");
			pst.setInt(1, codigoI);
			//recupero clave
			ResultSet rs = pst.getGeneratedKeys();
			
			if(rs.next()) {
				//muestro los datos de ahora
				System.out.println("Datos del cliente ahora mismo");
				System.out.println("Nombre: " + rs.getString("nombre"));
				System.out.println("Direccion: " + rs.getString("direccion"));
				System.out.println("Codigo: " + rs.getInt("codigo"));
				
				//pedir los datos del cliente nuevo
				System.out.println("Introduce los nuevos datos");
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
