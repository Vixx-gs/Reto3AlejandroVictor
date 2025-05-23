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

/**
 * Clase DAO para la entidad {@link Pedido}.
 * Encargada de acceder y manipular datos relacionados con los pedidos en la base de datos.
 * 
 *Incluye métodos para listar todos los pedidos e insertar nuevos.
 * 
 * @author Victor Gallego
 * @version 1
 */
public class PedidoDAO {

    /**
     * Obtiene una lista de todos los pedidos registrados en la base de datos.
     * 
     * @return lista de objetos {@link Pedido}
     */
    public static List<Pedido> lista() {
        List<Pedido> lista = new ArrayList<Pedido>();
        try {
            Connection con = Conexion.abreConexion();
            PreparedStatement pst = con.prepareStatement(
                "SELECT p.idpedido, p.precioTotal, p.direccionEnvio, p.fecha, " +
                "c.idcliente, c.nombre, c.direccion, c.codigo " +
                "FROM pedido p INNER JOIN cliente c ON p.idcliente = c.idcliente " +
                "ORDER BY p.fecha DESC"
            );
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Cliente cl = new Cliente(
                    rs.getInt("idcliente"),
                    rs.getString("nombre"),
                    rs.getString("direccion"),
                    rs.getInt("codigo")
                );

                Pedido pedido = new Pedido(
                    rs.getInt("idpedido"),
                    cl,
                    rs.getDouble("precioTotal"),
                    rs.getString("direccionEnvio"),
                    rs.getDate("fecha")
                );

                lista.add(pedido);
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
     * Inserta un nuevo pedido en la base de datos.
     * 
     * @param pedido el objeto {@link Pedido} a insertar
     */
    public static void inserta(Pedido pedido) {
        try (Connection con = Conexion.abreConexion()) {
            String sql = "INSERT INTO pedido (idCliente, precioTotal, direccionEnvio, fecha) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, pedido.getIdcliente().getIdCliente());
            ps.setDouble(2, pedido.getPrecioTotal());
            ps.setString(3, pedido.getDireccionEnvio());
            ps.setDate(4, pedido.getFecha() != null ? pedido.getFecha() : new Date(System.currentTimeMillis()));

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Pedido guardado");
            } else {
                System.out.println("Error al guardar el pedido");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.cierraConexion();
        }
    }
}