package dao;

import connection.DatabaseConnection;
import model.Product;

import java.sql.*;
import java.util.ArrayList;

/**
 * Product database access object
 *
 * @author Csabi
 */
public class ProductDAO{
    private Statement productStatement;
    private static final ProductDAO instance = new ProductDAO();

    /**
     * Returns instance of ProductDAO object
     *
     * @return
     */
    public static ProductDAO getInstance() {
        return instance;
    }

    /**
     * Constructor
     */
    public ProductDAO() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            productStatement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a list of Products objects from the database
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Product> getProductList() throws SQLException {
        ResultSet rs = productStatement.executeQuery("SELECT * FROM products");

        ArrayList<Product> productList = new ArrayList<>();
        while (rs.next()) {
            Product newProduct = new Product(rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getInt("price"),
                    rs.getInt("stock"));
            productList.add(newProduct);
        }
        return productList;
    }

    /**
     * Returns product with a given ID
     *
     * @param product_id
     * @return
     * @throws SQLException
     */
    public Product getProduct(int product_id) throws SQLException {
        ResultSet rs = productStatement.executeQuery("SELECT * FROM products WHERE product_id = " + product_id);

            Product newProduct = new Product(rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getInt("price"),
                    rs.getInt("stock"));

        return newProduct;
    }

    /**
     * Adds product to the database
     * The parameters correspond to the columns of the database
     *
     * @param product_id
     * @param product_name
     * @param price
     * @param stock
     * @throws SQLException
     */
    public void insertProduct(int product_id, String product_name, int price, int stock) throws SQLException {
            String insertSql = "INSERT INTO products(product_id, product_name, price, stock) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(insertSql);
            preparedStatement.setInt(1, product_id);
            preparedStatement.setString(2, product_name);
            preparedStatement.setInt(3, price);
            preparedStatement.setInt(4, stock);
            preparedStatement.executeUpdate();
    }

    /**
     * Deletes a product with a given ID
     *
     * @param product_id
     * @throws SQLException
     */
    public void deleteProduct(int product_id) throws SQLException {
            String deleteSql = "DELETE FROM products WHERE product_id = " + product_id;
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(deleteSql);
            preparedStatement.executeUpdate();
    }

    /**
     * Edits product
     *
     * @param product_id
     * @param product_name
     * @param price
     * @param stock
     * @throws SQLException
     */
    public void editProduct(int product_id, String product_name, int price, int stock) throws SQLException {
            String editSql = "UPDATE products SET  " +
                    "product_id = " + product_id + ", " +
                    "product_name = '" + product_name + "', " +
                    "price = " + price + ", " +
                    "stock = " + stock + " " +
                    "WHERE product_id = " + product_id;
            PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(editSql);
            preparedStatement.executeUpdate();
    }

    /**
     * Returns true if product with given ID has an ongoing order, false otherwise
     *
     * @param product_id
     * @return
     * @throws SQLException
     */
    public boolean existsProductOrder(int product_id) throws SQLException {
        ResultSet rs = productStatement.executeQuery("SELECT * FROM orders WHERE product_id = " + product_id);
        return rs.isBeforeFirst();
    }

    /**
     * Returns true if product with given ID exists, false otherwise
     *
     * @param product_id
     * @return
     * @throws SQLException
     */
    public boolean existsProductID (int product_id) throws SQLException {
        ResultSet rs = productStatement.executeQuery("SELECT * FROM products WHERE product_id = " + product_id);
        return rs.isBeforeFirst();
    }



}
