package dao;

import bll.util.TableUtil;
import connection.DatabaseConnection;
import model.Order;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Order database access object
 * Contains basic methods for order operations
 *
 * @author Csabi
 */
public class OrderDAO{
    private Statement orderStatement;
    private static final OrderDAO instance = new OrderDAO();

    /**
     * Returns instance of OrderDAO object
     *
     * @return
     */
    public static OrderDAO getInstance() {
        return instance;
    }

    /**
     * Constructor
     */
    public OrderDAO() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            this.orderStatement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a list of Orders objects from the database
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Order> getOrderList() throws SQLException {
        ResultSet rs = orderStatement.executeQuery("SELECT * FROM orders");

        ArrayList<Order> orderList = new ArrayList<>();
        while (rs.next()) {
            Order newOrder = new Order(rs.getInt("order_id"),
                    rs.getInt("client_id"),
                    rs.getInt("product_id"),
                    rs.getInt("order_quantity"));
            orderList.add(newOrder);
        }
        return orderList;
    }

    /**
     * Adds order to the database
     * The parameters correspond to the columns of the database
     *
     * @param order
     * @throws SQLException
     */
    public void insertOrder(Order order) throws SQLException {
        String insertSql = "INSERT INTO orders(order_id, product_id, client_id, order_quantity) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(insertSql);
        preparedStatement.setInt(1, order.getOrderId());
        preparedStatement.setInt(2, order.getProductId());
        preparedStatement.setInt(3, order.getClientId());
        preparedStatement.setInt(4, order.getOrderQuantity());
        preparedStatement.executeUpdate();

        decrementProduct(order.getProductId(), order.getOrderQuantity());
    }

    /**
     * Deletes a order with a given ID
     *
     * @param order_id
     * @throws SQLException
     */
    public void deleteOrder(int order_id) throws SQLException {
        String deleteSql = "DELETE FROM orders WHERE order_id = " + order_id;
        PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(deleteSql);
        preparedStatement.executeUpdate();
    }

    /**
     * Decrements the stock of a product with a given ID by the given quantity after an order is successfully created
     *
     * @param product_id
     * @param order_quantity
     * @throws SQLException
     */
    public void decrementProduct(int product_id, int order_quantity) throws SQLException {
        String updateSql = "UPDATE products SET stock = " + (getStockNumber(product_id) - order_quantity) + " WHERE product_id = " + product_id + ";";
        PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(updateSql);
        preparedStatement.executeUpdate();
    }

    /**
     * Returns true if there is enough stock of a product with a given ID, false otherwise
     *
     * @param product_id
     * @param order_quantity
     * @return
     * @throws SQLException
     */
    public boolean existsStock(int product_id, int order_quantity) throws SQLException {
        ResultSet rs = orderStatement.executeQuery("SELECT stock FROM products WHERE product_id = " + product_id);
        int stock = ((Number) rs.getObject(1)).intValue();

        if (stock < order_quantity) {
            JOptionPane.showMessageDialog(null, "Not enough stock!");
            return false;
        } else
            return true;
    }

    /**
     * Returns true if order with given ID exists, false otherwise
     *
     * @param order_id
     * @return
     * @throws SQLException
     */
    public boolean existsOrderID(int order_id) throws SQLException {
        ResultSet rs = orderStatement.executeQuery("SELECT * FROM orders WHERE order_id = " + order_id);
        return rs.isBeforeFirst();
    }

    /**
     * Returns the number of stock of a product with a given ID
     *
     * @param product_id
     * @return
     * @throws SQLException
     */
    private int getStockNumber(int product_id) throws SQLException {
        ResultSet rs = orderStatement.executeQuery("SELECT stock FROM products WHERE product_id = " + product_id);
        return ((Number) rs.getObject(1)).intValue();
    }

}
