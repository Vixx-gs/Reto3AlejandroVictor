package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import Util.Conexion;
import clases.Categoria;
import clases.Producto;

/**
 * Clase DAO para manejar operaciones CRUD sobre la entidad {@link Producto}.
 * Contiene métodos para listar, insertar, buscar y actualizar productos en la base de datos.
 * 
 * @author Victor Gallego
 * @version 1
 */
public class ProductosDAO {

    /**
     * Obtiene una lista con todos los productos almacenados en la base de datos.
     * 
     * @return lista de productos
     */
    public static List<Producto> lista() {
        List<Producto> lista = new ArrayList<>();
        try {
            Connection con = Conexion.abreConexion();
            PreparedStatement pst = con.prepareStatement(
                "SELECT idproducto, idcategoria, nombre, precio, descripcion, color, talla, stock FROM producto ORDER BY nombre ASC");
            ResultSet rs = pst.executeQuery();

            Categoria cat = new Categoria(rs.getInt("idcategoria"), rs.getString("nombre"));
            while (rs.next()) {
                lista.add(new Producto(rs.getInt("idproducto"), cat, rs.getString("nombre"), rs.getDouble("precio"),
                        rs.getString("descripcion"), rs.getString("color"), rs.getString("talla"), rs.getInt("stock")));
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
     * Inserta un nuevo producto en la base de datos y asigna su id generado.
     * 
     * @param prod producto a insertar
     */
    public static void insertaProducto(Producto prod) {
        try {
            Connection con = Conexion.abreConexion();
            PreparedStatement pst = con.prepareStatement(
                "INSERT INTO productos(idcategoria, nombre, precio, descripcion, color, talla, stock) VALUES (?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, prod.getIdCategoria().getIdCategoria());
            pst.setString(2, prod.getNombre());
            pst.setDouble(3, prod.getPrecio());
            pst.setString(4, prod.getDescripcion());
            pst.setString(5, prod.getColor());
            pst.setString(6, prod.getTalla());
            pst.setInt(7, prod.getStock());

            pst.execute();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                prod.setIdProducto(rs.getInt(1));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexion.cierraConexion();
        }
    }

    public static void pideProducto(Producto prod) {
        try {
            Connection con = Conexion.abreConexion();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM producto WHERE nombre= ?");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene una lista de productos con stock mayor a 5.
     * 
     * @return lista de productos con stock > 5
     */
    public static List<Producto> bajoStock() {
        List<Producto> listaProductos = new ArrayList<>();
        try {
            Connection con = Conexion.abreConexion();
            PreparedStatement pst = con.prepareStatement(
                "SELECT idcategoria, nombre, precio, descripcion, color, talla, stock FROM productos WHERE stock > 5");
            ResultSet rs = pst.executeQuery();

            Categoria cat = new Categoria(rs.getInt("idCategoria"));
            while (rs.next()) {
                Producto prod = new Producto(cat, rs.getString("nombre"), rs.getDouble("precio"), rs.getString("descripcion"),
                        rs.getString("color"), rs.getString("talla"), rs.getInt("stock"));
                listaProductos.add(prod);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaProductos;
    }

    /**
     * Actualiza el stock del producto con el nombre indicado.
     * 
     * @param nombre nombre del producto
     * @param num nuevo valor de stock
     * @return lista con el producto actualizado (puede estar vacía)
     */
    public static List<Producto> actualizarStock(String nombre, int num) {
        List<Producto> listaProductos = new ArrayList<>();
        try {
            Connection con = Conexion.abreConexion();
            PreparedStatement pst = con.prepareStatement("UPDATE productos SET stock = ? WHERE nombre = ?");
            pst.setInt(1, num);
            pst.setString(2, nombre);
            pst.execute();

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Producto prod = new Producto(rs.getString(nombre), rs.getInt(num));
                listaProductos.add(prod);
            }
        } catch (Exception e) {
        }
        return listaProductos;
    }

    /**
     * Lista productos filtrados por categoría.
     * 
     * @param num id de la categoría
     * @return lista de productos pertenecientes a la categoría
     */
    public static List<Producto> listaProductosPorCategoria(int num) {
        List<Producto> listaProductos = new ArrayList<>();
        try {
            Connection con = Conexion.abreConexion();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM productos WHERE idcategoria = ?");
            pst.setInt(1, num);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Producto prod = new Producto(rs.getString("nombre"), rs.getDouble("precio"), rs.getString("descripcion"),
                        rs.getString("color"), rs.getString("talla"), rs.getInt("stock"));
                listaProductos.add(prod);
            }
        } catch (Exception e) {
        }
        return listaProductos;
    }

    /**
     * Busca productos usando un procedimiento almacenado con filtros opcionales.
     * 
     * @param nombre nombre del producto (puede ser vacío)
     * @param talla talla del producto (puede ser vacío)
     * @param color color del producto (puede ser vacío)
     * @return lista de productos que cumplen los criterios
     * @throws SQLException si ocurre un error de base de datos
     */
    public static List<Producto> buscarProductos(String nombre, String talla, String color) throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String vacio = "";

        String prodsql = "CALL buscarProductos(?,?,?)";
        try (Connection con = Conexion.abreConexion(); PreparedStatement pst = con.prepareStatement(prodsql)) {
            if (nombre.equals(vacio))
                pst.setNull(1, Types.VARCHAR);
            else
                pst.setString(1, nombre);

            if (talla.equals(vacio))
                pst.setNull(2, Types.VARCHAR);
            else
                pst.setString(2, talla);

            if (color.equals(vacio))
                pst.setNull(3, Types.VARCHAR);
            else
                pst.setString(3, color);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Categoria cat1 = new Categoria(rs.getInt("idCategoria"), rs.getString("nombre"));
                productos.add(new Producto(rs.getInt("idProducto"), cat1, rs.getString("nombre"), rs.getDouble("precio"),
                        rs.getString("descripcion"), rs.getString("color"), rs.getString("talla"), rs.getInt("stock")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productos;
    }

    /**
     * Obtiene un producto por su nombre.
     * 
     * @param nombre nombre del producto
     * @return producto encontrado o null si no existe
     * @throws SQLException si ocurre un error de base de datos
     */
    public static Producto obtenerporNombre(String nombre) throws SQLException {
        Producto producto = null;
        try (Connection con = Conexion.abreConexion();) {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM producto WHERE nombre = ?");
            pst.setString(1, nombre);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Categoria cat = new Categoria(rs.getInt("idCategoria"));
                producto = new Producto(cat, rs.getString("nombre"), rs.getDouble("precio"), rs.getString("descripcion"),
                        rs.getString("color"), rs.getString("talla"), rs.getInt("stock"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return producto;
    }

    /**
     * Reduce el stock de un producto en la cantidad especificada.
     * 
     * @param idProducto id del producto a actualizar
     * @param cantidadReducida cantidad a descontar del stock
     */
    public static void reducirStock(int idProducto, int cantidadReducida) {
        try (Connection con = Conexion.abreConexion()) {
            PreparedStatement ps = con.prepareStatement("UPDATE producto SET stock = stock - ? WHERE idProducto = ?");
            ps.setInt(1, cantidadReducida);
            ps.setInt(2, idProducto);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Stock actualizado correctamente");
            } else {
                System.out.println("Error al actualizar el stock");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
