package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Util.Conexion;
import clases.Cliente;

/**
 * Clase DAO (Data Access Object) para la entidad {@link Cliente}.
 * Proporciona métodos para realizar operaciones de acceso a datos
 * relacionados con los clientes en la base de datos.
 * 
 *Métodos disponibles:
 *{@link #lista()}
 *{@link #insertaClien(Cliente)}
 *{@link #buscarCliente(int)}
 *{@link #actualizarCliente(Cliente)}
 *{@link #obtenerporCodigo(int)}
 * 
 * @author Victor Gallego
 * @version 1
 */
public class ClientesDAO {

    /**
     * Recupera una lista de todos los clientes ordenados por nombre ascendente.
     * 
     * @return lista de objetos {@link Cliente}
     */
    public static List<Cliente> lista() {
        List<Cliente> lista = new ArrayList<Cliente>();
        try {
            Connection con = Conexion.abreConexion();
            PreparedStatement pst = con.prepareStatement(
                "Select idcliente, nombre, direccion, codigo from cliente order by nombre asc");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                lista.add(new Cliente(rs.getInt("idcliente"), rs.getString("nombre"),
                        rs.getString("direccion"), rs.getInt("codigo")));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexion.cierraConexion();
        }
        return lista;
    }

    /**
     * Inserta un nuevo cliente en la base de datos.
     * 
     * @param cliente el objeto {@link Cliente} que se desea insertar
     */
    public static void insertaClien(Cliente cliente) {
        try {
            Connection con = Conexion.abreConexion();
            PreparedStatement pst = con.prepareStatement(
                "insert into Cliente(cliente) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, cliente.getNombre());
            pst.setString(2, cliente.getDireccion());
            pst.setInt(3, cliente.getCodigo());
            pst.execute();

            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                cliente.setIdCliente(1); // Error potencial: debería usar rs.getInt(1)
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexion.cierraConexion();
        }
    }

    /**
     * Busca un cliente en la base de datos por su código.
     * 
     * @param codigoI el código del cliente a buscar
     * @return el objeto {@link Cliente} encontrado o {@code null} si no se encuentra
     */
    public static Cliente buscarCliente(int codigoI) {
        try {
            Connection con = Conexion.abreConexion();
            PreparedStatement pst = con.prepareStatement(
                "Select * from clientes where codigo = ?");
            pst.setInt(1, codigoI);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt("idCliente"), rs.getString("nombre"),
                        rs.getString("direccion"), rs.getInt("codigo"));
                rs.close();
                return cliente;
            }
            rs.close();
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    /**
     * Actualiza la información de un cliente existente en la base de datos.
     * 
     * @param cliente el objeto {@link Cliente} con los datos actualizados
     */
    public static void actualizarCliente(Cliente cliente) {
        try {
            Connection con = Conexion.abreConexion();
            PreparedStatement pst = con.prepareStatement(
                "Update cliente set nombre= ?, direccion= ?, where codigo = ?");
            pst.setString(1, cliente.getNombre());
            pst.setString(2, cliente.getDireccion());
            pst.setInt(3, cliente.getCodigo());

            int eu = pst.executeUpdate();
            if (eu > 0) {
                System.out.println("Cliente actualizado correctamente");
            } else {
                System.out.println("No se ha podido actualizar el cliente");
            }
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene un cliente por su código.
     * 
     * @param codigo el código del cliente
     * @return el objeto {@link Cliente} correspondiente, o {@code null} si no se encuentra
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public static Cliente obtenerporCodigo(int codigo) throws SQLException {
        Cliente cliente = null;
        try (Connection con = Conexion.abreConexion()) {
            PreparedStatement pst = con.prepareStatement("Select * from cliente where codigo = ?");
            pst.setInt(1, codigo);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setCodigo(rs.getInt("codigo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente;
    }
}
