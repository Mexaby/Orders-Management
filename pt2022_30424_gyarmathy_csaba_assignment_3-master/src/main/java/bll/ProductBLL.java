package bll;

import bll.util.TableUtil;
import dao.ProductDAO;
import model.Product;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Product business logic layer
 * Contains the logic of the CRUD methods with validators
 *
 * @author Csabi
 */
public class ProductBLL {
    private static final ProductBLL instance = new ProductBLL();
    private final ProductDAO productDAO;

    /**
     * Returns instance of ProductBLL object
     *
     * @return
     */
    public static ProductBLL getInstance() {
        return instance;
    }

    /**
     * Constructor
     */
    public ProductBLL() {
        this.productDAO = ProductDAO.getInstance();
    }

    /**
     * Utilizes the method from TableUti to create a JTable of product objects and insert it into the interface
     *
     * @param frame
     * @throws SQLException
     */
    public void viewAllProducts(JFrame frame) throws SQLException {
        ArrayList<Product> productList = productDAO.getProductList();
        JTable productTable = TableUtil.createTable(productList);
        frame.add(new JScrollPane(productTable));
    }

    /**
     * Inserts product into database with validators
     *
     * @param product_id
     * @param product_name
     * @param price
     * @param stock
     * @throws SQLException
     */
    public void insertProduct(int product_id, String product_name, int price, int stock) throws SQLException {
        try {
            productDAO.insertProduct(product_id, product_name, price, stock);
            JOptionPane.showMessageDialog(null, "Successfully added!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Price and stock must be numbers.");
        }
    }

    /**
     * Deletes product from database with validators
     *
     * @param product_id
     * @throws SQLException
     */
    public void deleteProduct(int product_id) throws SQLException {
        if (!productDAO.existsProductID(product_id)) {
            JOptionPane.showMessageDialog(null, "ID does not exist");
            return;
        }
        if (productDAO.existsProductOrder(product_id)) {
            JOptionPane.showMessageDialog(null, "Can't delete product with ongoing order!");
        } else {
            productDAO.deleteProduct(product_id);
            JOptionPane.showMessageDialog(null, "Successfully deleted!");

        }
    }

    /**
     * Edits product from database with validators
     *
     * @param product_id
     * @param product_name
     * @param price
     * @param stock
     * @throws SQLException
     */
    public void editProduct(int product_id, String product_name, int price, int stock) throws SQLException {
        if (!productDAO.existsProductID(product_id)) {
            JOptionPane.showMessageDialog(null, "ID does not exist");
        } else {
            productDAO.editProduct(product_id, product_name, price, stock);
            JOptionPane.showMessageDialog(null, "Successfully edited!");

        }
    }
}
