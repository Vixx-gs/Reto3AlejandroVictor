package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Util.Conexion;
import clases.Categoria;
import clases.Cliente;
import clases.Pedido;
import clases.PedidoProducto;
import clases.Producto;

/**
 * Clase DAO para la entidad {@link PedidoProducto}.
 * Gestiona las operaciones de acceso a datos para pedidos y sus productos asociados.
 * 
 *Incluye métodos para listar todos los pedidos-producto, buscar clientes por código y añadir productos a pedidos.
 * 
 * @author Victor Gallego
 * @version 1
 */
public class PedidoProductoDAO {

    /**
     * Obtiene una lista de todos los objetos {@link PedidoProducto} almacenados en la base de datos.
     * 
     * @return lista con todos los pedidos-producto
     */
    public static List<PedidoProducto> lista() {
        List<PedidoProducto> lista = new ArrayList<PedidoProducto>();
        try {
            Connection con = Conexion.abreConexion();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM pedidoproducto ORDER BY idpedidoproducto ASC");
            ResultSet rs = pst.executeQuery();
            Cliente cli = new Cliente(rs.getInt("idcliente"), rs.getString("nombre"), rs.getString("direccion"), rs.getInt("codigo"));
            Categoria cat = new Categoria(rs.getInt("idcategoria"), rs.getString("nombre"));
            Producto prod = new Producto(rs.getInt("idproducto"), cat, rs.getString("nombre"), rs.getDouble("precio"), rs.getString("descripcion"),
                    rs.getString("color"), rs.getString("talla"), rs.getInt("stock"));
            Pedido ped = new Pedido(rs.getInt("idpedido"), cli, rs.getDouble("precioTotal"), rs.getString("direccionEnvio"), rs.getDate("fecha"));

            while (rs.next()) {
                lista.add(new PedidoProducto(rs.getInt("idpedidoproducto"), prod, ped,
                        rs.getInt("unidades"), rs.getDouble("precio")));
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
     * Busca un cliente en la base de datos por su código.
     * 
     * @param codigo código del cliente a buscar
     * @return objeto {@link Cliente} si se encuentra, null si no
     * @throws SQLException si ocurre un error con la base de datos
     */
    public static Cliente buscarClienteId(int codigo) throws SQLException {
        Connection con = Conexion.abreConexion();
        PreparedStatement pst = con.prepareStatement("SELECT idcliente, nombre, direccion FROM clientes WHERE codigo = ?");
        pst.setInt(1, codigo);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            Cliente datos = new Cliente(rs.getInt("idcliente"), rs.getString("nombre"), rs.getString("direccion"), codigo);
            return datos;
        }
        return null;
    }

    /**
     * Inserta un producto en un pedido especificado, con la cantidad indicada.
     * 
     * @param idPedido identificador del pedido
     * @param producto objeto {@link Producto} a añadir
     * @param cantidad cantidad de unidades a añadir
     */
    public static void inserta(int idPedido, Producto producto, int cantidad) {
        try (Connection con = Conexion.abreConexion()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO pedido_producto (idPedido, idProducto, cantidad, precioUnitario) VALUES (?, ?, ?, ?)");

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
        } finally {
            Conexion.cierraConexion();
        }
    }
}
