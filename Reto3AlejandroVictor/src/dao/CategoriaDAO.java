package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Util.Conexion;
import clases.Categoria;
import clases.Producto;

/**
 * Clase DAO (Data Access Object) para la entidad {@link Categoria}.
 * Proporciona métodos para interactuar con la base de datos y realizar
 * operaciones relacionadas con las categorías.
 * 
 * Métodos principales:
 *{@link #lista()}
 *{@link #listaIdCat()}
 *{@link #listarCategorias()}
 *{@link #inserta(Categoria)}
 * 
 * @author Victor Gallego
 * @version 1
 */
public class CategoriaDAO {

    /**
     * Recupera una lista de todas las categorías ordenadas por nombre ascendente.
     * 
     * @return una lista de objetos {@code Categoria}
     */
    public static List<Categoria> lista() {
        List<Categoria> lista = new ArrayList<Categoria>();
        try {
            Connection con = Conexion.abreConexion();
            PreparedStatement pst = con.prepareStatement(
                "Select idCategoria, nombre from categoria order by nombre asc");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                lista.add(new Categoria(rs.getInt("idcategoria"), rs.getString("nombre")));
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
     * Recupera una lista con solo los ID de las categorías desde la tabla "categorias".
     * 
     * @return una lista de objetos {@code Categoria} con solo el ID establecido
     */
    public static List<Categoria> listaIdCat() {
        List<Categoria> lista = new ArrayList<Categoria>();
        try {
            Connection con = Conexion.abreConexion();
            PreparedStatement pst = con.prepareStatement(
                "Select idCategoria from categorias order by nombre asc");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                lista.add(new Categoria(rs.getInt("idcategoria")));
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
     * Método incompleto que debería listar los productos por categoría.
     * Actualmente no implementado.
     */
    public static void listarProductosPorCategoria() {
        List<Categoria> lista = new ArrayList<Categoria>();
        try {
            Connection con = Conexion.abreConexion();
            PreparedStatement pst = con.prepareStatement("Select * from ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Recupera una lista de categorías desde la tabla "categorias".
     * 
     * @return una lista de objetos {@code Categoria}
     */
    public static List<Categoria> listarCategorias() {
        List<Categoria> listaCategoria = new ArrayList<>();
        try {
            Connection con = Conexion.abreConexion();
            PreparedStatement pst = con.prepareStatement("Select idcategoria, nombre from categorias");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setIdCategoria(rs.getInt("idcategoria"));
                cat.setNombre(rs.getString("nombre"));
                listaCategoria.add(cat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaCategoria;
    }

    /**
     * Inserta una nueva categoría en la base de datos.
     * 
     * @param categoria el objeto {@code Categoria} sin insertar aun 
     */
    public static void inserta(Categoria categoria) {
        try {
            Connection con = Conexion.abreConexion();
            PreparedStatement pst = con.prepareStatement(
                "insert into Categorias(nombre) values (?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, categoria.getNombre());
            pst.execute();

            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                categoria.setIdCategoria(rs.getInt(1));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexion.cierraConexion();
        }
    }
}


