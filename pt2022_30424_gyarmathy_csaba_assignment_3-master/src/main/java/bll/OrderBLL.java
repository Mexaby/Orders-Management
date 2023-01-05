package bll;

import bll.util.FileUtil;
import bll.util.TableUtil;
import dao.ClientDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Client;
import model.Order;
import model.Product;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Order business logic layer
 * Contains the logic of the CRUD methods with validators
 *
 * @author Csabi
 */
public class OrderBLL {
    private static final OrderBLL instance = new OrderBLL();
    public OrderDAO orderDAO;

    /**
     * Returns instance of OrderBLL object
     *
     * @return
     */
    public static OrderBLL getInstance() {
        return instance;
    }

    /**
     * Constructor
     */
    public OrderBLL() {
        this.orderDAO = OrderDAO.getInstance();
    }

    /**
     * Utilizes the method from TableUti to create a JTable of order objects and insert it into the interface
     *
     * @param frame
     * @throws SQLException
     */
    public void viewAllOrders(JFrame frame) throws SQLException {
        ArrayList<Order> orderList = orderDAO.getOrderList();
        JTable orderTable = TableUtil.createTable(orderList);
        frame.add(new JScrollPane(orderTable));
    }

    /**
     * Inserts order into database with validators
     * Creates bill
     *
     * @param order_id
     * @param client_id
     * @param product_id
     * @param order_quantity
     * @throws SQLException
     */
    public void insertOrder(int order_id, int client_id, int product_id, int order_quantity) throws SQLException {

        if (ClientDAO.getInstance().existsClientID(client_id)) {
            if (ProductDAO.getInstance().existsProductID(product_id)) {
                if (orderDAO.existsStock(product_id, order_quantity)) {
                    Order order = new Order(order_id, product_id, client_id, order_quantity);
                    orderDAO.insertOrder(order);

                    Client client = ClientDAO.getInstance().getClient(client_id);
                    Product product = ProductDAO.getInstance().getProduct(product_id);
                    FileUtil.createBill(client, order, product);

                    JOptionPane.showMessageDialog(null, "Successfully added!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid product ID");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid client ID");
        }

    }

    /**
     * Deletes order from database with validators
     *
     * @param order_id
     * @throws SQLException
     */
    public void deleteOrder(int order_id) throws SQLException {
        if (!orderDAO.existsOrderID(order_id)) {
            JOptionPane.showMessageDialog(null, "ID does not exist");
        } else {
            orderDAO.deleteOrder(order_id);
            JOptionPane.showMessageDialog(null, "Successfully deleted!");

        }
    }
}
